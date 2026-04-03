package com.epam.training.iryna_chekh.page;

import com.epam.training.iryna_chekh.driver.SingletonDriver;
import com.epam.training.iryna_chekh.user.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends AbstractPage {

    @FindBy(css = "input[id=login-button]")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public LoginPage openPage(){
        driver.get("https://www.saucedemo.com/");
        return this;
    }

    public LoginPage enterUserNameAndPassword(User user){
        WebElement userNameInput = new  WebDriverWait(driver, DURATION).until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id=user-name]")));
        userNameInput.sendKeys(user.getUserName());
        WebElement userPasswordInput = new  WebDriverWait(driver, DURATION).until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id=password]")));
        userPasswordInput.sendKeys(user.getUserPassword());
        return this;
    }

    public ProductsPage login(){
        loginButton.click();
        return new ProductsPage(driver);
    }
}
