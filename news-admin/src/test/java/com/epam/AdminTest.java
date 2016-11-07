package com.epam;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

/**
 * Created by User on 11/7/2016.
 */
public class AdminTest {
    @Test
    public void checkLogin() throws InterruptedException {
        String path ="E:\\chromedriver_win32\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",path);
        WebDriver webDriver=new ChromeDriver();
        webDriver.get("http://localhost:8081");
        System.out.println(webDriver.getTitle());
        webDriver.quit();
    }
}
