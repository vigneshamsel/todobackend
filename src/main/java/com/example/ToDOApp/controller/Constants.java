package com.example.ToDOApp.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Constants {
        public static final String Cr;

        static {
            String tempCr;
            try {
                Process process = Runtime.getRuntime().exec("aws --version");
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line = reader.readLine();
                if (line != null && line.contains("aws-cli")) {
                    tempCr = "https://todoapp.online/";
                } else {
                    tempCr = "http://localhost:4200";
                }
            } catch (Exception e) {
                tempCr = "http://localhost:4200"; // Default to local if there's an exception
            }
            Cr = tempCr; // Assign the final value to Cr
        }


}
