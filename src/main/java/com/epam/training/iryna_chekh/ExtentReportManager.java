package com.epam.training.iryna_chekh;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static ExtentReports extentReport;

    public static ExtentReports getExtentReport() {
        if (extentReport==null){
            String reportPath = "reports\\report.html";
            ExtentSparkReporter report = new ExtentSparkReporter(reportPath);
            report.config().setReportName("Automation testing");
            report.config().setDocumentTitle("Test Results");
            report.config().enableOfflineMode(true);
            report.config().setTimeStampFormat("EEEE, MMMM dd yyyy hh:mm");
            report.config().setEncoding("UTF-8");

            extentReport = new ExtentReports();
            extentReport.attachReporter(report);
        }
        return extentReport;
    }
}
