package fct.unl.pt.instagramplus.Filters;


import fct.unl.pt.instagramplus.Controllers.Authenticator.AuthenticatorInterface;
import fct.unl.pt.instagramplus.Repositories.Accounts.AccountsRepository;
import fct.unl.pt.instagramplus.Utils.CookiesUtil;
import fct.unl.pt.instagramplus.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthFilter implements Filter {

    @Autowired
    private AccountsRepository acc;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        try {
            Cookie token = CookiesUtil.getCookie(AuthenticatorInterface.TOKEN_NAME, req.getCookies());
            Long id = Long.parseLong(JwtUtil.parseJWT(token.getValue()));
            if(acc.getAccountById(id)==null)
                throw new Exception();
            req.setAttribute("id", id);
            chain.doFilter(request, response);
        }catch (Exception e){
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
