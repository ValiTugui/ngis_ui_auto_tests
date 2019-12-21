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
    private static String app_superUsername;
    private static String app_superPassword;
    private static String to_patient_search_url;
    private static String td_private_url;
    private static String to_dashboard_url;
    private static String MYNewName;
    public static String NGIS_Version_Number;
    private static String NGIS_Version_URL;
    public static String searchTerm;
    public static Properties properties = null;

    public static void loadAppConfig() {
        String configFileName = "%s-appconfig.properties";
        String EnvironmentName = System.getProperty("TestEnvironment");
        System.out.println("RUN Environment: " + EnvironmentName);

        configFileName = String.format(configFileName, EnvironmentName);
        properties = new Properties();
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            try (InputStream resourceStream = loader.getResourceAsStream(configFileName)) {
                //  properties.load(new FileInputStream(new File(configFileName)));
                properties.load(resourceStream);
            }
            //  System.out.println("mehnat_karo_bhai" + properties.getProperty("envname"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        app_url = properties.getProperty("APP_URL");
        app_username = properties.getProperty("APP_USERNAME");
        app_password = properties.getProperty("APP_PASSWORD");
        app_superUsername = properties.getProperty("SUPER_USERNAME");
        app_superPassword = properties.getProperty("SUPER_PASSWORD");
        to_patient_search_url = properties.getProperty("TO_PATIENT_SEARCH_URL");
        td_private_url = properties.getProperty("TEST_DIRECTORY_PRIVATE_URL");
        to_dashboard_url = properties.getProperty("DASHBOARD_PRIVATE_URL");
        MYNewName = properties.getProperty("MYNewName");
        NGIS_Version_URL = properties.getProperty("NGIS_Version_URL");
        app_superUsername = properties.getProperty("SUPER_USERNAME");
        app_superPassword = properties.getProperty("SUPER_PASSWORD");
    }

    public static String getPropertyValueFromPropertyFile(String propertyVal) {
        if (properties == null)
            loadAppConfig();
        return properties.getProperty(propertyVal);
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

    public static String getTo_patient_search_url() {
        if (to_patient_search_url == null || to_patient_search_url.isEmpty()) {
            loadAppConfig();
        }
        return to_patient_search_url;
    }

    public static String getTd_private_url() {
        if (td_private_url == null || td_private_url.isEmpty()) {
            loadAppConfig();
        }
        return td_private_url;
    }

    public static String getTo_dashboard_url() {
        if (to_dashboard_url == null || to_dashboard_url.isEmpty()) {
            loadAppConfig();
        }
        return to_dashboard_url;
    }

    public static String getTo_NGISVerion_url() {
        if (NGIS_Version_URL == null || NGIS_Version_URL.isEmpty()) {
            loadAppConfig();
        }
        return NGIS_Version_URL;
    }

    public static void setApp_url(String app_url) {
        AppConfig.app_url = app_url;
    }

    public static String getApp_username() {
        return app_username;
    }

    public static String getApp_superUsername() {
        return app_superUsername;
    }

    public static void setApp_username(String app_username) {
        AppConfig.app_username = app_username;
    }

    public static String getApp_password() {
        return app_password;
    }

    public static String getApp_superPassword() {
        return app_superPassword;
    }

    public static void setApp_password(String app_password) {
        AppConfig.app_password = app_password;
    }

    public static String getMYNewName() {
        return MYNewName;
    }

    public static String getNGISVersion() {
        NGIS_Version_Number = properties.getProperty("NGIS_Version_Number");
        return NGIS_Version_Number;
    }
    public static String getSearchTerm() {
        searchTerm = properties.getProperty("Search_Term");
        return searchTerm;
    }
}//end
