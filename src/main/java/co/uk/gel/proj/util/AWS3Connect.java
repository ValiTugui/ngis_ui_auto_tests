package co.uk.gel.proj.util;

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
import java.util.List;

public class AWS3Connect {

 public static String connectToS3Bucket(){
     try{
         AWSCredentials credentials = new BasicAWSCredentials("235-365-35-08b36c-3","MhYmDQbGIrdXaQXB/iHWkUZRJdIonX/LpYBUAGse");
         AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration("https://cas.cor00005.ukcloud.com", Regions.DEFAULT_REGION.toString());
         //String bucket_name = "patient-records";
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
         System.out.println("Number of Files in the given Bucket: "+objects.size());
         for (S3ObjectSummary os : objects) {
             System.out.println("* " + os.getKey());
         }

        //s3Client.putObject(bucket_name, key_name, new File(file_path));
         //s3Client.deleteObject(bucket_name, key_name);
         System.out.println("Connected.....");
         return "Success";
     }catch(Exception exp){
         System.out.println("Connect Exception...."+exp);
         return "Exception in S3 Connect: "+exp;
     }

 }

 public static void main(String args[]){
     connectToS3Bucket();
 }

}
