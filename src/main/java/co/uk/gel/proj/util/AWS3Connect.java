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

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AWS3Connect {

    private static String accessKey = AppConfig.getPropertyValueFromPropertyFile("ACCESS_KEY");
    private static String secretKey = AppConfig.getPropertyValueFromPropertyFile("SECRET_KEY");
    private static String hostName = AppConfig.getPropertyValueFromPropertyFile("HOST_NAME");

    private static AmazonS3 s3Client;

    public static List<String> s3FileNamesList = new ArrayList<>();

    public static boolean connectToS3Bucket() {
        try {

//            System.out.println("The accessKey:" + accessKey + " secret key:" + secretKey + " hostname:" + hostName);
            AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
            AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(hostName, Regions.DEFAULT_REGION.toString());
            s3Client = AmazonS3ClientBuilder
                    .standard()
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withEndpointConfiguration(endpointConfiguration)
                    .build();

            System.out.println("Connected.....");
            return true;
        } catch (Exception exp) {
            System.out.println("Exception in connection to Amazon S3 bucket...." + exp);
            return false;
        }
    }

//    public static void main(String args[]) {
//        connectToS3Bucket();
//    }

    public static String getFilesListInFolder(String s3FolderName) {
        try {
            if (!connectToS3Bucket()) {
                return "Failure in connecting to AWS S3...";
            }
            String bucket_name = s3FolderName;
            Debugger.println("Checking in folder- " + bucket_name);
            ListObjectsV2Result objectsInBucket = s3Client.listObjectsV2(bucket_name);
            List<S3ObjectSummary> fileObjects = objectsInBucket.getObjectSummaries();
            System.out.println("Number of Files in the given Bucket: " + fileObjects.size());
            for (S3ObjectSummary fileObjSummary : fileObjects) {
                s3FileNamesList.add(fileObjSummary.getKey());
//                if (fileObjSummary.getKey().equalsIgnoreCase("Deceased.pdf")) {
//                    System.out.println("* " + fileObjSummary.getKey());
//                }
            }
            return "Success";
        } catch (Exception exp) {
            return "Exception in reading file from AWS S3.." + exp;
        }
    }

    public static boolean checkFilePresence(String expectedFileName) {
        try {
            Debugger.println("In the matching section...");
            Collections.sort(s3FileNamesList);
            for (String fileName : s3FileNamesList) {
                if (expectedFileName.equalsIgnoreCase(fileName)) {
                    Debugger.println("Match found- ............");
                    return true;
                }
            }
            Debugger.println("No Match found....................");
            return false;
        } catch (Exception exp) {
            System.out.println("Exception from file checking.. " + exp);
            return false;
        }
    }


//    String file_path = "D:/STAG/DoSmartQA.txt";
//    String key_name = Paths.get(file_path).getFileName().toString();

//     s3Client.putObject(bucket_name, key_name, new File(file_path));
//            s3Client.deleteObject(bucket_name, key_name);


}//end
