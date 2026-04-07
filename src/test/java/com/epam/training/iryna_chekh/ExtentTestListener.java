package com.epam.training.iryna_chekh;

import com.aventstack.extentreports.Status;
import com.epam.training.iryna_chekh.driver.SingletonDriver;
import com.epam.training.iryna_chekh.report.ExtentTestManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;



public class ExtentTestListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.log(Status.PASS, "The test was successful");
    }

    private void addScreenshort(String screenshortTitle){
        String base64Screenshot = ((TakesScreenshot) SingletonDriver.getDriver())
                .getScreenshotAs(OutputType.BASE64);
        ExtentTestManager.getTest()
                .addScreenCaptureFromBase64String(base64Screenshot, screenshortTitle);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTestManager.log(Status.FAIL, result.getThrowable().getMessage());
        addScreenshort("Fail Test");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.log(Status.SKIP, result.getThrowable().getMessage());
        addScreenshort("Skip Test");
    }

}
