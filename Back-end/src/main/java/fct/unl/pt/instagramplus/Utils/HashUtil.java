package fct.unl.pt.instagramplus.Utils;

import java.security.MessageDigest;

public class HashUtil {

    private static final String ALGORITHM = "SHA-256";
    private static MessageDigest hashF;

    static{
        try{
            hashF = MessageDigest.getInstance(ALGORITHM);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static byte[] getHash(byte[] input){
        return hashF.digest(input);
    }

    public static byte[] getHash(String input){
        return hashF.digest(input.getBytes());
    }

    public static int getHashSize(){
        return hashF.getDigestLength();
    }
}
