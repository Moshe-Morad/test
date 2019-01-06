package com.moshe.final_project2.entity;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;



public class ConvertFile {
	
    public static void main(String[] args) throws MalformedURLException {
        File file = new File("file:///Users/moshe-morad-macbookpro/Desktop/FinalProject/index.html");

        //Convert local path to URL
        URL url = file.toURI().toURL();
        System.out.println("2. URL of given file is:"+url);


    }

}
