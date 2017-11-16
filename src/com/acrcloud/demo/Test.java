package com.acrcloud.demo; /**
 *
 *  @author qinxue.pan E-mail: xue@acrcloud.com
 *  @version 1.0.0
 *  @create 2015.10.01
 *  
 **/

import java.io.*;
import java.util.Map;
import java.util.HashMap;

import com.acrcloud.utils.ACRCloudRecognizer;
import com.acrcloud.utils.ACRCloudExtrTool;

public class Test {

    static String filePath = "C:\\Users\\Jordan\\Downloads\\Robin Schulz - Oh Child.mp3";

    public static void main(String[] args) {
        Map<String, Object> config = new HashMap<String, Object>();
        // Replace "xxxxxxxx" below with your project's access_key and access_secret.
        config.put("host",
                "identify-us-west-2.acrcloud.com");
        config.put("access_key", "ad95b6fe7fd55053b53241195ff117f7");
        config.put("access_secret", "EIQbfsRSZSxFSqUua0TzI0qj7gKSUvjSU66nDtk5");
        config.put("debug", false);
        config.put("timeout", 10); // seconds

        ACRCloudRecognizer re = new ACRCloudRecognizer(config);


        /**
          *   
          *  recognize by file path of (Formatter: Audio/Video)
          *     Audio: mp3, wav, m4a, flac, aac, amr, ape, ogg ...
          *     Video: mp4, mkv, wmv, flv, ts, avi ...
          *     
          * 
         **/
        // It will skip 0 seconds.
        String result = re.recognizeByFile(filePath, 0);
        System.out.println(result);


        int fileDurationMS = ACRCloudExtrTool.getDurationMSByFile(filePath);
        System.out.println("duration_ms = "+fileDurationMS);



        /**
          *   
          *  recognize by buffer of (Formatter: Audio/Video)
          *     Audio: mp3, wav, m4a, flac, aac, amr, ape, ogg ...
          *     Video: mp4, mkv, wmv, flv, ts, avi ...
          *     
          * 
          **/
        File file = new File(filePath);
        byte[] buffer = new byte[(int)file.length()];
        if (!file.exists()) {
            return;
        }
        FileInputStream fin = null;
        int bufferLen = 0;
        try {
            fin = new FileInputStream(file);
            bufferLen = fin.read(buffer, 0, buffer.length);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fin != null) {
                	fin.close();
                }
            } catch (IOException e) {
            	e.printStackTrace();
            }
        }
        System.out.println("bufferLen=" + bufferLen);
        
        if (bufferLen <= 0)
            return;

        // It will skip 80 seconds from the beginning of (buffer).
        result = re.recognizeByFileBuffer(buffer, bufferLen, 0);
        System.out.println(" RECOGNIZED " + result);


        // recognize by wav audio buffer(RIFF (little-endian) data, WAVE audio, Microsoft PCM, 16 bit, mono 8000 Hz) 
        //File file = new File("test.wav");
        //byte[] buffer = new byte[12 * 8000 * 2];
        //if (!file.exists()) {
        //    return;
        //}
        //FileInputStream fin = null;
        //int bufferLen = 0;
        //try {
        //    fin = new FileInputStream(file);
        //    fin.skip(44);
        //    bufferLen = fin.read(buffer, 0, buffer.length);
        //} catch (Exception e) {
        //    e.printStackTrace();
        //} finally {
        //    try {
        //        if (fin != null) {
        //        	fin.close();
        //        }
        //    } catch (IOException e) {
        //    	e.printStackTrace();
        //    }
        //}
        //System.out.println("bufferLen=" + bufferLen);
        //
        //if (bufferLen <= 0)
        //    return;

        //String result = re.recognize(buffer, bufferLen);
        //System.out.println(result);
    }
}
