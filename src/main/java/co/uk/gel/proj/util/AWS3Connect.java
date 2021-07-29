package co.uk.gel.proj.util;

import co.uk.gel.proj.config.AppConfig;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.util.*;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;


public class AWS3Connect {

    private static String accessKey = AppConfig.getPropertyValueFromPropertyFile("ACCESS_KEY");
    private static String secretKey = AppConfig.getPropertyValueFromPropertyFile("SECRET_KEY");
    private static String hostName = AppConfig.getPropertyValueFromPropertyFile("HOST_NAME");

    private static AmazonS3 s3Client;

    public static List<String> s3FileNamesList = new ArrayList<>();

    public static boolean connectToS3Bucket() {
        try {

//            Debugger.println("The accessKey:" + accessKey + " secret key:" + secretKey + " hostname:" + hostName);
            AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
            AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(hostName, Regions.DEFAULT_REGION.toString());
            s3Client = AmazonS3ClientBuilder
                    .standard()
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withEndpointConfiguration(endpointConfiguration)
                    .build();

            Debugger.println("Connected.....");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in connection to Amazon S3 bucket...." + exp);
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

            ObjectListing listOfObjectsInBucket = s3Client.listObjects(bucket_name);
            List<S3ObjectSummary> fileObjectsSummaries = listOfObjectsInBucket.getObjectSummaries();
            while (listOfObjectsInBucket.isTruncated()) {
                listOfObjectsInBucket = s3Client.listNextBatchOfObjects(listOfObjectsInBucket);
                fileObjectsSummaries.addAll(listOfObjectsInBucket.getObjectSummaries());
            }
            Debugger.println("Number of Files in the given Bucket: " + fileObjectsSummaries.size());

            for (S3ObjectSummary fileObjSummary : fileObjectsSummaries) {
                s3FileNamesList.add(fileObjSummary.getKey());
            }
            return "Success";
        } catch (Exception exp) {
            return "Exception in reading file from AWS S3.." + exp;
        }
    }

    public static boolean checkFilePresence(String expectedFileName) {
        try {
            Debugger.println("In the matching section...");
            Debugger.println("Num of files in S3 bucket- " + s3FileNamesList.size());
            for (String fileName : s3FileNamesList) {
                if (expectedFileName.equalsIgnoreCase(fileName)) {
                    Debugger.println("Match found- ............");
                    return true;
                }
            }
            Debugger.println("No Match found....................");
            return false;
        } catch (Exception exp) {
            Debugger.println("Exception from file checking.. " + exp);
            return false;
        }
    }


//    String file_path = "D:/STAG/DoSmartQA.txt";
//    String key_name = Paths.get(file_path).getFileName().toString();

//     s3Client.putObject(bucket_name, key_name, new File(file_path));
//            s3Client.deleteObject(bucket_name, key_name);


}//end
