/**
 * Scaffolding file used to store all the setups needed to run 
 * tests automatically generated by EvoSuite
 * Thu Sep 10 07:27:58 GMT 2020
 */

package com.brmayi.yuna.controller;

import org.evosuite.runtime.annotation.EvoSuiteClassExclude;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.junit.AfterClass;
import org.evosuite.runtime.sandbox.Sandbox;
import org.evosuite.runtime.sandbox.Sandbox.SandboxMode;

import static org.evosuite.shaded.org.mockito.Mockito.*;
@EvoSuiteClassExclude
public class HystrixController_ESTest_scaffolding {

  @org.junit.Rule 
  public org.evosuite.runtime.vnet.NonFunctionalRequirementRule nfr = new org.evosuite.runtime.vnet.NonFunctionalRequirementRule();

  private static final java.util.Properties defaultProperties = (java.util.Properties) java.lang.System.getProperties().clone(); 

  private org.evosuite.runtime.thread.ThreadStopper threadStopper =  new org.evosuite.runtime.thread.ThreadStopper (org.evosuite.runtime.thread.KillSwitchHandler.getInstance(), 3000);


  @BeforeClass 
  public static void initEvoSuiteFramework() { 
    org.evosuite.runtime.RuntimeSettings.className = "com.brmayi.yuna.controller.HystrixController"; 
    org.evosuite.runtime.GuiSupport.initialize(); 
    org.evosuite.runtime.RuntimeSettings.maxNumberOfThreads = 100; 
    org.evosuite.runtime.RuntimeSettings.maxNumberOfIterationsPerLoop = 10000; 
    org.evosuite.runtime.RuntimeSettings.mockSystemIn = true; 
    org.evosuite.runtime.RuntimeSettings.sandboxMode = org.evosuite.runtime.sandbox.Sandbox.SandboxMode.RECOMMENDED; 
    org.evosuite.runtime.sandbox.Sandbox.initializeSecurityManagerForSUT(); 
    org.evosuite.runtime.classhandling.JDKClassResetter.init();
    setSystemProperties();
    initializeClasses();
    org.evosuite.runtime.Runtime.getInstance().resetRuntime(); 
    try { initMocksToAvoidTimeoutsInTheTests(); } catch(ClassNotFoundException e) {} 
  } 

  @AfterClass 
  public static void clearEvoSuiteFramework(){ 
    Sandbox.resetDefaultSecurityManager(); 
    java.lang.System.setProperties((java.util.Properties) defaultProperties.clone()); 
  } 

  @Before 
  public void initTestCase(){ 
    threadStopper.storeCurrentThreads();
    threadStopper.startRecordingTime();
    org.evosuite.runtime.jvm.ShutdownHookHandler.getInstance().initHandler(); 
    org.evosuite.runtime.sandbox.Sandbox.goingToExecuteSUTCode(); 
    setSystemProperties(); 
    org.evosuite.runtime.GuiSupport.setHeadless(); 
    org.evosuite.runtime.Runtime.getInstance().resetRuntime(); 
    org.evosuite.runtime.agent.InstrumentingAgent.activate(); 
  } 

  @After 
  public void doneWithTestCase(){ 
    threadStopper.killAndJoinClientThreads();
    org.evosuite.runtime.jvm.ShutdownHookHandler.getInstance().safeExecuteAddedHooks(); 
    org.evosuite.runtime.classhandling.JDKClassResetter.reset(); 
    resetClasses(); 
    org.evosuite.runtime.sandbox.Sandbox.doneWithExecutingSUTCode(); 
    org.evosuite.runtime.agent.InstrumentingAgent.deactivate(); 
    org.evosuite.runtime.GuiSupport.restoreHeadlessMode(); 
  } 

  public static void setSystemProperties() {
 
    java.lang.System.setProperties((java.util.Properties) defaultProperties.clone()); 
    java.lang.System.setProperty("file.encoding", "GBK"); 
    java.lang.System.setProperty("java.awt.headless", "true"); 
    java.lang.System.setProperty("java.io.tmpdir", "C:\\Users\\XIEXIA~1\\AppData\\Local\\Temp\\"); 
    java.lang.System.setProperty("user.country", "CN"); 
    java.lang.System.setProperty("user.dir", "D:\\work\\yuna"); 
    java.lang.System.setProperty("user.home", "C:\\Users\\xiexiaojing"); 
    java.lang.System.setProperty("user.language", "zh"); 
    java.lang.System.setProperty("user.name", "xiexiaojing"); 
    java.lang.System.setProperty("user.timezone", "Asia/Shanghai"); 
  }

  private static void initializeClasses() {
    org.evosuite.runtime.classhandling.ClassStateSupport.initializeClasses(HystrixController_ESTest_scaffolding.class.getClassLoader() ,
      "org.springframework.beans.factory.HierarchicalBeanFactory",
      "com.netflix.hystrix.HystrixThreadPoolKey",
      "org.springframework.context.NoSuchMessageException",
      "com.netflix.hystrix.HystrixCommandMetrics",
      "com.netflix.hystrix.HystrixCollapserKey",
      "com.netflix.hystrix.HystrixInvokableInfo",
      "com.brmayi.yuna.util.DemoHystrixCommand",
      "org.springframework.beans.factory.annotation.Autowired",
      "com.netflix.hystrix.exception.HystrixRuntimeException",
      "rx.functions.Action1",
      "com.netflix.hystrix.AbstractCommand$TryableSemaphore",
      "com.netflix.hystrix.HystrixCommand$Setter",
      "com.netflix.hystrix.HystrixCircuitBreaker",
      "org.springframework.core.env.PropertyResolver",
      "org.springframework.context.ApplicationEventPublisher",
      "com.netflix.hystrix.strategy.properties.HystrixPropertiesStrategy",
      "org.springframework.core.ResolvableType",
      "com.netflix.hystrix.ExecutionResult$1",
      "org.springframework.context.MessageSourceResolvable",
      "org.springframework.core.io.Resource",
      "org.springframework.core.NestedRuntimeException",
      "org.springframework.core.io.ResourceLoader",
      "com.netflix.hystrix.HystrixEventType",
      "com.netflix.hystrix.HystrixCommandKey",
      "com.netflix.hystrix.HystrixCachedObservable",
      "com.netflix.hystrix.HystrixThreadPoolProperties$Setter",
      "rx.Observer",
      "org.springframework.web.bind.annotation.RestController",
      "com.brmayi.yuna.controller.HystrixController",
      "com.netflix.hystrix.strategy.concurrency.HystrixRequestContext",
      "com.netflix.hystrix.HystrixCommand",
      "com.netflix.hystrix.exception.HystrixRuntimeException$FailureType",
      "rx.functions.Action0",
      "org.springframework.core.env.Environment",
      "org.springframework.core.io.InputStreamSource",
      "com.netflix.hystrix.ExecutionResult",
      "com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook",
      "com.netflix.hystrix.HystrixInvokable",
      "com.netflix.hystrix.HystrixKey",
      "org.springframework.beans.factory.ListableBeanFactory",
      "com.netflix.hystrix.AbstractCommand$ExecutionHookDeprecationWrapper",
      "com.netflix.hystrix.HystrixRequestLog",
      "com.netflix.hystrix.HystrixCommandProperties",
      "org.springframework.beans.factory.config.AutowireCapableBeanFactory",
      "com.netflix.hystrix.HystrixCommandResponseFromCache",
      "org.springframework.stereotype.Controller",
      "com.netflix.hystrix.ExecutionResult$EventCounts",
      "rx.functions.Function",
      "org.springframework.context.annotation.Scope",
      "rx.Observable",
      "rx.functions.Func0",
      "org.springframework.beans.factory.ObjectProvider",
      "rx.functions.Func1",
      "com.netflix.hystrix.HystrixThreadPool",
      "org.springframework.core.env.EnvironmentCapable",
      "com.netflix.hystrix.AbstractCommand",
      "org.springframework.lang.Nullable",
      "org.springframework.stereotype.Indexed",
      "com.netflix.hystrix.exception.HystrixBadRequestException",
      "org.springframework.context.MessageSource",
      "com.netflix.hystrix.HystrixCommandGroupKey",
      "org.springframework.context.annotation.ScopedProxyMode",
      "org.springframework.beans.BeansException",
      "org.springframework.web.bind.annotation.ResponseBody",
      "org.springframework.context.ApplicationContext",
      "org.springframework.context.ApplicationEvent",
      "org.springframework.beans.factory.ObjectFactory",
      "rx.Observable$Operator",
      "org.springframework.stereotype.Component",
      "com.netflix.hystrix.HystrixObservable",
      "com.netflix.hystrix.HystrixMetrics",
      "org.springframework.beans.factory.BeanFactory",
      "org.springframework.beans.factory.NoSuchBeanDefinitionException",
      "com.netflix.hystrix.HystrixExecutable",
      "com.netflix.hystrix.HystrixCommandProperties$Setter",
      "com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy",
      "rx.functions.Action",
      "org.springframework.core.io.support.ResourcePatternResolver"
    );
  } 
  private static void initMocksToAvoidTimeoutsInTheTests() throws ClassNotFoundException { 
    mock(Class.forName("com.brmayi.yuna.util.DemoHystrixCommand", false, HystrixController_ESTest_scaffolding.class.getClassLoader()));
    mock(Class.forName("org.springframework.context.ApplicationContext", false, HystrixController_ESTest_scaffolding.class.getClassLoader()));
  }

  private static void resetClasses() {
    org.evosuite.runtime.classhandling.ClassResetter.getInstance().setClassLoader(HystrixController_ESTest_scaffolding.class.getClassLoader()); 

    org.evosuite.runtime.classhandling.ClassStateSupport.resetClasses(
      "com.brmayi.yuna.controller.HystrixController",
      "org.springframework.context.annotation.ScopedProxyMode",
      "com.netflix.hystrix.AbstractCommand",
      "com.netflix.hystrix.HystrixCommand",
      "com.brmayi.yuna.util.DemoHystrixCommand"
    );
  }
}
