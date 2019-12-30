package dev.jdvila.marvelunlimitedmark01;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import dev.jdvila.marvelunlimitedmark01.encryption.Md5HashCreator;
import dev.jdvila.marvelunlimitedmark01.encryption.Md5HashCreatorImp;

import static org.junit.Assert.assertTrue;

public class Md5HashCreatorImpUnitTest {

    private Md5HashCreator md5HashCreator;
    private Long timeStamp;
    private String dummyPublicKey;
    private String dummyPrivateKey;

    @Before
    public void setUp() throws Exception {
        md5HashCreator = new Md5HashCreatorImp();
        timeStamp = new Date().getTime();
        dummyPublicKey = "12345678a1234a123456b12345678c0d";
        dummyPrivateKey = "1234a1234b1234c1234d1234e1234f1234g1234h";
    }

    @Test
    public void createHash_isCharacterLengthEqualTo32_True() {
        String timeStampString = Long.toString(timeStamp);
        int md5HashLength = md5HashCreator.createHash(timeStampString, dummyPublicKey, dummyPrivateKey).length();
        Assert.assertEquals(32, md5HashLength);
    }

    @Test
    public void createHash_isReturnValueOnlyLettersAndNumbers_True() {
        String pattern = "^[a-zA-Z0-9]*$";
        String timeStampString = Long.toString(timeStamp);
        String md5Hash = md5HashCreator.createHash(timeStampString, dummyPublicKey, dummyPrivateKey);
        assertTrue(md5Hash.matches(pattern));
    }

    @After
    public void tearDown() throws Exception {
        md5HashCreator = null;
        timeStamp = null;
        dummyPublicKey = null;
        dummyPrivateKey = null;
    }
}
