package com.epam;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;
import java.util.List;

/**
 * Created by User on 11/7/2016.
 */
public class AdminTestRedundant {
    @Test
    public void login() throws InterruptedException {
        String path ="E:\\chromedriver_win32\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",path);
        WebDriver webDriver=new ChromeDriver();
        webDriver.get("http://localhost:8081");
        System.out.println(webDriver.getTitle());
        WebElement link=webDriver.findElement(By.linkText("news"));
        link.click();
        WebElement userInput=webDriver.findElement(By.id("username"));
        userInput.sendKeys("login3");
        WebElement passwordInput= webDriver.findElement(By.id("password"));
        passwordInput.sendKeys("pass3");
        webDriver.findElement(By.name("submit")).click();
        assertTrue(webDriver.findElement(By.tagName("h2")).getText().equals("News")&&webDriver.findElement(By.tagName("a")).getText().equals("unbelievable"));
        webDriver.quit();
    }
    @Test
    public void logout()throws InterruptedException{
        String path ="E:\\chromedriver_win32\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        WebDriver webDriver=new ChromeDriver();
        webDriver.get("http://localhost:8081");
        WebElement link=webDriver.findElement(By.linkText("news"));
        link.click();
        WebElement userInput=webDriver.findElement(By.id("username"));
        userInput.sendKeys("login3");
        WebElement passwordInput= webDriver.findElement(By.id("password"));
        passwordInput.sendKeys("pass3");
        webDriver.findElement(By.name("submit")).click();
        webDriver.findElement(By.xpath("//input[@name='submit' and @value='Exit']")).submit();
        assertTrue(webDriver.getTitle().equals("Login page"));
        webDriver.quit();
    }

    @Test
    public void invalidLogin() throws InterruptedException {
        String path ="E:\\chromedriver_win32\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",path);
        WebDriver webDriver=new ChromeDriver();
        webDriver.get("http://localhost:8081");
        System.out.println(webDriver.getTitle());
        WebElement link=webDriver.findElement(By.linkText("news"));
        link.click();
        WebElement userInput=webDriver.findElement(By.id("username"));
        userInput.sendKeys("asdfg1");
        WebElement passwordInput= webDriver.findElement(By.id("password"));
        passwordInput.sendKeys("asdfg1");
        webDriver.findElement(By.name("submit")).click();
        assertTrue(webDriver.getTitle().equals("Login page"));
        webDriver.quit();
    }
    @Test
    public void firstNewsPieceDetails() throws InterruptedException {
        String path ="E:\\chromedriver_win32\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",path);
        WebDriver webDriver=new ChromeDriver();
        webDriver.get("http://localhost:8081");
        WebElement link=webDriver.findElement(By.linkText("news"));
        link.click();
        WebElement userInput=webDriver.findElement(By.id("username"));
        userInput.sendKeys("login3");
        WebElement passwordInput= webDriver.findElement(By.id("password"));
        passwordInput.sendKeys("pass3");
        webDriver.findElement(By.name("submit")).click();
        WebElement newsLink = webDriver.findElement(By.linkText("unbelievable"));
        newsLink.click();
        assertTrue(webDriver.findElement(By.tagName("p")).getText().trim().equals("unbelievable"));
        webDriver.quit();
    }
}
