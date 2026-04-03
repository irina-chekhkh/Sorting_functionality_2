package com.epam.training.iryna_chekh.page;

import com.epam.training.iryna_chekh.driver.SingletonDriver;
import com.epam.training.iryna_chekh.SortingParameter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage extends AbstractPage{


    @FindBy(css="div[data-test=inventory-item-name]")
    List<WebElement> productNames;

    @FindBy(css="div[data-test=inventory-item-price]")
    List<WebElement> productPrices;

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public ProductsPage sortElements(SortingParameter sortingParameter){
        WebElement sortDropdown = new WebDriverWait(driver,DURATION).until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("select[data-test='product-sort-container']"))
        );
        new Select(sortDropdown).selectByValue(sortingParameter.getValue());
        return this;
    }

    public List<String> getProductsNames(){
        return productNames.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<Double> getProductsPrice(){
        return productPrices.stream()
                .map(e-> Double.parseDouble(e.getText().replace("$","")))
                .collect(Collectors.toList());
    }
}
