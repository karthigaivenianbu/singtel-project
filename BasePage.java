package com.example.ui.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class BasePage {

    @FindBy(xpath = "//h1") public WebElement HomeHeader;
    @FindBy(xpath = "//input[@class='new-todo']") public WebElement Searchbox;
    @FindBy(xpath="//span[@class='todo-count']") public WebElement Count;
    @FindBy(xpath = "//input[@class='toggle']") public WebElement itemcheckbox;
    @FindBy(xpath="//li[@class='todo completed']") public WebElement completedcheckbox;


    public void enter(WebElement element, String fieldName, String textToEnter) {
        clearTextUsingKeys(element);
        element.sendKeys(textToEnter.trim());
    }

    private void clearTextUsingKeys(WebElement element) {
        element.click();
        element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
    }

}
