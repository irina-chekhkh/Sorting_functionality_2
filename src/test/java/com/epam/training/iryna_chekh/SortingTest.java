package com.epam.training.iryna_chekh;

import com.epam.training.iryna_chekh.driver.DriverFactory;
import com.epam.training.iryna_chekh.driver.SingletonDriver;
import com.epam.training.iryna_chekh.page.LoginPage;
import com.epam.training.iryna_chekh.page.ProductsPage;
import com.epam.training.iryna_chekh.report.ExtentTestManager;
import com.epam.training.iryna_chekh.user.User;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;


import static org.assertj.core.api.Assertions.*;

@Listeners({com.epam.training.iryna_chekh.ExtentTestListener.class})
public class SortingTest {
    private final static User currentUser = UserGenerator.createUser();

    @Parameters("browser")
    @BeforeMethod
    public void initDriver(@Optional("nothing") String browser) {
        if (browser.equals("nothing")) {
            browser = System.getProperty("browser");
        }
        DriverFactory.createDriver(browser);
    }

    private ProductsPage login(User user) {
        return new LoginPage(SingletonDriver.getDriver())
                .openPage()
                .enterUserNameAndPassword(user)
                .login();
    }

    @Test(description = "Testing the functionality of sorting product names in A–Z order")
    public void shouldSortTitlesAscending() {
        ExtentTestManager.createTest("Log in user and sort products by their " +
                "titles ascending " + SingletonDriver.getDriver().toString());
        List<String> actualNames = login(currentUser)
                .sortElements(SortingParameter.TITLE_ASC)
                .getProductsNames();

        assertThat(actualNames)
                .describedAs("Product names should be sorted alphabetically")
                .isSortedAccordingTo(Comparator.naturalOrder());
    }

    @Test(description = "Testing the functionality of sorting product names in Z–A order")
    public void shouldSortTitlesDescending() {
        ExtentTestManager.createTest("Log in user and sort products by their titles descending "
                + SingletonDriver.getDriver().toString());

        List<String> actualNames = login(currentUser)
                .sortElements(SortingParameter.TITLE_DES)
                .getProductsNames();

        assertThat(actualNames)
                .describedAs("Product names should be sorted in reverse alphabetical order")
                .isSortedAccordingTo(Comparator.reverseOrder());
    }

    @Test(description = "Verification of product sorting by ascending price")
    public void shouldSortPricesAscending() {
        ExtentTestManager.createTest("Log in user and sort products by their prices ascending "
                + SingletonDriver.getDriver().toString());

        List<Double> actualPrices = login(currentUser)
                .sortElements(SortingParameter.PRICE_ASC)
                .getProductsPrice();

        assertThat(actualPrices)
                .describedAs("Product prices should be sorted in ascending order")
                .isSortedAccordingTo(Comparator.naturalOrder());
    }

    @Test(description = "Verification of product sorting by descending price")
    public void shouldSortPricesDescending() {
        ExtentTestManager.createTest("Log in user and sort products by their prices descending "
                + SingletonDriver.getDriver().toString());

        List<Double> actualPrices = login(currentUser)
                .sortElements(SortingParameter.PRICE_DES)
                .getProductsPrice();

        assertThat(actualPrices)
                .describedAs("Product prices should be sorted in descending order")
                .isSortedAccordingTo(Comparator.reverseOrder());
    }


    @AfterMethod
    public void logResultAndCloseDriver(ITestResult result) {
        SingletonDriver.closeDriver();
    }


    @AfterSuite
    public void generateReport() {
        ExtentTestManager.flushReport();
    }

}
