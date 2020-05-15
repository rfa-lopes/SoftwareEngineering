package fct.unl.pt.instagramplus.AppConfigs;

import fct.unl.pt.instagramplus.Filters.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class AppConfigs {

    private @Autowired
    AutowireCapableBeanFactory beanFactory;

    @Bean
    public FilterRegistrationBean authenticatorFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        Filter authFilter = new AuthFilter();
        beanFactory.autowireBean(authFilter);

        registrationBean.setFilter(authFilter);
        registrationBean.addUrlPatterns(
                "/messages/*",
                "/accounts/*",
                "/publications/*",
                "/auth/logout");

        return registrationBean;
    }

}
