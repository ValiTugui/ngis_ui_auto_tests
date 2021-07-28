package co.uk.gel.proj.util;

import co.uk.gel.proj.config.AppConfig;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.nio.file.Paths;
import java.util.List;


public class AWS3Connect {

    @FindBy(xpath = "//object")
    public WebElement uploadedFileName;
    String pdfFileName = uploadedFileName.getAttribute("data");

    public static boolean connectToS3Bucket() {
        try {
            String accessKey = AppConfig.getPropertyValueFromPropertyFile("ACCESS_KEY");
            String secretKey = AppConfig.getPropertyValueFromPropertyFile("SECRET_KEY");
            String hostName = AppConfig.getPropertyValueFromPropertyFile("HOST_NAME");
            System.out.println("The accessKey:" + accessKey + " secret key:" + secretKey + " hostname:" + hostName);

            AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
            AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(hostName, Regions.DEFAULT_REGION.toString());

//           String bucket_name = "patient-records";
            String bucket_name = "formLibrary";

            String file_path = "D:/STAG/DoSmartQA.txt";
            String key_name = Paths.get(file_path).getFileName().toString();
            AmazonS3 s3Client = AmazonS3ClientBuilder
                    .standard()
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withEndpointConfiguration(endpointConfiguration)
                    .build();

            ListObjectsV2Result result = s3Client.listObjectsV2(bucket_name);
            List<S3ObjectSummary> objects = result.getObjectSummaries();
            System.out.println("Number of Files in the given Bucket: " + objects.size());
            for (S3ObjectSummary os : objects) {
                if (os.getKey().equalsIgnoreCase("Deceased.pdf")) {
                    System.out.println("* " + os.getKey());
                }

            }

            //s3Client.putObject(bucket_name, key_name, new File(file_path));
            //s3Client.deleteObject(bucket_name, key_name);
            System.out.println("Connected.....");
            return true;
        } catch (Exception exp) {
            System.out.println("Connect Exception...." + exp);
            return false;
        }

    }

    public static void main(String args[]) {
        connectToS3Bucket();
    }

}
