/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.configuration;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import static org.mariadb.jdbc.internal.com.send.authentication.ed25519.Utils.bytesToHex;

/**
 *
 * @author huba
 */
public class MyUUIDWrapper {
    public static String getUUIDV5() throws NoSuchAlgorithmException, UnsupportedEncodingException{
    
        UUID uuid = UUID.randomUUID();

        MessageDigest salt = MessageDigest.getInstance("SHA-256");
        salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
        String digest = bytesToHex(salt.digest());

        return digest;
    }
}
