package com.epam.training.iryna_chekh;

import com.aventstack.extentreports.Status;
import com.epam.training.iryna_chekh.driver.Driver;
import com.epam.training.iryna_chekh.page.LoginPage;
import com.epam.training.iryna_chekh.page.ProductsPage;
import com.epam.training.iryna_chekh.user.User;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;


public class SortingTest {
    private Driver driver;
    private User currentUser = UserGenerator.createUser();


    @Parameters("browser")
    @BeforeMethod
    public void initDriver(String browser) {
        driver = new Driver();
        driver.setDriver(browser);

    }

    private ProductsPage login(User user) {
        return new LoginPage(driver)
                .openPage()
                .enterUserNameAndPassword(currentUser)
                .login();
    }

    @Test
    public void shouldSortTitlesAscending() {
        ExtentTestManager.createTest("Log in user and sort products by their titles ascending "+driver);
        List<String> actualNames = login(currentUser)
                .sortElements(SortingParameter.TITLE_ASC)
                .getProductsNames();
        List<String> expectedNames = actualNames.stream().sorted().collect(Collectors.toList());

        assertEquals(actualNames, expectedNames);
    }

    @Test
    public void shouldSortTitlesDescending() {
        ExtentTestManager.createTest("Log in user and sort products by their titles descending "+driver);
        List<String> actualNames = login(currentUser)
                .sortElements(SortingParameter.TITLE_DES)
                .getProductsNames();
        List<String> expectedNames = actualNames.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        assertEquals(actualNames, expectedNames);
    }

    @Test
    public void shouldSortPricesAscending() {
        ExtentTestManager.createTest("Log in user and sort products by their prices ascending "+driver);

        List<Double> actualPrices = login(currentUser)
                .sortElements(SortingParameter.PRICE_ASC)
                .getProductsPrice();
        List<Double> expectedPrices = actualPrices.stream().sorted().collect(Collectors.toList());
        assertEquals(actualPrices, expectedPrices);
    }

    @Test
    public void shouldSortPricesDescending() {
        ExtentTestManager.createTest("Log in user and sort products by their prices descending "+driver);
        List<Double> actualPrices = login(currentUser)
                .sortElements(SortingParameter.PRICE_DES)
                .getProductsPrice();
        List<Double> expectedPrices = actualPrices.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        assertEquals(actualPrices, expectedPrices);
    }


    @AfterMethod
    public void logResult(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentTestManager.log(Status.PASS, "Тест прошёл успешно");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            ExtentTestManager.log(Status.FAIL, "Тест упал: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentTestManager.log(Status.SKIP, "Тест пропущен: " + result.getThrowable());
        }
    }

    @AfterMethod
    public void closeDriver(){
        driver.closeDriver();
    }

    @AfterSuite
    public void generateReport(){
        ExtentTestManager.flushReport();
    }

}
