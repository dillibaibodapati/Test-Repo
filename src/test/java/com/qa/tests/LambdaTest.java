package com.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.qa.pageobjects.SimpleFormDemo;
import org.qa.testbase.BaseClass;
//import org.qa.utilities.Xls_Reader;
import org.qa.utilities.TestUtilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;
import java.io.IOException;

public class LambdaTest extends BaseClass {

    SimpleFormDemo simpleform;
    TestUtilities utils;


    @BeforeClass
    public void methodName() {

        init();
        setup();
        driver.get("https://www.lambdatest.com/selenium-playground/");
        utils = new TestUtilities();
        simpleform = new SimpleFormDemo(driver);

    }

    @Test
    public void Scenario1(){

//        simpleform.simpleformdemo().click();
        utils.clickonforStale(driver, simpleform.simpleformdemo(), 40);
        String var = "Welcome to Lambda Test";
        simpleform.enterMessage().sendKeys(var);
        simpleform.checkedValue().click();
        Assert.assertEquals(simpleform.message().getText(), var);

    }

    @Test
    public void Scenario2(){

        driver.get("https://www.lambdatest.com/selenium-playground/");
        utils.explicitWaitinit(driver, 40);
        utils.clickonforStale(driver, simpleform.dragAndDrop(), 40);
//        simpleform.dragAndDrop().click();
        Actions action = new Actions(driver);
        Dimension sliderSize = simpleform.source().getSize();
        int sliderWidth = sliderSize.getWidth();
        int xCoord = simpleform.source().getLocation().getX();

//        action.moveToElement(simpleform.source())
//                .click()
//                .dragAndDropBy(simpleform.source(), xCoord+sliderWidth, -15)
//                .build()
//                .perform();
        for (int i = 1; i <= 80 ; i++) {
            simpleform.source().sendKeys(Keys.ARROW_RIGHT);
        }
        Assert.assertEquals(simpleform.getTarget().getText(), "95");

    }

    @Test
    public void Scenario3(){

        driver.get("https://www.lambdatest.com/selenium-playground/");

        simpleform.FillSimpleForm();
        Assert.assertEquals(simpleform.getSuccessmsg().getText(), "Thanks for contacting us, we will get back to you shortly.");

    }

    @AfterMethod
    public void endtest(ITestResult result) throws IOException {
        tearDown(result);
    }

    @AfterClass
    public void endTest() {
        EndTest();
        endReport();
    }
}
