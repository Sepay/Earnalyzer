package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.List;

public class CROWebDriving {
    public static String cryptoN;
    public static String[] main(String cryptoName, String amountValue, String duration, String lock) throws InterruptedException, IOException {

        if (cryptoName.equals("PAXG")){
             cryptoN = "PAX";
        }
        else {
             cryptoN = cryptoName;
        }


        //        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\Driver\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\Driver\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://crypto.com/eea/earn");
        Thread.sleep(1500);

        WebElement acceptCookiesButton = driver.findElement(By.xpath("//button[contains(text(), 'Accept')]"));
        acceptCookiesButton.click();

        Thread.sleep(1500);

        WebElement button = driver.findElement(By.id("token-select-inputbox"));
        button.click();
        WebElement button2 = driver.findElement(By.className("css-1bvzme1"));
        button2.click();
        button2.sendKeys(cryptoN);
        Thread.sleep(1000);
        WebElement cryptoButton = driver.findElement(By.className("css-1u0sann"));
        cryptoButton.click();
        Thread.sleep(500);
        WebElement amount = driver.findElement(By.xpath("//*[@id=\"calculator\"]/div/div[1]/div/div[2]/div[2]/div/div[2]/div/input"));
        amount.click();
        amount.clear();
        amount.sendKeys(amountValue);

        WebElement durButton = driver.findElement(By.className("css-1m9zygp"));
        durButton.click();

        Thread.sleep(1500);

        List<WebElement> durations = driver.findElements(By.className("css-17k7ina"));
        if (duration.contains("1")) {
            for (WebElement e : durations){
                if (e.getText().contains("1")) {
                    e.click();
                    break;
                }
            }
        }
        else if (duration.contains("Flex")) {
            for (WebElement e : durations){
                if (e.getText().contains("Flex")) {
                    e.click();
                    break;
                }
            }
        }
        else{
            for (WebElement e : durations){
                if (e.getText().contains("3")) {
                    e.click();
                    break;
                }
            }
        }

        Thread.sleep(1500);

        if (lock.contains("Less")){
            WebElement lockUpLess = driver.findElement(By.xpath("//*[@id=\"calculator\"]/div/div[1]/div/div[2]/div[4]/div/div[2]/button[1]/h5"));
            lockUpLess.click();
        }
        if (lock.contains("More")){
            WebElement lockUpMore = driver.findElement(By.xpath("//*[@id=\"calculator\"]/div/div[1]/div/div[2]/div[4]/div/div[2]/button[3]/h5"));
            lockUpMore.click();
        }
        
        Thread.sleep(1500);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Thread.sleep(1500);

        WebElement element = driver.findElement(By.xpath("/html/body/div[1]/div[1]/main/div[2]/div/div[1]/section[1]/div/div[4]/div[2]/div/div[2]/div[1]/h2/div/div[1]"));

        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

        Thread.sleep(1500);
        String text = (String) js.executeScript("return arguments[0].textContent;", element);
        String[] Calculators = Calculator.calculations(text, amountValue, cryptoName);
        String ganhoAnual = Calculators[0];
        String weekly = Calculators[1];
        String adaAnual = Calculators[2];
        driver.quit();
        return new String[]{text, ganhoAnual, weekly, adaAnual};
    }
}
