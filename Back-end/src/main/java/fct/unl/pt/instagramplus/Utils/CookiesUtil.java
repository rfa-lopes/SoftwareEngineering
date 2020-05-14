package fct.unl.pt.instagramplus.Utils;

import javax.servlet.http.Cookie;

public class CookiesUtil {

    public static Cookie getCookie(String cookie, Cookie[] cookies){
        for(Cookie c : cookies){
            if(c.getName().equalsIgnoreCase(cookie))
                return c;
        }
        return null;
    }
}
