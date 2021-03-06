/*
 * This file was automatically generated by EvoSuite
 * Thu Sep 10 07:30:25 GMT 2020
 */

package com.brmayi.yuna.controller;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.brmayi.yuna.controller.JacksonController;
import com.brmayi.yuna.model.Pojo;
import java.io.IOException;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class JacksonController_ESTest extends JacksonController_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      JacksonController jacksonController0 = new JacksonController();
      Pojo pojo0 = jacksonController0.readPojoValue("{}UbuU:Ybx");
      assertEquals(0, pojo0.getId());
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      JacksonController jacksonController0 = new JacksonController();
      try { 
        jacksonController0.readPojoValue((String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.fasterxml.jackson.core.JsonFactory", e);
      }
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      JacksonController jacksonController0 = new JacksonController();
      try { 
        jacksonController0.readPojoValue("");
        fail("Expecting exception: IOException");
      
      } catch(IOException e) {
         //
         // No content to map due to end-of-input
         //  at [Source: (String)\"\"; line: 1, column: 0]
         //
         verifyException("com.fasterxml.jackson.databind.exc.MismatchedInputException", e);
      }
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      JacksonController jacksonController0 = new JacksonController();
      try { 
        jacksonController0.readPojoValue("Y{7<Yj!Qy)0");
        fail("Expecting exception: IOException");
      
      } catch(IOException e) {
         //
         // Unrecognized token 'Y': was expecting ('true', 'false' or 'null')
         //  at [Source: (String)\"Y{7<Yj!Qy)0\"; line: 1, column: 2]
         //
         verifyException("com.fasterxml.jackson.core.JsonParser", e);
      }
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      JacksonController jacksonController0 = new JacksonController();
      try { 
        jacksonController0.readPojoValue("-");
        fail("Expecting exception: IOException");
      
      } catch(IOException e) {
         //
         // Unexpected end-of-inputNo digit following minus sign
         //  at [Source: (String)\"-\"; line: 1, column: 3]
         //
         verifyException("com.fasterxml.jackson.core.base.ParserMinimalBase", e);
      }
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      JacksonController jacksonController0 = new JacksonController();
      Pojo pojo0 = jacksonController0.readPojoValue("null");
      assertNull(pojo0);
  }

  @Test(timeout = 4000)
  public void test6()  throws Throwable  {
      JacksonController jacksonController0 = new JacksonController();
      String string0 = jacksonController0.writeStringAsString(")");
      assertEquals("\")\"", string0);
  }

  @Test(timeout = 4000)
  public void test7()  throws Throwable  {
      JacksonController jacksonController0 = new JacksonController();
      String string0 = jacksonController0.writePojoAsString((Pojo) null);
      assertEquals("null", string0);
  }
}
