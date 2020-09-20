/*
 * This file was automatically generated by EvoSuite
 * Thu Sep 10 07:27:58 GMT 2020
 */

package com.brmayi.yuna.controller;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.brmayi.yuna.controller.HystrixController;
import com.brmayi.yuna.util.DemoHystrixCommand;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.javaee.injection.Injector;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class HystrixController_ESTest extends HystrixController_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      HystrixController hystrixController0 = new HystrixController();
      DemoHystrixCommand demoHystrixCommand0 = mock(DemoHystrixCommand.class, new ViolatedAssumptionAnswer());
      doReturn((Object) null).when(demoHystrixCommand0).execute();
      ApplicationContext applicationContext0 = mock(ApplicationContext.class, new ViolatedAssumptionAnswer());
      doReturn(demoHystrixCommand0).when(applicationContext0).getBean(nullable(java.lang.Class.class));
      Injector.inject(hystrixController0, (Class<?>) HystrixController.class, "applicationContext", (Object) applicationContext0);
      Injector.validateBean(hystrixController0, (Class<?>) HystrixController.class);
      String string0 = hystrixController0.hystrix("Error calling HystrixCommandExecutionHook.onExecutionEmit");
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      HystrixController hystrixController0 = new HystrixController();
      DemoHystrixCommand demoHystrixCommand0 = mock(DemoHystrixCommand.class, new ViolatedAssumptionAnswer());
      doReturn("Error calling HystrixCommandExecutionHook.onCacheHit").when(demoHystrixCommand0).execute();
      ApplicationContext applicationContext0 = mock(ApplicationContext.class, new ViolatedAssumptionAnswer());
      doReturn(demoHystrixCommand0).when(applicationContext0).getBean(nullable(java.lang.Class.class));
      Injector.inject(hystrixController0, (Class<?>) HystrixController.class, "applicationContext", (Object) applicationContext0);
      Injector.validateBean(hystrixController0, (Class<?>) HystrixController.class);
      String string0 = hystrixController0.hystrix("Error calling HystrixCommandExecutionHook.onCacheHit");
      assertEquals("Error calling HystrixCommandExecutionHook.onCacheHit", string0);
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      HystrixController hystrixController0 = new HystrixController();
      DemoHystrixCommand demoHystrixCommand0 = mock(DemoHystrixCommand.class, new ViolatedAssumptionAnswer());
      doReturn("").when(demoHystrixCommand0).execute();
      ApplicationContext applicationContext0 = mock(ApplicationContext.class, new ViolatedAssumptionAnswer());
      doReturn(demoHystrixCommand0).when(applicationContext0).getBean(nullable(java.lang.Class.class));
      Injector.inject(hystrixController0, (Class<?>) HystrixController.class, "applicationContext", (Object) applicationContext0);
      Injector.validateBean(hystrixController0, (Class<?>) HystrixController.class);
      String string0 = hystrixController0.hystrix("");
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      HystrixController hystrixController0 = new HystrixController();
      ApplicationContext applicationContext0 = mock(ApplicationContext.class, new ViolatedAssumptionAnswer());
      doReturn((Object) null).when(applicationContext0).getBean(nullable(java.lang.Class.class));
      Injector.inject(hystrixController0, (Class<?>) HystrixController.class, "applicationContext", (Object) applicationContext0);
      Injector.validateBean(hystrixController0, (Class<?>) HystrixController.class);
      try { 
        hystrixController0.hystrix((String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.brmayi.yuna.controller.HystrixController", e);
      }
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      HystrixController hystrixController0 = new HystrixController();
      ApplicationContext applicationContext0 = mock(ApplicationContext.class, new ViolatedAssumptionAnswer());
      doReturn("4(").when(applicationContext0).getBean(nullable(java.lang.Class.class));
      Injector.inject(hystrixController0, (Class<?>) HystrixController.class, "applicationContext", (Object) applicationContext0);
      Injector.validateBean(hystrixController0, (Class<?>) HystrixController.class);
      try { 
        hystrixController0.hystrix("");
        fail("Expecting exception: ClassCastException");
      
      } catch(ClassCastException e) {
         //
         // java.lang.String cannot be cast to com.brmayi.yuna.util.DemoHystrixCommand
         //
         verifyException("com.brmayi.yuna.controller.HystrixController", e);
      }
  }
}