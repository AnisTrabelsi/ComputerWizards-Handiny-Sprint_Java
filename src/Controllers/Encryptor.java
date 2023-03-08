/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

/**
 *
 * @author Chayma
 */
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {
  public String encryptString(String input) throws NoSuchAlgorithmException {
 if (input == null) {
        return null;
    }
    try {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] messageDigest = md.digest(input.getBytes("UTF-8"));
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", b));
        }
        return hexString.toString();
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
        // handle the exception, for example:
        System.out.println("Error: " + e.getMessage());
        return null;
    }}
}

