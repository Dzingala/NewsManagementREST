package com.epam.ta.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.ta.utils.Utils;

public class DetailedNewsPieceInfoPage extends AbstractPage
{
	private final String BASE_URL = "http://www.localhost:8080/news/view/1";
	private final Logger logger = LogManager.getRootLogger();


	@FindBy(xpath = "//form[@id='new_repository']//button[@type='submit']")
	private WebElement butttonAdd;


	@FindBy(xpath = "//a[@data-pjax='#js-repo-pjax-container']")
	private WebElement linkCurrentRepository;

	public DetailedNewsPieceInfoPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public String getCurrentPageTitle(){
		return driver.getTitle();
	}



	public String getCurrentRepositoryName()
	{
		return linkCurrentRepository.getText();
	}

	@Override
	public void openPage()
	{
		driver.navigate().to(BASE_URL);
	}

}
