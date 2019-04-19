package com.qtu404.neptune.web.common.filter;

import com.google.common.base.Throwables;
import com.qtu404.neptune.common.constant.ConstantValues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.qtu404.neptune.web.common.util.RequestContext.setUuid;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/4/18
 */
@Component
@Slf4j
@WebFilter(urlPatterns = "/api/*", filterName = "currentUserFilter")
public class CurrentUserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String tokenValue = null;
        try {
            if (request instanceof HttpServletRequest) {
                HttpServletRequest httpRequest = (HttpServletRequest) request;
                Cookie[] cookies = httpRequest.getCookies();
                if (cookies!=null){
                    for (Cookie token : cookies) {
                        if (token != null && token.getName() != null && token.getName().equals(ConstantValues.UUID_PREFIX)) {
                            tokenValue = token.getValue();
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("failed to filter,cause:{}", Throwables.getStackTraceAsString(e));
        }
        setUuid(tokenValue);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
