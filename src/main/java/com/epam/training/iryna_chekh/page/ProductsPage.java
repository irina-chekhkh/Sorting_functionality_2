package com.epam.training.iryna_chekh.page;

import com.aventstack.extentreports.Status;
import com.epam.training.iryna_chekh.SortingParameter;
import com.epam.training.iryna_chekh.report.ExtentTestManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ProductsPage extends AbstractPage {
    private static final Logger LOGGER = Logger.getLogger(ProductsPage.class.getName());


    @FindBy(css = "div[data-test=inventory-item-name]")
    private List<WebElement> productNames;

    @FindBy(css = "div[data-test=inventory-item-price]")
    private List<WebElement> productPrices;

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private void sendInfoToLoggerAndReport(String description) {
        LOGGER.info(description);
        ExtentTestManager.log(Status.INFO, description);
    }

    public ProductsPage sortElements(SortingParameter sortingParameter) {
        WebElement sortDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("select[data-test='product-sort-container']")
                )
        );

        sendInfoToLoggerAndReport("Choosing sorting type:" + sortingParameter.name());
        new Select(sortDropdown).selectByValue(
                sortingParameter.getValue()
        );
        return this;
    }

    public List<String> getProductsNames() {
        sendInfoToLoggerAndReport("Getting products names");
        List<String> names = productNames.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        if (names.isEmpty()){
            throw new IllegalArgumentException("Array of names can't be empty");
        }
        return names;
    }

    public List<Double> getProductsPrice() {
        sendInfoToLoggerAndReport("Getting product prices");
        List<Double> prices = productPrices.stream()
                .map(e -> Double.parseDouble(
                                e.getText().replace("$", "")
                        )
                )
                .collect(Collectors.toList());
        if (prices.isEmpty()){
            throw new IllegalArgumentException("Array of prices can't be empty");
        }
        return prices;
    }
}
