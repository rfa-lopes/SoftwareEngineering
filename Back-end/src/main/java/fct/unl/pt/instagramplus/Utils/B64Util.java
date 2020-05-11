package fct.unl.pt.instagramplus.Utils;

import java.util.Base64;

public class B64Util {

    public static String encode(byte[] input){
        return Base64.getEncoder().encodeToString(input);
    }

    public static String encode(String input){
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    public static byte[] decode(byte[] input){
        return Base64.getDecoder().decode(input);
    }

    public static byte[] decode(String input){
        return Base64.getDecoder().decode(input);
    }
}
