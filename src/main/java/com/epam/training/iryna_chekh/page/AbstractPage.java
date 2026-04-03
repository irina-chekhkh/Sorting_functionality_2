package com.epam.training.iryna_chekh.page;

import org.openqa.selenium.WebDriver;

import java.time.Duration;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected Duration DURATION = Duration.ofSeconds(10);

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }
}
