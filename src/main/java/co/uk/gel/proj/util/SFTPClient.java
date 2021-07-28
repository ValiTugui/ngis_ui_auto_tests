package co.uk.gel.proj.util;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class SFTPClient {


    private Session session;

    private String userName = "235-365-35-08b36c-3"; //env.getProperty("sftp_user");
    private String hostAddress = "cas.cor00005.ukcloud.com"; //env.getProperty("sftp_server");
    private String password = "MhYmDQbGIrdXaQXB/iHWkUZRJdIonX/LpYBUAGse"; //env.getProperty("sftp_password");


    private static final Logger log = LoggerFactory.getLogger(SFTPClient.class);

    public boolean connectSftp() {
        try {
            JSch jsch = new JSch();
            // Open a session with SFTP server and user details based on the environment used
            session = jsch.getSession(userName, hostAddress);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            Debugger.println("SFTP is successfully connected");
            System.out.println("SFTP is successfully connected");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception while executing SFTP connect method: " + exp);
            return false;
        }
    }

    //source is full path including filename and destination is only the path
    public boolean downloadFromSFTP(String source, String destination) {
        ChannelSftp sftpChannel = null;
        try {
            if (!connectSftp()) {
                return false;
            }
            //Create SFTP channel and connect
            sftpChannel = (ChannelSftp) session.openChannel("sftp");
            sftpChannel.connect();
            //Download file from SFTP source to local destination (path only needed)
            sftpChannel.get(source, destination);
            Debugger.println("Download from SFTP finished");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from download method: " + exp);
            return false;
        } finally {
            if (sftpChannel != null) {
                sftpChannel.exit();
            }
            if (session != null) {
                session.disconnect();
            }
        }
    }

    //source is full path including filename and destination is only the path where file is to be uploaded
    public boolean uploadToSFTP(String source, String destination) {
        ChannelSftp sftpChannel = null;
        try {
            if (!connectSftp()) {
                return false;
            }
            //File check at local
            File uploadFile = new File("source");
            if (!uploadFile.exists()) {
                Debugger.println("File to upload does Not exist at local location: " + source);
                return false;
            }
            //Create SFTP channel and connect
            sftpChannel = (ChannelSftp) session.openChannel("sftp");
            sftpChannel.connect();
            //Upload file from local source to SFTP destination
            sftpChannel.put(source, destination);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from Upload method: " + exp);
            return false;
        } finally {
            if (sftpChannel != null) {
                sftpChannel.exit();
            }
            if (session != null) {
                session.disconnect();
            }
        }
    }

    public List<String> getFileNamesInSFTPDir(String path) {
        ChannelSftp sftpChannel = null;
        List<String> fileNames = new ArrayList<>();
        System.out.println("The file reading has been started");
        try {
            if (!connectSftp()) {
                System.out.println("Unable to connect");
                return null;
            }
            //Create SFTP channel and connect
            sftpChannel = (ChannelSftp) session.openChannel("sftp");
            sftpChannel.connect();
            //Get the list of all files inside the path provided
            Vector<ChannelSftp.LsEntry> fileList = sftpChannel.ls(path);
            Debugger.println("The num. of files read:" + fileList.size());
            for (ChannelSftp.LsEntry fileEntries : fileList) {
                fileNames.add(fileEntries.getFilename());
            }
            Debugger.println("Reading files from SFTP path:" + path + " completed");
            System.out.println("File reading completed");
            return fileNames;
        } catch (Exception exp) {
            Debugger.println("Exception from getFilesInSFTPDir " + exp);
            return null;
        } finally {
            if (sftpChannel != null) {
                sftpChannel.exit();
            }
            if (session != null) {
                session.disconnect();
            }
        }
    }

}
