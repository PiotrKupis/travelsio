package com.travelsio.flightservice.parser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.stereotype.Component;

@Component
public class HtmlParser {

    public Document parse(String url) {

        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.1234.56 Safari/537.36");
        options.addArguments("--lang=en-US,en;q=0.9");
        options.addArguments("--referer=https://www.google.com/");

        WebDriver webDriver =  new FirefoxDriver(options);
        webDriver.get(url);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            //TODO add exception handling
            e.printStackTrace();
        }

        Document document = Jsoup.parse(webDriver.getPageSource());
        webDriver.quit();

        return document;
    }
}
