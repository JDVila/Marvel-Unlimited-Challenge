package dev.jdvila.marvelunlimitedmark01.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5HashCreatorImp implements Md5HashCreator {

    @Override
    public String createHash(String timeStamp, String publicKey, String privateKey) {
        try {
            String value = timeStamp + privateKey + publicKey;
            MessageDigest md5Encoder = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5Encoder.digest(value.getBytes());
            StringBuilder md5 = new StringBuilder();
            for (byte b : md5Bytes) {
                md5.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
            }
            return md5.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error - Malformed MD5 Hash!", e);
        }
    }
}
