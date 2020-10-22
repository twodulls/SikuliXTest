package com.test.sikulix;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.annotations.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class SikuliXTest {
    public WebDriver driver;
    public WebDriverWait wait;
    public String pathStr;

    @BeforeMethod
    public void setUp(){
        Path path = Paths.get("");
        pathStr = path.toAbsolutePath().toString();
        //크롬 드라이버 세팅
        System.setProperty("webdriver.chrome.driver", pathStr + "//src//driver//chromedriver");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); // 기본 대기 시간
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 20);
    }

    @Test
    public void sikulixTest() throws FindFailed, InterruptedException {
        driver.get("http://demo.guru99.com/test/image_upload/index.php");

        // form <input type='file'> about click
        WebElement chooseFile = driver.findElement(By.xpath("//*[@id=\"photoimg\"]"));
        wait.until(ExpectedConditions.visibilityOf(chooseFile));
        Actions action = new Actions(driver);
        action.moveToElement(chooseFile).click().perform();
        System.out.println("파일 선택 클릭");

        Screen s = new Screen();
        Pattern fileInputTextBox = new Pattern(pathStr + "/src/test/resources/test_docx.png");
//        Pattern openButton = new Pattern(pathStr + "/src/test/resources/open.png");

        s.wait(fileInputTextBox, 20);
        s.doubleClick(fileInputTextBox, 20);
        System.out.println("test.docx파일 선택");

//        s.click(openButton, 20);
//        System.out.println("열기 클릭");

        WebElement fileUploadResult;
        fileUploadResult = driver.findElement(By.xpath("//*[@id=\"preview\"]/h4/font"));
        String result = fileUploadResult.getText();
        System.out.println("result : " + result);

        Assert.assertEquals(result, "File Upload Successful");
        System.out.println("assert 확인");
    }

    @AfterMethod
    public void teardown(){
        if( driver != null ){
            driver.close();
            driver.quit();
        }
    }
}
