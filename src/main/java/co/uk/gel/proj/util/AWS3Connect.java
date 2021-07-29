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
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import java.io.*;
import java.util.*;
import java.io.File;
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

            Debugger.println("AWS S3 is Connected.....");
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

    public static boolean checkFilePresenceAndDownload(String s3FolderName, String expectedFileName) {
        try {
            Debugger.println("Num of files in S3 bucket- " + s3FileNamesList.size());
            for (String fileName : s3FileNamesList) {
                if (!expectedFileName.equalsIgnoreCase(fileName)) {
                    Debugger.println("No Match found for the file- "+expectedFileName+ " ,in Folder- "+s3FolderName);
                    return false;
                }
            }
            Debugger.println("Match found.......Downloading now.............");
            downloadFileFromAWSS3(s3FolderName, expectedFileName);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from file checking.. " + exp);
            return false;
        }
    }

    public static boolean checkFilePresence(String s3FolderName, String expectedFileName) {
        try {
            Debugger.println("Num of files in S3 bucket- " + s3FileNamesList.size());
            for (String fileName : s3FileNamesList) {
                if (!expectedFileName.equalsIgnoreCase(fileName)) {
                    Debugger.println("No Match found for the file- "+expectedFileName+ " ,in Folder- "+s3FolderName);
                    return false;
                }
            }
            Debugger.println("File Match found....................");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from file checking.. " + exp);
            return false;
        }
    }

    public static void downloadFileFromAWSS3(String s3FolderName, String fileNameToDownload) {
        System.out.format("Downloading %s from S3 bucket %s...\n", fileNameToDownload, s3FolderName);
//        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
        try {
            String downloadFilepath = System.getProperty("user.dir") + File.separator + "target" + File.separator + fileNameToDownload;
            S3Object o = s3Client.getObject(s3FolderName, fileNameToDownload);
            S3ObjectInputStream s3is = o.getObjectContent();
            FileOutputStream fos = new FileOutputStream(new File(downloadFilepath));
            byte[] read_buf = new byte[1024];
            int read_len = 0;
            while ((read_len = s3is.read(read_buf)) > 0) {
                fos.write(read_buf, 0, read_len);
            }
            s3is.close();
            fos.close();
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        System.out.println("Download Done!");
    }

    public static String uploadFileToAwsS3(String s3FolderName, String fileName) {
        try {
            String filePath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + fileName;
            System.out.format("Uploading %s to S3 bucket %s...\n", filePath, s3FolderName);
            s3Client.putObject(s3FolderName, fileName, new File(filePath));
            return "Success";
        } catch (Exception exp) {
            return ("Exception from Uploading file to AWS S3- " + exp);
        }
    }

}//end
