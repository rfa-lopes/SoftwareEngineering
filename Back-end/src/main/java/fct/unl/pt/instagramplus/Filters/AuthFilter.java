package fct.unl.pt.instagramplus.Filters;


import fct.unl.pt.instagramplus.Controllers.Authenticator.AuthenticatorInterface;
import fct.unl.pt.instagramplus.Utils.CookiesUtil;
import fct.unl.pt.instagramplus.Utils.JwtUtil;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        try {
            Cookie token = CookiesUtil.getCookie(AuthenticatorInterface.TOKEN_NAME, req.getCookies());
            Long id = Long.parseLong(JwtUtil.parseJWT(token.getValue()));
            req.setAttribute("id", id);
            chain.doFilter(request, response);
        }catch (Exception e){
            res.setStatus(401);
        }
    }
}
