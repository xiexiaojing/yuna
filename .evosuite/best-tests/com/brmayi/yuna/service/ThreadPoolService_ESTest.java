/*
 * This file was automatically generated by EvoSuite
 * Thu Sep 10 07:28:25 GMT 2020
 */

package com.brmayi.yuna.service;

import org.junit.Test;
import static org.junit.Assert.*;
import com.brmayi.yuna.service.ThreadPoolService;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class ThreadPoolService_ESTest extends ThreadPoolService_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      ThreadPoolService threadPoolService0 = new ThreadPoolService();
      threadPoolService0.createThreadPool();
  }
}
