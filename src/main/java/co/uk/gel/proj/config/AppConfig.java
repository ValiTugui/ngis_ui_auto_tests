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
    static String concurrent_user1_username;
    static String concurrent_user1_password;
    static String concurrent_user2_username;
    static String concurrent_user2_password;
    static String concurrent_user3_username;
    static String concurrent_user3_password;
    static String concurrent_user4_username;
    static String concurrent_user4_password;
    static String concurrent_user5_username;
    static String concurrent_user5_password;
    private static String app_superUsername;
    private static String app_superPassword;
    private static String to_patient_search_url;
    private static String td_private_url;
    private static String to_dashboard_url;
    private static String MYNewName;
    public static String NGIS_Version_Number;
    private static String NGIS_Version_URL;
    public static String searchTerm;
    public static String panel_app_url;
    public static Properties properties = null;
    public static String BASE_URL_TS;
    public static String BASE_URL_TO;
    public static String BASE_URL_PA;
    public static String BASE_URL_PP;
    public static String BASE_URL_DS;
    public static String mi_portal_test_data_file;
    public static boolean snapshotRequired = false;

    public static void loadAppConfig() {
        String configFileName = "%s-appconfig.properties";
        String current_environment = System.getProperty("TestEnvironment");
        System.out.println("TestEnvironment: " + current_environment);
        if(current_environment.equalsIgnoreCase("UAT") ||
        current_environment.equalsIgnoreCase("Beta") ||
                current_environment.equalsIgnoreCase("Prod")){
            snapshotRequired = true;
        }
        configFileName = String.format(configFileName, current_environment);
        properties = new Properties();
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            try (InputStream resourceStream = loader.getResourceAsStream(configFileName)) {
                properties.load(resourceStream);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        app_url = properties.getProperty("APP_URL");
        app_username = properties.getProperty("APP_USERNAME");
        app_password = properties.getProperty("APP_PASSWORD");
        app_superUsername = properties.getProperty("SUPER_USERNAME");
        app_superPassword = properties.getProperty("SUPER_PASSWORD");
        concurrent_user1_username = properties.getProperty("CONCURRENT_USER1_NAME");
        concurrent_user1_password = properties.getProperty("CONCURRENT_USER1_PASSWORD");
        concurrent_user2_username = properties.getProperty("CONCURRENT_USER2_NAME");
        concurrent_user2_password = properties.getProperty("CONCURRENT_USER2_PASSWORD");
        concurrent_user3_username = properties.getProperty("CONCURRENT_USER3_NAME");
        concurrent_user3_password = properties.getProperty("CONCURRENT_USER3_PASSWORD");
        concurrent_user4_username = properties.getProperty("CONCURRENT_USER4_NAME");
        concurrent_user4_password = properties.getProperty("CONCURRENT_USER5_PASSWORD");
        concurrent_user5_username = properties.getProperty("CONCURRENT_USER5_NAME");
        concurrent_user5_password = properties.getProperty("CONCURRENT_USER6_PASSWORD");
        to_patient_search_url = properties.getProperty("TO_PATIENT_SEARCH_URL");
        td_private_url = properties.getProperty("TEST_DIRECTORY_PRIVATE_URL");
        to_dashboard_url = properties.getProperty("DASHBOARD_PRIVATE_URL");
        MYNewName = properties.getProperty("MYNewName");
        NGIS_Version_URL = properties.getProperty("NGIS_Version_URL");
        app_superUsername = properties.getProperty("SUPER_USERNAME");
        app_superPassword = properties.getProperty("SUPER_PASSWORD");
        panel_app_url = properties.getProperty("PANEL_APP_URL");
        mi_portal_test_data_file = properties.getProperty("MI_PORTAL_TEST_DATA_FILE");

        BASE_URL_TS =   properties.getProperty("BASE_URL_TS");
        BASE_URL_TO =  properties.getProperty("BASE_URL_TO");
        BASE_URL_PA =  properties.getProperty("BASE_URL_PA");
        BASE_URL_PP =  properties.getProperty("BASE_URL_PP");
        BASE_URL_DS =  properties.getProperty("BASE_URL_DS");

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

    public static String getPanel_app_url() {
        if (panel_app_url == null || panel_app_url.isEmpty()) {
            loadAppConfig();
        }
        return panel_app_url;
    }

    public static String getConcurrent_user1_username() {
        if(concurrent_user1_username == null ||
                concurrent_user1_username.isEmpty()){
            loadAppConfig();
        }
        return concurrent_user1_username;
    }

    public static String getConcurrent_user1_password() {
        return concurrent_user1_password;
    }

    public static String getConcurrent_user2_username() {
        if(concurrent_user2_username == null ||
                concurrent_user2_username.isEmpty()){
            loadAppConfig();
        }
        return concurrent_user2_username;
    }

    public static String getConcurrent_user2_password() {
        return concurrent_user2_password;
    }

    public static String getConcurrent_user3_username() {
        if(concurrent_user3_username == null ||
                concurrent_user3_username.isEmpty()){
            loadAppConfig();
        }
        return concurrent_user3_username;
    }

    public static String getConcurrent_user3_password() {
        return concurrent_user3_password;
    }

    public static String getConcurrent_user4_username() {
        if(concurrent_user4_username == null ||
                concurrent_user4_username.isEmpty()){
            loadAppConfig();
        }
        return concurrent_user4_username;
    }

    public static String getConcurrent_user4_password() {
        return concurrent_user4_password;
    }

    public static String getConcurrent_user5_username() {
        if(concurrent_user5_username == null ||
                concurrent_user5_username.isEmpty()){
            loadAppConfig();
        }
        return concurrent_user5_username;
    }

    public static String getConcurrent_user5_password() {
        return concurrent_user5_password;
    }
}//end
