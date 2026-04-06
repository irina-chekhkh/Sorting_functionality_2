package com.epam.training.iryna_chekh;

import com.aventstack.extentreports.Status;
import com.epam.training.iryna_chekh.report.ExtentTestManager;
import org.testng.ITestResult;


public class TestResultLogger {

    public static void logResult(ITestResult result) {
        Status status;
        String details;

        if (result.getStatus() == ITestResult.SUCCESS) {
            status = Status.PASS;
            details = "The test was successful";
        } else {
            Throwable throwable = result.getThrowable();
            String message = (throwable != null) ? throwable.getMessage() : "Unknown error";

            if (result.getStatus() == ITestResult.FAILURE) {
                status = Status.FAIL;
                details = "The test failed: " + message;
            } else {
                status = Status.SKIP;
                details = "The test skipped: " + message;
            }
        }
        ExtentTestManager.log(status, details);
    }

}
