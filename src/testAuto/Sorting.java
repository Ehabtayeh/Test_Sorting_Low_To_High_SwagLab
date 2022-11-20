package testAuto;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Sorting {

	public WebDriver driver;

	@BeforeTest()
	public void login() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
		driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select/option[3]")).click();

	}

	@Test()
	public void testSorting_Low_to_high() {
		List<WebElement> thePriceList = driver.findElements(By.className("inventory_item_price"));
		List<Double> newEditedList = new ArrayList<>();
		for (int i = 0; i < thePriceList.size(); i++) {
			String price = thePriceList.get(i).getText();
			String EditedList = price.replace("$", "").trim();
			double val = Double.parseDouble(EditedList);
			newEditedList.add(val);

		}
		for(int j = 0;j<newEditedList.size();j++)
		{
			boolean checkPrice = newEditedList.get(0)<newEditedList.get(newEditedList.size()-1);
			Assert.assertEquals(checkPrice, true);
		}
	}
	
	
}
