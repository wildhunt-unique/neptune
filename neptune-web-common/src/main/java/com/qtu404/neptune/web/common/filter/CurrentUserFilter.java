package com.qtu404.neptune.web.common.filter;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.facade.UserFacade;
import com.qtu404.neptune.api.request.user.UserGetFromRedisRequest;
import com.qtu404.neptune.api.response.user.UserThinResponse;
import com.qtu404.neptune.common.constant.ConstantValues;
import com.qtu404.neptune.util.model.Response;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

import static com.qtu404.neptune.web.common.util.UserUtils.setUser;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/4/18
 */
@Component
@WebFilter(urlPatterns = "/api/*",filterName = "currentUserFilter")
public class CurrentUserFilter implements Filter {
    @Reference
    private UserFacade userFacade;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      if (request instanceof HttpServletRequest){
          HttpServletRequest httpRequest = (HttpServletRequest)request;
          Cookie[] cookies =  httpRequest.getCookies();
          String tokenValue = null;
          for (Cookie token :cookies){
              if (token!=null && token.getName().equals(ConstantValues.UUID_PREFIX)){
                  tokenValue = token.getValue();
                  break;
              }
          }
          if (Objects.isNull(tokenValue)){
              setUser(null);
          }else {
              Response<UserThinResponse> userThinResponse = this.userFacade.getFromRedis(UserGetFromRedisRequest.builder().key(tokenValue).build());
              if (userThinResponse.isSuccess() && userThinResponse.getResult()!=null){
                  setUser(userThinResponse.getResult());
              }else {
                  setUser(null);
              }
          }
      }
    }

    @Override
    public void destroy() {

    }
}
