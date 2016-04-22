package by.epam.lab.task1.md5util;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * Utility which is used for encryption of users' passwords *
 * @author Ivan Dzinhala
 */
public class MD5Hashing {
    public static String md5(String input) {

        String md5 = null;

        if(null == input) return null;

        try {

            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());

            //Converts message digest value in base 16 (hex)
            md5 = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such algorithm in md5hash");
            e.printStackTrace();
        }
        return md5;
    }
}
