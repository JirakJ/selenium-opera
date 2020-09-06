package com.jakubjirak.automation;

import com.jakubjirak.automation.helpers.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.testng.annotations.Test;

public class Runner {
    private static Runner instance = null;
    private WebDriver webDriver;
    private OperaOptions operaOptions;
    public static final String URL = "https://jakubjirak.com";

    private Runner(){
        System.setProperty("webdriver.opera.driver", ConfigReader.getInstance().getOperaDriver());
        System.out.println("Using opera driver");

        operaOptions = new OperaOptions();
        operaOptions.addArguments("--disable-gpu","--window-size=1920,1080","--ignore-ssl-errors=yes");
        operaOptions.setBinary(ConfigReader.getInstance().getOperaBinary());
        webDriver = new OperaDriver(operaOptions);
    }

    private void navigate(final String url) {
        System.out.println(String.format("Navigating to %s", url));
        webDriver.get(url);
    }

    public void teardown() {
        System.out.println("Browser teardown");
        webDriver.close();
        webDriver.quit();
    }

    public static Runner getInstance() {
        if(instance==null){
            instance = new Runner();
        }
        return instance;
    }

    @Test
    public void run_opera() {
        Runner runner = Runner.getInstance();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        runner.navigate(URL);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        runner.teardown();

    }
}
