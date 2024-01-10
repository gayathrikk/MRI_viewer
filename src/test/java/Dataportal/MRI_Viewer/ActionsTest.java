package Dataportal.MRI_Viewer;

import java.net.URL;
import org.openqa.selenium.interactions.Actions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.awt.AWTException;
import java.net.MalformedURLException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ActionsTest {

    private RemoteWebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void setup() throws MalformedURLException {
        DesiredCapabilities dc = DesiredCapabilities.chrome();
        URL url = new URL("http://172.20.23.7:5555/wd/hub");
        driver = new RemoteWebDriver(url, dc);
        wait = new WebDriverWait(driver, 10); // Adjust the timeout as needed
    }

    @Test(priority = 1)
    public void launch() throws AWTException, InterruptedException {

        driver.get("http://dev2sam.humanbrain.in/annotation/portal");
        driver.manage().window().maximize();
    }
        @Test(priority=2)
        public void analytics() throws InterruptedException
        {
        Actions actions = new Actions(driver);
        actions.sendKeys("d").perform(); // Simulate pressing lowercase 'd'
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement viewerElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='Public']")));
        if (viewerElement.isEnabled() && viewerElement.isDisplayed()) {
            viewerElement.click();
            System.out.println("public icon is clicked");
        } else {
            System.out.println("public icon is not clickable");
        }
        Thread.sleep(8000);
        
        WebElement section = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='772']")));
        if (section.isEnabled() && section.isDisplayed()) {
            section.click();
            System.out.println("section 772 icon is clicked");
        } else {
            System.out.println("section 772 icon is not clickable");
        }
        Thread.sleep(6000);
        driver.findElement(By.xpath("(//i[@role='presentation'])[1]")).click();
        driver.findElement(By.xpath("(//i[@role='presentation'])[4]")).click();
        driver.findElement(By.xpath("(//i[@role='presentation'])[7]")).click();
        driver.findElement(By.xpath("(//i[@role='presentation'])[10]")).click();
        driver.findElement(By.xpath("(//i[@role='presentation'])[13]")).click();
        driver.findElement(By.xpath("(//i[@role='presentation'])[22]")).click();
        WebElement node = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//i[@role='presentation'])[38]")));
        if (node.isEnabled() && node.isDisplayed()) {
            node.click();
            System.out.println("node is clicked");
        } else {
            System.out.println("node is  not clickable");
        }
        Thread.sleep(8000);
        
      //analytics
        
        System.out.println("*Analytics values of particular node:");
		 String jsonBody = "{\"currentsection\":\"B_222_FB74-SL_258-ST_NISL-SE_772_lossless.jp2\",\"geojson\":\"{\\\"type\\\":\\\"Polygon\\\",\\\"coordinates\\\":[[[53642.14428714697,-42621.60012466364],[51967.624605384204,-40062.557449968],[50610.86735332233,-37884.10214384048],[49349.656386616916,-36087.83197913884],[49196.78233004657,-35036.82284021767],[48929.25273104845,-34071.805358117315],[48336.86576183834,-33746.94798790532],[47381.40290827363,-33527.191531585435],[45460.92257260858,-33441.199874764614],[43330.24040915929,-33164.11564723085],[41877.93687174094,-32600.39256362767],[41760.89267217927,-32551.42509238248],[41071.76508904572,-32635.02809206939],[40462.657519898225,-32646.971377738955],[40462.657519898225,-32646.971377738955],[40462.657519898225,-32646.971377738955],[40462.657519898225,-32646.971377738955],[40008.81266445499,-32623.084806399835],[39749.64336542557,-32039.058137158412],[40487.5939899673,-31732.777251938314],[40186.767620931416,-31425.173253743087],[40521.17961967906,-31224.526054494505],[41139.86281016868,-30923.30342706747],[41619.96190127847,-31205.416797423204],[43989.50977811893,-32600.39256362767],[48957.91661665539,-32179.988908059204],[53066.40688698361,-30011.08823046733],[56295.87133203231,-28854.978177654033],[57748.17486945066,-27708.42275337639],[58703.63772301536,-26714.7413856691],[60499.90788771701,-26638.304357383924],[61684.68182613724,-27173.363555380158],[61761.11885442241,-30077.970630216856],[62563.70765141677,-30727.68537064086],[62563.70765141677,-32829.7036484832],[61952.21142513535,-34855.28489804037],[60958.530057428055,-35504.99963846437],[59391.570977581956,-35963.62180817543],[57518.86378459513,-35925.40329403284],[53697.01237033632,-35504.99963846437],[52474.0199177735,-35810.74775160508],[51251.02746521068,-36919.08466174013],[51356.128379102796,-37578.354030699775],[51556.775578351386,-38791.79185472695],[52827.541173592435,-40989.35641792577],[53773.449398621495,-42766.51732555612],[53773.449398621495,-42766.51732555612],[53642.14428714697,-42621.60012466364]]]}\",\"biosample\":\"222\"}";
	        given()
	            .auth()
	            .preemptive()
	            .basic("admin", "admin")
	            .contentType(ContentType.JSON) 
	            .body(jsonBody) 
	        .when()
	            .post("http://ap3.humanbrain.in:90/getRegionData/")
	        .then()
	            .statusCode(200)
	            .log().all();
        }
        @Test(priority=3)
        public void cell_api()
        {
	        
	        //EP1
	        
	      	 Response response1 = RestAssured
	               .given()
	                   .auth()
	                   .preemptive()
	                   .basic("admin", "admin")
	                   .formParam("iipsrv", "http://apollo2.humanbrain.in:9081/fcgi-bin/iipsrv.fcgi?FIF=/data/storageIIT/humanbrain/analytics/222/NISL/B_222_FB74-SL_258-ST_NISL-SE_772_compressed.jp2&GAM=1.5&WID=512&RGN=0.7629672162524267,0.3702347863421689,0.006999699231673639,0.005784918536596389&CVT=jpeg")
	               .when()
	                   .post("http://ap3.humanbrain.in:8888/predictions/ep1");
	      	 int statusCode1 = response1.getStatusCode();
	      	 if (statusCode1 == 200) {
	               System.out.println("API request to Ep1 passed. Status code: " + statusCode1);
	           } else {
	               System.out.println("API request to Ep1 failed. Status code: " + statusCode1);
	           }
	           Assert.assertEquals(statusCode1, 200, "API request to Ep1 failed");
        
    }
    
    	@Test(priority=4)
        public void Brains_api()
        {
        Public_5brains();
        }
    	@Test(priority=5)
    	public void Maskimage_api()
    	{
        System.out.println("*");
        Maskimage_141();
        Maskimage_222();
        Maskimage_212();
        Maskimage_142();
        Maskimage_213();
    	}
    	@Test(priority=6)
    	public void MRI_api()
    	{
        System.out.println("*");
        MRIimage_141();
        MRIimage_222();
        MRIimage_212();
        MRIimage_142();
        MRIimage_213();
    	}
    	@Test(priority=7)
    	public void Public_sections_api()
    	{
        System.out.println("*");
        public_sections_141();
        public_sections_222();
        public_sections_212();
        public_sections_142();
        public_sections_213();
        
    }
    private  void Public_5brains() {
    	
   	 Response response = RestAssured.get("https://dataportal.iitm.humanbrain.in/GW/getPublicBrains");
     int statusCode = response.getStatusCode();
     if (statusCode == 200) {
         System.out.println("API request to the public 5 brains passed. Status code: " + statusCode);
     } else {
         System.out.println("API request to the public 5 brains failed. Status code: " + statusCode);
     }
     Assert.assertEquals(statusCode, 200, "API request to the public 5 brains failed");
    }
    
    private  void Maskimage_141() {
    	
      	 Response response = RestAssured.get("https://dataportal.iitm.humanbrain.in/iipsrv/data/storageIIT/humanbrain/analytics/141/MRI/stl/brain_small.stl");
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            System.out.println("API request to the mask image-141 passed. Status code: " + statusCode);
        } else {
            System.out.println("API request to the mask image-141 failed. Status code: " + statusCode);
        }
        Assert.assertEquals(statusCode, 200, "API request to the mask image-141 failed");
       }
    private  void Maskimage_222() {
    	
     	 Response response = RestAssured.get("https://dataportal.iitm.humanbrain.in/iipsrv/data/storageIIT/humanbrain/analytics/222/MRI/stl/brain_small.stl");
       int statusCode = response.getStatusCode();
       if (statusCode == 200) {
           System.out.println("API request to the mask image-222 passed. Status code: " + statusCode);
       } else {
           System.out.println("API request to the mask image-222 failed. Status code: " + statusCode);
       }
       Assert.assertEquals(statusCode, 200, "API request to the mask image-222 failed");
      }
    private  void Maskimage_212() {
    	
    	 Response response = RestAssured.get("https://dataportal.iitm.humanbrain.in/iipsrv/data/storageIIT/humanbrain/analytics/212/MRI/stl/brain_small.stl");
      int statusCode = response.getStatusCode();
      if (statusCode == 200) {
          System.out.println("API request to the mask image-212 passed. Status code: " + statusCode);
      } else {
          System.out.println("API request to the mask image-212 failed. Status code: " + statusCode);
      }
      Assert.assertEquals(statusCode, 200, "API request to the mask image-212 failed");
     }
    private  void Maskimage_142() {
    	
   	 Response response = RestAssured.get("https://dataportal.iitm.humanbrain.in/iipsrv/data/storageIIT/humanbrain/analytics/142/MRI/stl/brain_small.stl");
     int statusCode = response.getStatusCode();
     if (statusCode == 200) {
         System.out.println("API request to the mask image-142 passed. Status code: " + statusCode);
     } else {
         System.out.println("API request to the mask image-142 failed. Status code: " + statusCode);
     }
     Assert.assertEquals(statusCode, 200, "API request to the mask image-142 failed");
    }
    private  void Maskimage_213() {
    	
      	 Response response = RestAssured.get("https://dataportal.iitm.humanbrain.in/iipsrv/data/storageIIT/humanbrain/analytics/213/MRI/stl/brain_small.stl");
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            System.out.println("API request to the mask image-213 passed. Status code: " + statusCode);
        } else {
            System.out.println("API request to the mask image-213 failed. Status code: " + statusCode);
        }
        Assert.assertEquals(statusCode, 200, "API request to the mask image-213 failed");
       }
    private  void MRIimage_141() {
    	
     	 Response response = RestAssured.get("https://dataportal.iitm.humanbrain.in/iipsrv/data/storageIIT/humanbrain/analytics/141/MRI/nii/FB40.nii.gz");
       int statusCode = response.getStatusCode();
       if (statusCode == 200) {
           System.out.println("API request to the MRI image-141 passed. Status code: " + statusCode);
       } else {
           System.out.println("API request to the MRI image-141 failed. Status code: " + statusCode);
       }
       Assert.assertEquals(statusCode, 200, "API request to the MRI image-141 failed");
      }
    private  void MRIimage_222() {
    	
    	 Response response = RestAssured.get("http://dev2sam.humanbrain.in:9081/data/storageIIT/humanbrain/analytics/222/MRI/nii/masked_0__1.nii.gz");
      int statusCode = response.getStatusCode();
      if (statusCode == 200) {
          System.out.println("API request to the MRI image-222 passed. Status code: " + statusCode);
      } else {
          System.out.println("API request to the MRI image-222 failed. Status code: " + statusCode);
      }
      Assert.assertEquals(statusCode, 200, "API request to the MRI image-222 failed");
     }
    private  void MRIimage_212() {
    	
   	 Response response = RestAssured.get("http://dev2sam.humanbrain.in:9081/data/storageIIT/humanbrain/analytics/212/MRI/nii/FB67.nii.gz");
     int statusCode = response.getStatusCode();
     if (statusCode == 200) {
         System.out.println("API request to the MRI image-212 passed. Status code: " + statusCode);
     } else {
         System.out.println("API request to the MRI image-212 failed. Status code: " + statusCode);
     }
     Assert.assertEquals(statusCode, 200, "API request to the MRI image-212 failed");
    }
    private  void MRIimage_142() {
    	
      	 Response response = RestAssured.get("http://dev2sam.humanbrain.in:9081/data/storageIIT/humanbrain/analytics/142/MRI/nii/FB34.nii.gz");
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            System.out.println("API request to the MRI image-142 passed. Status code: " + statusCode);
        } else {
            System.out.println("API request to the MRI image-142 failed. Status code: " + statusCode);
        }
        Assert.assertEquals(statusCode, 200, "API request to the MRI image-142 failed");
       }
    private  void MRIimage_213() {
    	
     	 Response response = RestAssured.get("http://dev2sam.humanbrain.in:9081/data/storageIIT/humanbrain/analytics/213/MRI/nii/FB62.nii.gz");
       int statusCode = response.getStatusCode();
       if (statusCode == 200) {
           System.out.println("API request to the MRI image-213 passed. Status code: " + statusCode);
       } else {
           System.out.println("API request to the MRI image-213 failed. Status code: " + statusCode);
       }
       Assert.assertEquals(statusCode, 200, "API request to the MRI image-213 failed");
      }
    private  void public_sections_141() {
    	
    	 Response response = RestAssured.get("http://dev2sam.humanbrain.in:8000/GW/getBrainThumbNailDetails/IIT/V1/SS-62?public=1");
      int statusCode = response.getStatusCode();
      if (statusCode == 200) {
          System.out.println("API request to the public sections of-141 passed. Status code: " + statusCode);
      } else {
          System.out.println("API request to the public sections of-141 failed. Status code: " + statusCode);
      }
      Assert.assertEquals(statusCode, 200, "API request to the public sections of-141 failed");
     }
    private  void public_sections_222() {
    	
   	 Response response = RestAssured.get("http://dev2sam.humanbrain.in:8000/GW/getBrainThumbNailDetails/IIT/V1/SS-100?public=1");
     int statusCode = response.getStatusCode();
     if (statusCode == 200) {
         System.out.println("API request to the public sections of-222 passed. Status code: " + statusCode);
     } else {
         System.out.println("API request to the public sections of-222 failed. Status code: " + statusCode);
     }
     Assert.assertEquals(statusCode, 200, "API request to the public sections of-222 failed");
    }
    private  void public_sections_212() {
    	
      	 Response response = RestAssured.get("http://dev2sam.humanbrain.in:8000/GW/getBrainThumbNailDetails/IIT/V1/SS-88?public=1");
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            System.out.println("API request to the public sections of-212 passed. Status code: " + statusCode);
        } else {
            System.out.println("API request to the public sections of-212 failed. Status code: " + statusCode);
        }
        Assert.assertEquals(statusCode, 200, "API request to the public sections of-212 failed");
       }
    private  void public_sections_142() {
    	
     	 Response response = RestAssured.get("http://dev2sam.humanbrain.in:8000/GW/getBrainThumbNailDetails/IIT/V1/SS-65?public=1");
       int statusCode = response.getStatusCode();
       if (statusCode == 200) {
           System.out.println("API request to the public sections of-142 passed. Status code: " + statusCode);
       } else {
           System.out.println("API request to the public sections of-142 failed. Status code: " + statusCode);
       }
       Assert.assertEquals(statusCode, 200, "API request to the public sections of-142 failed");
      }
    private  void public_sections_213() {
    	
    	 Response response = RestAssured.get("http://dev2sam.humanbrain.in:8000/GW/getBrainThumbNailDetails/IIT/V1/SS-94?public=1");
      int statusCode = response.getStatusCode();
      if (statusCode == 200) {
          System.out.println("API request to the public sections of-213 passed. Status code: " + statusCode);
      } else {
          System.out.println("API request to the public sections of-213 failed. Status code: " + statusCode);
      }
      Assert.assertEquals(statusCode, 200, "API request to the public sections of-213 failed");
     }



    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
