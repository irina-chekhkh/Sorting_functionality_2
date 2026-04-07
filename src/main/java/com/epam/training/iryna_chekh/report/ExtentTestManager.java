package com.epam.training.iryna_chekh.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;



public class ExtentTestManager {
    private static final ExtentReports extent = ExtentReportManager.getExtentReport();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static synchronized void createTest(String testName) {
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
    }

    public static synchronized void log(Status status, String details) {
        test.get().log(status, details);
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void flushReport() {
        extent.flush();
    }

}
