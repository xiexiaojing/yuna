package com.brmayi.yuna.solution;

public class WordDictionary {
    WordDictionary[] wd;
    boolean isEnd;

    public WordDictionary() {
        wd = new WordDictionary[26];
    }

    public void addWord(String word) {
        if (wd[word.charAt(0) - 'a'] == null) {
            wd[word.charAt(0) - 'a'] = new WordDictionary();
        }
        int len = word.length();
        if (len > 1) {
            wd[word.charAt(0) - 'a'].addWord(word.substring(1, len));
        } else {
            wd[word.charAt(0) - 'a'].isEnd = true;
        }
    }

    public boolean search(String word) {
        int len = word.length();
        if (word.charAt(0) == '.') {
            for (int i = 0; i < 26; i++) {
                if (wd[i] != null) {
                    if(len==1) {
                        return isEnd;
                    }
                    boolean subAns = wd[i].search(word.substring(1, len));
                    if (subAns) {
                        return true;
                    }
                }
            }
        } else if (wd[word.charAt(0) - 'a'] != null) {
            if(len==1) {
                return isEnd;
            }
            return wd[word.charAt(0) - 'a'].search(word.substring(1, len));
        }

        return false;
    }

    public static void main(String[] args) {
        WordDictionary wd = new WordDictionary();
        wd.addWord("bad");
        wd.addWord("mad");
        System.out.println(wd.search("bad"));
    }
}
