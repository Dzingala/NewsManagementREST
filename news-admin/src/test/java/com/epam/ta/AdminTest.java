package com.epam.ta;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.ta.steps.Steps;

public class AdminTest
{
	private Steps steps;
	private final String USERNAME = "login3";
	private final String PASSWORD = "pass3";

	@BeforeMethod(description = "Init browser")
	public void setUp()
	{
		steps = new Steps();
		steps.initBrowser();
	}


	@Test(description = "Login to NewsApp")
	public void oneCanLoginNewsApp()
	{
		steps.login(USERNAME, PASSWORD);
		Assert.assertTrue(steps.isLoggedIn());
	}

	@Test(description = "Logout from NewsApp")
	public void oneCanLogoutNewsApp(){
		steps.login(USERNAME,PASSWORD);
		steps.logout();
		Assert.assertTrue(steps.isLoggedOut());
	}

	@Test(description="Test 1st news piece content")
	public void oneCanWatchDetailedNewsPiece(){
		steps.login(USERNAME,PASSWORD);
		steps.openFirstNewsPieceDetails();
		Assert.assertTrue(steps.checkFirstNewsPieceDetails());

	}

	@AfterMethod(description = "Stop Browser")
	public void stopBrowser()
	{
		steps.closeDriver();
	}

}
