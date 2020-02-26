package com.restassuredautomation.base;


import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterSuite;

import java.io.File;
import java.io.IOException;


public class BaseTestNG {

    @AfterSuite
    public void copyReport() {

        try {
            System.out.println(System.getProperty("user.dir"));
            String path = System.getProperty("user.dir");
            String reportPath = path + File.separator + "target" + File.separator + "surefire-reports" + File.separator + "ExtentReportsTestNG.html";

            File srcFile = new File(reportPath);
            if (srcFile.exists()) {
                String destResult = path + File.separator + "results" + File.separator + "ExtentReportsTestNG" + System.currentTimeMillis() + ".html";
//			System.out.println(a);
                FileUtils.copyFile(srcFile, new File(destResult));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
