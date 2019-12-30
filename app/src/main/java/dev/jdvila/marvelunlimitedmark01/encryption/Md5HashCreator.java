package dev.jdvila.marvelunlimitedmark01.encryption;

public interface Md5HashCreator {
    String createHash(String timestamp, String publicKey, String privateKey);
}
