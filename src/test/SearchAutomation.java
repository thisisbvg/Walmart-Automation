package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import util.ReadFromFile;


public class SearchAutomation implements XpathRepository{

	private static Map<String,String>map = new HashMap<String,String>();

	WebDriver driver = new FirefoxDriver();
	
	/*@BeforeClass
	public void instantiateChromeBrowser()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\gnanasrinivas\\Desktop\\New folder\\Written Test\\chromedriver.exe");
		driver = new org.openqa.selenium.chrome.ChromeDriver();
	}*/

	/*
	 * This before method reads the input text file based on the file name entered by the user.
	 * Then it populates these values (ItemName & Quantity) into a map.
	 * In case the file name is invalid, then an exception will be thrown.
	 */
	
	
	@BeforeTest
	public static void readInputFile()
	{
		String fName = null;
		System.out.println("Enter Input file name");
		String l_sLine = null;

		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			fName = br.readLine();

			ReadFromFile rff= new ReadFromFile(fName);

			Boolean isTitle = true;

			String[] contents;

			while((l_sLine = rff.readItems())!=null)
			{
				if(isTitle==false)
				{
					contents = l_sLine.split("\t");
					map.put(contents[0], contents[1]);
				}
				isTitle = false;
			}
			
			
		}
		catch(IOException ioe)
		{
			Logger.getLogger("Error in reading file name");
			ioe.printStackTrace();
			System.exit(1);
		}

		Logger.getLogger("I completed the map populating part:");
	}
	/*
	 * This method will open the Walmart URL and then logs in to the application.
	 * Here I am even ensuring that the cart is empty.
	 */
	@BeforeMethod(alwaysRun=true)
	public void walmartSignIn()
	{
		try
		{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get(homePageUrl);

		driver.manage().window().maximize();

		driver.findElement(By.xpath(userNameXpath)).sendKeys(userName);

		driver.findElement(By.xpath(passwordXpath)).sendKeys(password);

		driver.findElement(By.xpath(signInButton)).click();

		
		Logger.getLogger("I completed the login part");
		deleteAnItemFromCart();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	/*
	 * This method searches for a value based on the ItemValues in the map and then clicks on the first element
	 * and then adds it to the cart. Validates if the element added to the cart is the same as that is intended to add.
	 */
	@Test
	public void searchItem()
	{
		int count=0;
		boolean state=false;
		String ItemNameOnPopUpWindow=null;
		String itemToBeAdded=null;
		try
		{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			int randomSearchValue = randomValueGenerator();
			for(String str : map.keySet())
			{
				if(randomSearchValue==count)
				{

					Thread.sleep(5000);
					System.out.println("The first value is--------->"+str);
					driver.findElement(By.xpath(searchBar)).clear();
					driver.findElement(By.xpath(searchBar)).sendKeys(str);
					driver.findElement(By.xpath(searchBarClick)).click();
					Thread.sleep(6000);
					itemToBeAdded = driver.findElement(By.xpath(addThisSearchElement)).getText();
					System.out.println("The item added to the cart is--------->"+itemToBeAdded);
					driver.findElement(By.xpath(addThisSearchElement)).click();
					Thread.sleep(10000);


					if(str.equalsIgnoreCase("iphone"))
					{
						Thread.sleep(4000);
						driver.findElement(By.cssSelector(".variant-swatch")).click();
						Thread.sleep(4000);
						System.out.println("I clicked on the first color");
						driver.findElement(By.xpath(addToCartButton)).click();
						Thread.sleep(2000);
						System.out.println("Added item to the cart:");
						/*driver.findElement(By.cssSelector(iphoneColor2)).click();
						Thread.sleep(3000);*/
					}



					if(driver.findElement(By.xpath("//*[@id='WMItemAddToCartBtn']"))!=null)
					{

						driver.navigate().refresh();
						Thread.sleep(6000);
						driver.findElement(By.xpath("//*[@id='WMItemAddToCartBtn']")).click();
						Thread.sleep(3000);
					}
					Thread.sleep(4000);


					if(str.equalsIgnoreCase("tv"))
					{
						Thread.sleep(2000);
						driver.findElement(By.xpath(upArrowForTv)).click();
						Thread.sleep(4000);
						ItemNameOnPopUpWindow = driver.findElement(By.xpath(cartPopUpWindow)).getText();
						Thread.sleep(2000);

					}
					else if(str.equalsIgnoreCase("socks") ||  str.equalsIgnoreCase("toys for toddlers") || str.equalsIgnoreCase("dvd") || str.equalsIgnoreCase("iphone"))
					{
						Thread.sleep(4000);
						ItemNameOnPopUpWindow = driver.findElement(By.xpath(cartPopUpWindow)).getText();
						Thread.sleep(2000);
					}
					System.out.println("Trying to validate*************>"+ItemNameOnPopUpWindow);



					if(ItemNameOnPopUpWindow.contains(itemToBeAdded))
					{
						state=true;
					}
					Assert.assertEquals(true, state);

					break;
				}

				else
				{
					count++;
				}

			}
			System.out.println("String lengths are:"+ ItemNameOnPopUpWindow.length()+"    "+itemToBeAdded.length());

		}
		catch(Exception e)
		{

			e.printStackTrace();
			Assert.assertEquals(true, false);

		}
	}
	/*
	 * This method deletes the item from the cart and then quits the webdriver after successful execution.
	 */
	@AfterTest
	public void clearTheCart()
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(5000);
			if(driver.findElement(By.xpath(closePopUpWindow))!=null)
			{
				driver.findElement(By.xpath(closePopUpWindow)).click();
				Thread.sleep(3000);
			}
			Thread.sleep(5000);

			deleteAnItemFromCart();
			driver.quit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.assertEquals(true, false);
		}
	}


	/*
	 * This is a method used to generate a random number in between 0-maximum size of the hash map.
	 * Then this value gets returned, which is then used to fetch the corresponding value in the map and search the same element.
	 */
	public int randomValueGenerator()
	{
		int min=0;
		Random rand=new Random();
		int max=map.keySet().size();
		int randomNum = rand.nextInt((max - min)) + min;
		System.out.println("The random value is::::::::::::::"+randomNum);
		return randomNum;

	}

	public void deleteAnItemFromCart() throws Exception
	{
		try
		{
			if(driver.findElement(By.xpath(TrolleyShape))!=null)
			{
				System.out.println("I clicked on the cart button:");
				Thread.sleep(4000);
				driver.findElement(By.xpath(TrolleyShape)).click();
			}

			if(driver.findElement(By.xpath(deleteItemFromCart))!=null)
			{
				driver.findElement(By.xpath(deleteItemFromCart)).click();
				//driver.navigate().refresh();
				Thread.sleep(3000);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
