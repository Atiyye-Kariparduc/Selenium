package com.myfirstproject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Day12_StaleElementReferenceException {
    WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void staleElementTest(){
        driver.get("https://techproeducation.com/");
        driver.navigate().refresh();//REFRESHING
        WebElement lmsLoginLink = driver.findElement(By.linkText("LMS Login"));
        lmsLoginLink.click(); //WORKS
    }
    @Test
    public void staleElementTest1(){
        driver.get("https://techproeducation.com/");
        WebElement lmsLoginLink = driver.findElement(By.linkText("LMS Login"));
        driver.navigate().refresh();//REFRESHING
        lmsLoginLink.click(); //StaleElementReferenceException lmsLoginLink is available but no longer usable(not freshh)
    }
    @Test
    public void staleElementTest2(){
        driver.get("https://techproeducation.com/");
        WebElement lmsLoginLink = driver.findElement(By.linkText("LMS Login"));
        lmsLoginLink.click();
        driver.navigate().back();//takes us back to home page
        lmsLoginLink.click();//StaleElementReferenceException cause the element is no longer fresh
    }
    @Test
    public void staleElementTest3(){
        driver.get("https://techproeducation.com/");
        WebElement lmsLoginLink = driver.findElement(By.linkText("LMS Login"));
        lmsLoginLink.click();

        driver.navigate().back();
        lmsLoginLink=driver.findElement(By.linkText("LMS Login"));//RELOCATION THE ELEMENT
        lmsLoginLink.click();

        driver.navigate().back();//takes us back to home page
        WebElement lmsLoginLink1 = driver.findElement(By.linkText("LMS Login"));//RELOCATION THE ELEMENT
        lmsLoginLink1.click();//No Issue

        driver.navigate().back();//back to the home page
        driver.findElement(By.linkText("LMS Login")).click();// RELOCATION THE ELEMENT
    }

    }

