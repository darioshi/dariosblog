package blog.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 */
public class MD5Util {
    /**
     * Encodes a string 2 MD5
     * @param str String to encode
     * @return Encoded String
     * @throws NoSuchAlgorithmException
     */
    public static String encode(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] hash = md.digest();
            for (byte aHash : hash) {
                if ((0xff & aHash) < 0x10) {
                    hexString.append("0").append(Integer.toHexString((0xFF & aHash)));
                } else {
                    hexString.append(Integer.toHexString(0xFF & aHash));
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hexString.toString();
    }
}
