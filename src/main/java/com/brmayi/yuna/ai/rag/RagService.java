package com.brmayi.yuna.ai.rag;

import com.google.common.collect.Maps;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.pinecone.PineconeEmbeddingStore;
import dev.langchain4j.store.embedding.pinecone.PineconeServerlessIndexConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.brmayi.yuna.ai.ZhipinConfig.chatModel;
import static com.brmayi.yuna.ai.ZhipinConfig.embeddingModel;
import static java.util.UUID.randomUUID;

@Slf4j
@Service(value = "ragService")
public class RagService {
    private static Map<String, String> embeddeds = Maps.newHashMap();
    private static final String PINECONE_API_KEY = "eb19a1d4-2d01-46b4-be20-46a648decf53";
    public static final EmbeddingStore<TextSegment> embeddingStore = PineconeEmbeddingStore.builder()
            .apiKey(PINECONE_API_KEY)
            .index("guoya")
            .nameSpace(randomUUID().toString())
            .createIndex(PineconeServerlessIndexConfig.builder()
                    .cloud("AWS")
                    .region("us-east-1")
                    .dimension(1024)
                    .build())
            .build();

    public  String call(String text) {
        // 进行搜索
        String searchResult = search(text);

        // 将问题和检索结果合并成提示词，增强后调用大模型返回结果。
        return callAi(text, searchResult);
    }

    public int store(String text) {
        // 将文本分割成文本段
        List<TextSegment> textSegments = makeTextSegments(text);
        log.info("将文本分割成文本段得到{}个文本段", textSegments.size());

        // 将文本批量向量化
        List<Embedding> embeddings = makeEmbeddings(textSegments);
        log.info("将文本批量向量化得到{}个Embedding", embeddings.size());

        // 批量存储到向量数据库
        return storeEmbeddings(embeddings, textSegments);
    }

    // 将批量文档分割成文本段
    private List<TextSegment> makeTextSegments(String resource) {
        Document document = Document.from(resource);
        DocumentSplitter splitter = DocumentSplitters.recursive(150,10);
        List<TextSegment>  textSegments = splitter.split(document);
        return textSegments;
    }

    // 将文本向量化
    private Embedding makeEmbedding(String text) {
        Response<Embedding> response = embeddingModel.embed(text);
        return response.content();
    }

    // 将文本批量向量化
    private List<Embedding> makeEmbeddings(List<TextSegment> textSegments) {
        Response<List<Embedding>> response = embeddingModel.embedAll(textSegments);
        return response.content();
    }


    // 批量存储到向量数据库
    private int storeEmbeddings(List<Embedding> embeddings, List<TextSegment> textSegments) {
        List<String> embeddingIds = embeddingStore.addAll(embeddings);
        for(int i=0; i<embeddingIds.size(); i++) {
            embeddeds.put(embeddingIds.get(i), textSegments.get(i).text());
        }
        return embeddingIds.size();
    }

    // 做搜索获取embeddingId，再根据map里的映射获取原文本
    private String search(String text) {
        // 检索条件也要向量化
        Embedding embedding = makeEmbedding(text);
        // 构造搜索条件
        EmbeddingSearchRequest embeddingSearchRequest = EmbeddingSearchRequest.builder().queryEmbedding(embedding).maxResults(1).build();
        // 在数据库中进行检索
        EmbeddingSearchResult<TextSegment> embeddedEmbeddingSearchResult = embeddingStore.search(embeddingSearchRequest);
        // 获取结果中匹配的结果
        List<EmbeddingMatch<TextSegment>> embeddingMatcheList = embeddedEmbeddingSearchResult.matches();

        //选择最匹配的一条返回（可以设置返回条件，返回的数据相关性越往后越弱）
        EmbeddingMatch<TextSegment> embeddingMatch = embeddingMatcheList.get(0);
        return embeddeds.get(embeddingMatch.embeddingId());
    }

    // 将问题和检索结果合并成提示词，增强后调用大模型返回结果。
    private String callAi(String question, String searchResult) {
        // 构造提示词
        PromptTemplate promptTemplate = PromptTemplate.from("基于如下信息进行回答:\n" +
                "{{context}}\n" +
                "提问:\n" +
                "{{question}}");
        Map<String, Object> variables = new HashMap<>();
        variables.put("context", searchResult);
        variables.put("question", question);
        Prompt prompt = promptTemplate.apply(variables);

        // 返回大模型结果
        return chatModel.generate(prompt.text());
    }
}
