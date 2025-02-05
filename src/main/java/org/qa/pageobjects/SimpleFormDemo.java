package org.qa.pageobjects;

import com.google.j2objc.annotations.Weak;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.qa.testbase.BaseClass;
import org.qa.utilities.TestUtilities;
import org.testng.Assert;

import javax.swing.text.Utilities;

public class SimpleFormDemo extends BaseClass {

    TestUtilities utils;

    public SimpleFormDemo(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        utils = new TestUtilities();
    }

    @FindBy(xpath = "//a[text()='Simple Form Demo']")
    WebElement simpleformdemo;

    @FindBy(xpath = "//input[@id='user-message']")
    WebElement entermessage;

    @FindBy(id = "showInput")
    WebElement checkedvalue;

    @FindBy(xpath = "//p[@id='message']")
    WebElement message;

    @FindBy(xpath = "//a[text()=\"Drag & Drop Sliders\"]")
    WebElement dragdropsliders;

    @FindBy(xpath = "//input[@value=\"15\"]")
    WebElement source;

    @FindBy(xpath = "//div[@id=\"slider3\"]/div/output")
    WebElement target;

    @FindBy(xpath = "//a[text()='Input Form Submit']")
    WebElement inputform;

    @FindBy(xpath = "//button[text()='Submit']")
    WebElement submit;

    @FindBy(name = "name")
    WebElement name;

    @FindBy(xpath = "//input[@id='inputEmail4']")
    WebElement email;

    @FindBy(xpath = "//input[@id='inputPassword4']")
    WebElement password;

    @FindBy(xpath = "//input[@id='company']")
    WebElement company;

    @FindBy(xpath = "//input[@id='websitename']")
    WebElement websitename;

    @FindBy(xpath = "//input[@id='inputCity']")
    WebElement city;

    @FindBy(xpath = "//input[@id='inputAddress1']")
    WebElement address1;

    @FindBy(xpath = "//input[@id='inputAddress2']")
    WebElement address2;

    @FindBy(xpath = "//input[@id='inputState']")
    WebElement state;

    @FindBy(xpath = "//input[@id='inputZip']")
    WebElement zip;

    @FindBy(xpath = "//p[@class='success-msg hidden']")
    WebElement successmsg;

    public WebElement simpleformdemo(){
        return simpleformdemo;
    }

    public WebElement enterMessage(){
        return entermessage;
    }

    public WebElement checkedValue(){
        return checkedvalue;
    }

    public WebElement message(){
        return message;
    }

    public WebElement dragAndDrop(){
        return dragdropsliders;
    }

    public WebElement source(){
        return source;
    }

    public WebElement getTarget(){
        return target;
    }

    public WebElement getInputform(){
        return inputform;
    }

    public WebElement getSubmit(){
        return submit;
    }

    public WebElement getSuccessmsg(){
        return successmsg;
    }

    public void FillSimpleForm(){

        getInputform().click();
        utils.explicitWaitinit(driver, 40);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)");
        utils.visibilityofWebElement(submit, driver);
        utils.clickUsingJS(driver, submit, 40);
        name.sendKeys("name");
        email.sendKeys("email@gmail.com");
        password.sendKeys("passs1234");
        company.sendKeys("LAMBDA");
        websitename.sendKeys("www.lambda.com");
        utils.visibilityofWebElement(driver.findElement(By.xpath("//select[@name='country']")), driver);
        Select country = new Select(driver.findElement(By.xpath("//select[@name='country']")));
        country.selectByVisibleText("United States");
        city.sendKeys("Newyork");
        address1.sendKeys("add1");
        address2.sendKeys("add2");
        state.sendKeys("Newyork");
        zip.sendKeys("736544");
        submit.click();

    }



}
