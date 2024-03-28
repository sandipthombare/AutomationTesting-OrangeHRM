package com.example.setup;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Setup {
    public WebDriver driver;

    @SuppressWarnings("deprecation")
    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30).toMillis(), TimeUnit.MILLISECONDS);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        
    }

    @AfterTest
    public void closeDriver(){
        driver.close();
    }
}
