package fct.unl.pt.instagramplus.Utils;

public class PasswordUtil {

    public static String create(String password){
        return B64Util.encode(HashUtil.getHash(password.getBytes()));
    }

    public static boolean verify(String hash, String password){
        String genHash =  B64Util.encode(HashUtil.getHash(password.getBytes()));
        return hash.equals(genHash);
    }

}
