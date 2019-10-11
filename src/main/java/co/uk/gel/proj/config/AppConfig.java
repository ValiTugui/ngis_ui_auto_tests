package co.uk.gel.proj.config;

import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by SANTHOSH LP on 4/26/2019.
 */
public class AppConfig {
    private static String app_url;
    private static String app_username;
    private static String app_password;
    private static String td_private_url;

    public static void loadAppConfig() {
        String configFileName = "%s-appconfig.properties";
        String EnvironmentName = System.getProperty("TestEnvironment");
        System.out.println("TestEnvironment: " + EnvironmentName);

        configFileName = String.format(configFileName, EnvironmentName);
        Properties properties = new Properties();
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            try (InputStream resourceStream = loader.getResourceAsStream(configFileName)) {
                //  properties.load(new FileInputStream(new File(configFileName)));
                properties.load(resourceStream);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        app_url = properties.getProperty("APP_URL");
        app_username = properties.getProperty("APP_USERNAME");
        app_password = properties.getProperty("APP_PASSWORD");
        td_private_url = properties.getProperty("TEST_DIRECTORY_PRIVATE_URL");
    }


    public static void loadAppConfig2() {
        Scanner file_scanner = loadFile("AppConfig.txt");
        String line = "";
        String[] linearray;
        while (file_scanner.hasNextLine()) {
            line = file_scanner.nextLine();
            if (line == null || line.trim().length() == 0) {
                continue;
            }
            if (line.startsWith("#")) {
                continue;
            }
            linearray = line.split("=");
            if (linearray == null || linearray.length != 2) {
                continue;
            }
            if (linearray[0].trim().equalsIgnoreCase("APP_URL")) {
                app_url = linearray[1].trim();
            } else if (linearray[0].trim().equalsIgnoreCase("APP_USERNAME")) {
                app_username = linearray[1].trim();
            } else if (linearray[0].trim().equalsIgnoreCase("APP_PASSWORD")) {
                app_password = linearray[1].trim();
            }
        }
    }

    private static Scanner loadFile(String filename) {

        String configlocation = System.getProperty("user.dir") + File.separator + "co/uk/gel/config/";
        Scanner file_scanner;
        try {
            file_scanner = new Scanner(new File(configlocation + filename));
            return file_scanner;
        } catch (Exception exp) {
            Assert.assertFalse("Exception from reading AppConfig :" + exp, false);
        }
        return null;
    }

    public static String getApp_url() {
        if (app_url == null || app_url.isEmpty()) {
            loadAppConfig();
        }
        return app_url;
    }

    public static String getTd_private_url() {
        if (td_private_url == null || td_private_url.isEmpty()) {
            loadAppConfig();
        }
        return td_private_url;
    }
    public static void setApp_url(String app_url) {
        AppConfig.app_url = app_url;
    }

    public static String getApp_username() {
        return app_username;
    }

    public static void setApp_username(String app_username) {
        AppConfig.app_username = app_username;
    }

    public static String getApp_password() {
        return app_password;
    }

    public static void setApp_password(String app_password) {
        AppConfig.app_password = app_password;
    }
}//end
