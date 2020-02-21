/*******************************************************************************
 * Copyright (c) 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.install.featureUtility.fat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

import com.ibm.websphere.simplicity.ProgramOutput;
import com.ibm.websphere.simplicity.log.Log;
import com.ibm.websphere.simplicity.OperatingSystem;

public class SudoTest extends InstallUtilityToolTest{
    private static final Class<?> c = SudoTest.class;
    public static File openLib = new File("/var/lib/openliberty");
    public static boolean openLibExists = openLib.exists();
    @BeforeClass
    public static void beforeClassSetup() throws Exception {
        setupEnv();
        createServerEnv();

    }

    @AfterClass
    public static void cleanup() throws Exception {
       if (openLibExists) {
           if (isLinuxRhel()){
               final String METHOD_NAME = "cleanup";
               entering(c, METHOD_NAME);
               cleanupEnv();
               exiting(c, METHOD_NAME);
           }
           else {
               logger.info("This machine is not Rhel");
           }
       }
       else {
           logger.info("OpenLiberty did not install successfully");
       }
    }

    @Test
    public void testSUDOCommands() throws Exception {
        
            String METHOD_NAME = "testSUDOCommands";
            entering(c, METHOD_NAME);
            
            String[] param1s = { "ls" };
            logger.info("Running sudo comand1:");
            ProgramOutput po = runCommand(METHOD_NAME, "sudo", param1s);
            logger.info("stdout:"+po.getStdout());
            logger.info("stderr:"+po.getStderr());
            logger.info("rc:"+po.getReturnCode());
            
            logger.info("Running sudo comand2:");
            String[] param2s = { "-l" };
            ProgramOutput po2 = runCommand(METHOD_NAME, "sudo", param2s);
            logger.info("stdout:"+po2.getStdout());
            logger.info("stderr:"+po2.getStderr());
            logger.info("rc:"+po2.getReturnCode());
            
            
            assertEquals("Expected exit code", 0, po.getReturnCode());
            exiting(c, METHOD_NAME);
    }

}
