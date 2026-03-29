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
            status = result.getStatus() == ITestResult.FAILURE ? Status.FAIL : Status.SKIP;
            String testType = result.getStatus() == ITestResult.FAILURE ? "The test failed:" : "The test skip:";
            details = testType + message;
        }
        ExtentTestManager.log(status, details);
    }

}
