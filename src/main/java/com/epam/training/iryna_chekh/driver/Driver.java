package com.epam.training.iryna_chekh.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {
    private WebDriver driver;

    public Driver() {
    }

    public WebDriver getDriver(){
        return driver;
    }

    public void setDriver(String browser) {
        if (driver == null) {
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
            }
        }
        driver.manage().window().maximize();
    }

    public void  closeDriver(){
        if (driver != null) {
            driver.quit();
            driver=null;
        }
    }

    @Override
    public String toString() {
        return driver.toString();
    }
}
