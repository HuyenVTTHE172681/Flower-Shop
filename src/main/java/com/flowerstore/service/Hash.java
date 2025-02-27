package com.flowerstore.service;

import java.security.*;

public class Hash {

     public static String hashCode(String message) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");

            byte[] hash = sha256.digest(message.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(hashCode("12345"));
    }
}
