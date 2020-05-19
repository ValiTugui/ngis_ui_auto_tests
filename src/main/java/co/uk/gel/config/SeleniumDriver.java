package co.uk.gel.config;


import co.uk.gel.lib.Actions;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import co.uk.gel.lib.SeleniumLib;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class SeleniumDriver extends EventFiringWebDriver {

    private static  WebDriver DRIVER;

    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            try {
                DRIVER.quit();
            } catch (Exception exp) {
                Debugger.println("Exception from Quiting the Driver...." + exp.getLocalizedMessage());
            }
        }
    };

    static {
        try {
            DRIVER = new BrowserFactory().getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Actions.deleteCookies(DRIVER);
        //Commenting the snapshot clean up as each browser invocation clean the existing snapshots, loosing snapshots with parallel run
        TestUtils.clearAllSnapShots();
        SeleniumLib.ParentWindowID = DRIVER.getWindowHandle();
        Capabilities cap = ((RemoteWebDriver) DRIVER).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        Debugger.println("BROWSER NAME : " + browserName);
        if(browserName.equalsIgnoreCase(BrowserConfig.getBrowser())){
            //DRIVER.manage().window().fullscreen(); //Switching to new tab gives error when in Full screen.
            DRIVER.manage().window().maximize();
        } else {
            DRIVER.manage().window().maximize();
        }
        DRIVER.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }
    public SeleniumDriver() {
        super(DRIVER);
    }

    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException("You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        super.close();
        Debugger.println("From SeleniumDriver...........Close");
    }

    //@Before
    /**
     * Delete all cookies at the start of each scenario to avoid
     * shared state between tests
     */
    public void deleteAllCookies() {
        manage().deleteAllCookies();
    }

}//end