package com.epam.ta.steps;

import by.epam.lab.task.entity.News;
import com.epam.ta.driver.DriverSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.epam.ta.pages.DetailedNewsPieceInfoPage;
import com.epam.ta.pages.LoginPage;
import com.epam.ta.pages.MainPage;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Steps
{
	private WebDriver driver;

	private final Logger logger = LogManager.getRootLogger();

	public void initBrowser()
	{
		driver = DriverSingleton.getDriver();
	}

	public void closeDriver()
	{
		driver.quit();
	}

	public void login(String username, String password)
	{
		LoginPage loginPage = new LoginPage(driver);
		loginPage.openPage();
		loginPage.login(username, password);
	}
	public void logout(){
        MainPage mainPage=new MainPage(driver);
        mainPage.logout();
    }
	public boolean isLoggedIn()
	{
		LoginPage loginPage = new LoginPage(driver);
		return (loginPage.isLoggedInH2().trim().equals("News"));
	}
    public boolean isLoggedOut()
    {
        return driver.getTitle().equals("Login page");
    }

	public void openFirstNewsPieceDetails()
	{
		MainPage mainPage = new MainPage(driver);
		mainPage.clickOnNewsPieceLink();
	}
	public boolean checkMainPageNewsCount(){
		MainPage mainPage = new MainPage(driver);
		List<WebElement> newsRows = mainPage.getNewsElementsList();
		return newsRows.size()/7  >= 4;//7 "td" tags per piece of news, we expect news amount to be greater than or equal to 4
	}
    public boolean checkFirstNewsPieceDetails(){
        DetailedNewsPieceInfoPage detailedNewsPieceInfoPage = new DetailedNewsPieceInfoPage(driver);
        return detailedNewsPieceInfoPage.getCurrentPageTitle().equals("News specifically");
    }


}
