package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.List;

public class BinanceWebDriving {
    public static String[] main(String cryptoName, String amountValue, String duration) throws InterruptedException, IOException {
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\rodrigo.f.pinto\\IdeaProjects\\FinalEarn\\Driver\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\Driver\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");




        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.binance.com/en/simple-earn");

        Thread.sleep(3000);

        WebElement acceptCookiesButton = driver.findElement(By.id("onetrust-accept-btn-handler"));
        acceptCookiesButton.click();

        Thread.sleep(3000);

        WebElement okButton = driver.findElement(By.xpath("//button[contains(text(), 'OK')]"));
        okButton.click();

        // Find the search input field
        WebElement searchInput = driver.findElement(By.xpath("//input[@placeholder='Search']"));

        // Enter the cryptocurrency name you want to search for
        searchInput.sendKeys(cryptoName);

        Thread.sleep(1500);



        List<WebElement> elements = driver.findElements(By.className("css-16rhyii"));
        if(elements.size()>1){
            List<WebElement> durations = driver.findElements(By.className("css-12nuixs"));
            for (WebElement e: durations){
                if (duration.contains("1")) {
                    if (e.getText().contains("30")) {
                        e.click();
                    }
                }
                if(duration.contains("3")){
                    if (e.getText().contains("90")){
                        e.click();
                    }
                }
                if (duration.contains("Flex")){
                    if (e.getText().contains("Flexible")){
                        e.click();
                    }
                }
            }
            for (WebElement e : elements) {
                WebElement t = e.findElement(By.className("css-1v0o0u8"));
                if (t.getText().equals(cryptoName)){
                    if (e.getText().contains("%")){
                        String text = e.getText();
                        int firstNewlineIndex = text.indexOf("\n");

                        // Check if a newline character is found and if there are characters after it
                        if (firstNewlineIndex != -1 && firstNewlineIndex < text.length() - 1) {
                            String afterFirstNewline = text.substring(firstNewlineIndex + 1);
                            String cleanedText = afterFirstNewline.replace("\n", "");
                            String result = cleanedText.substring(0, cleanedText.indexOf("+"));
                            String[] Calculators = Calculator.calculations(result, amountValue,cryptoName);
                            String ganhoAnual = Calculators[0];
                            String weekly = Calculators[1];
                            String adaAnual = Calculators[2];
                            String mValue = Calculators[3];
                            return new String[]{result, ganhoAnual, weekly, adaAnual, mValue};
                        }
                        break;
                    }


                }

            }
        }
        else{

            List<WebElement> durations = driver.findElements(By.className("css-12nuixs"));
            for (WebElement e: durations){
                if (duration.contains("1")) {
                    if (e.getText().contains("30")) {
                        e.click();
                    }
                }
                if(duration.contains("3")){
                    if (e.getText().contains("90")){
                        e.click();
                    }
                }
                if (duration.contains("Flex")){
                    if (e.getText().contains("Flexible")){
                        e.click();
                    }
                }
            }





            // Check if the element has the exact text "ETH"
            // Find the elements that contain the "%" symbol
            WebElement[] elementsWithPercentage = driver.findElements(By.xpath("//*[contains(text(), '%')]"))
                    .toArray(new WebElement[0]);

            // Print the elements containing the "%" symbol
            for (WebElement element : elementsWithPercentage) {
                String text = element.getText();
                if (text.length() > 0) {
                    String[] Calculators = Calculator.calculations(text, amountValue, cryptoName);
                    String ganhoAnual = Calculators[0];
                    String weekly = Calculators[1];
                    String adaAnual = Calculators[2];
                    String mValue = Calculators[3];
                    return new String[]{text, ganhoAnual, weekly, adaAnual, mValue};

                }

            }
        }
        driver.quit();


        return new String[0];
    }
}
