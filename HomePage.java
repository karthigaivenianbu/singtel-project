package com.example.ui.pages;

import com.example.ui.helper.WaitHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.testng.log4testng.Logger;
import java.util.List;


public class HomePage extends BasePage {
    private static Logger log = Logger.getLogger(HomePage.class);
    public WebDriver driver;
    WaitHelper wait;
    private static HomePage Homepage_instance = null;
    String todotext = "Set Morning meeting alert at 9 clock";


    SoftAssert soft = new SoftAssert();

    public HomePage(WebDriver webdriver) {
        driver = webdriver;
        wait = new WaitHelper();
        PageFactory.initElements(driver, this);
    }

    public HomePage() {
    }

    public static HomePage getInstance(WebDriver driver) {

        if (Homepage_instance == null)
            Homepage_instance = new HomePage(driver);
        return Homepage_instance;
    }

    public void verifyHomePage() {

        soft.assertTrue(driver.getCurrentUrl().contains("examples"));
        soft.assertEquals(HomeHeader.getText(), "todos");
    }

    public void search() throws InterruptedException {
        Searchbox.sendKeys(todotext);
        Searchbox.sendKeys(Keys.ENTER);
    }

    public void verifyList() {
        toDoList(todotext);
    }

    public void toDoList(String text) {
        WebElement task = driver.findElement(By.cssSelector("section.main>ul"));
        List<WebElement> links = task.findElements(By.tagName("li"));
        for (int i = 1; i < links.size(); i++) {
            String listtext = links.get(i).getText();
            Assert.assertEquals(listtext, text);
        }

    }

    public void actionTab(String actionList) {
        driver.findElement(By.xpath("//a[contains(@href,'" + actionList + "')]")).click();
    }

    public void itemCount(int count) {
        String itemleft = Count.getText();
        soft.assertEquals(itemleft, "" + count + " item left");
    }

    public void checkbox() {
        itemcheckbox.click();
    }

    public void Compeletedchebox() {
        Assert.assertTrue(completedcheckbox.isDisplayed());

    }
}