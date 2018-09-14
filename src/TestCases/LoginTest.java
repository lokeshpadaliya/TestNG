package TestCases;

import java.io.IOException;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;



@Listeners({TestCases.TestListner.class, TestCases.SuitListner.class, TestCases.ExecutionListener.class})


public class LoginTest {
	
	
	static public WebDriver driver;
	
	
	
	@BeforeSuite
	public void SetUp()
	{
		//driver=new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\eclipse\\"+"chromedriver.exe");
		driver = new ChromeDriver();
		String baseUrl = "http://newtours.demoaut.com/mercurysignon.php";
        driver.get(baseUrl);
	}
	///////
	@Test(dataProvider="LoginData", priority=1)
	public void Login(String username, String password)throws InterruptedException
	{
		WebElement Usernamebox = driver.findElement(By.xpath("html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[1]/td[2]/input"));
		Usernamebox.sendKeys(username);
		WebElement Passwordbox = driver.findElement(By.xpath("html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[2]/td[2]/input"));
		Passwordbox.sendKeys(password);
		driver.findElement(By.xpath("html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[4]/td/input")).click();
		int temp;
		System.out.println(driver.findElement(By.xpath("html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td[1]/a")).getText());
		
		if(driver.findElement(By.xpath("html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td[1]/a")).getText().equals("SIGN-OFF"))
		{
			temp=1;
		}
		else temp=0;
		Assert.assertEquals(temp, 1);		
		
	}
	
	
	@Test(priority=2)
	public void FindFlight() throws InterruptedException
	{
	LoginTest.driver.findElement(By.xpath("html/body/div[1]/table/tbody/tr/td[1]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/a")).click();
	LoginTest.driver.findElement(By.xpath("html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[2]/td[2]/b/font/input[1]")).click();
	Select dropdown = new Select(LoginTest.driver.findElement(By.name("passCount")));
	dropdown.selectByVisibleText("2");
	dropdown= new Select(LoginTest.driver.findElement(By.name("fromPort")));
	dropdown.selectByVisibleText("London");
	LoginTest.driver.findElement(By.name("findFlights")).click();
	LoginTest.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	int temp=0;
	if(LoginTest.driver.findElement(By.xpath("html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table[1]/tbody/tr[1]/td/table/tbody/tr[1]/td[1]/font/b/font")).getText().equals("DEPART"));
	temp=1;
	Assert.assertEquals(temp,1);
	}
	

	@Test(priority=3)
	public void LogOut() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.findElement(By.linkText("SIGN-OFF")).click();
	}
	
	
	@DataProvider(name="LoginData")
	
	public Object[][] TestData() throws IOException
	{
		TestDataConfig tdc =new TestDataConfig("D:\\Selenium\\Test Data\\TestData.xlsx");
		int rows= tdc.RowCount();
		System.out.println(rows);
		Object[][] data= new Object[rows][2];
		for(int i=0; i<rows; i++)
		{
			data[i][0]=tdc.ReadExcel(0,i,0);
			data[i][1]=tdc.ReadExcel(0,i,1);
			System.out.println(data[i][0]);
			System.out.println(data[i][1]);
		}
		return data;
	}
	
	@AfterSuite(alwaysRun=true)
	public void TearDown()
	{
		driver.close();
	}

}
