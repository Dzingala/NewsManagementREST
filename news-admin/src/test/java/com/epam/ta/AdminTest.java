package com.epam.ta;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.ta.steps.Steps;

public class AdminTest {
    private Steps steps;
    private final String USERNAME = "login3";
    private final String PASSWORD = "pass3";
    private final String USERNAME_INVALID = "asdfg1";
    private final String PASSWORD_INVALID = "asdfg1";
    private final String COMMENT="comment";

    @BeforeMethod(description = "Init browser")
    public void setUp() {
        steps = new Steps();
        steps.initBrowser();
    }


    @Test(description = "Login to NewsApp")
    public void oneCanLoginNewsApp() {
        steps.login(USERNAME, PASSWORD);
        Assert.assertTrue(steps.isLoggedIn());
    }

    @Test(description = "Logout from NewsApp")
    public void oneCanLogoutNewsApp() {
        steps.login(USERNAME, PASSWORD);
        steps.logout();
        Assert.assertTrue(steps.isLoggedOut());
    }

    @Test(description = "Test 1st news piece content")
    public void oneCanWatchDetailedNewsPiece() {
        steps.login(USERNAME, PASSWORD);
        steps.openFirstNewsPieceDetails();
        Assert.assertTrue(steps.checkFirstNewsPieceDetails());

    }

    @Test(description = "Test app login using nonexistent credentials")
    public void oneCannotLoginUsingWrongCredentials() {
        steps.login(USERNAME_INVALID, PASSWORD_INVALID);
        Assert.assertTrue(steps.isLoggedOut());
    }

    @Test(description = "Test main page news count")//We expect at least 4 news on the main page in any case.
    public void oneCanSeeNews() {
        steps.login(USERNAME, PASSWORD);
        Assert.assertTrue(steps.checkMainPageNewsCount());
    }

    @Test(description= "Comment 1st news piece")//It's fixed that the 1st piece of news always exist
    public void oneCanSeeJacksNews(){
        steps.login(USERNAME,PASSWORD);
        steps.openFirstNewsPieceDetails();
        steps.commentFirstNewsPiece(COMMENT);
        Assert.assertTrue(steps.doesFirstCommentExistAndActual());
    }

    @AfterMethod(description = "Stop Browser")
    public void stopBrowser() {
        steps.closeDriver();
    }

}
