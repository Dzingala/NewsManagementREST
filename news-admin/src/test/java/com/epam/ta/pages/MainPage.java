package com.epam.ta.pages;

import by.epam.lab.task.entity.dto.NewsTO;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class MainPage extends AbstractPage
{
	private final String BASE_URL = "https://localhost:8080/news";

	@FindBy(xpath = "//a[contains(@aria-label, 'Create new')]")
	private WebElement buttonCreateNew;

	@FindBy(xpath = "//a[contains(text(), 'New repository')]")
	private WebElement linkNewRepository;

	@FindBy(xpath="//input[@name='submit' and @value='Exit']")
	private WebElement logoutButton;

	@FindBy(linkText = "unbelievable")
	private WebElement firstNewsPieceLink;

	@FindBy(xpath = "//table[contains(@class,'table')]/tbody")
	private WebElement newsTable;

    @FindBy(xpath="//select[1]")
    private WebElement authorSelect;

    @FindBy(xpath="//input[@value='Search']")
    private WebElement searchButton;

	public MainPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

    public List<WebElement> getAuthorsNews(String authorName){
        Select dropdown = new Select(authorSelect);
        dropdown.selectByValue(authorName);
        searchButton.submit();
        return newsTable.findElements(By.tagName("td"));
    }
	public void clickOnNewsPieceLink()
	{
		firstNewsPieceLink.click();
	}

	public List<WebElement> getNewsElementsList(){
		return newsTable.findElements(By.tagName("td"));
	}
	public void logout(){
		logoutButton.click();
	}

	@Override
	public void openPage()
	{
		driver.navigate().to(BASE_URL);
	}
}
