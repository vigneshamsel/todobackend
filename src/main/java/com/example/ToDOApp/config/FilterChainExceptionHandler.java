package com.example.ToDOApp.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;

import java.util.List;

@Component
public class FilterChainExceptionHandler extends HandlerExceptionResolverComposite {

    @Autowired
    public FilterChainExceptionHandler(List<HandlerExceptionResolver> exceptionResolvers) {
        setExceptionResolvers(exceptionResolvers);
    }

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception ex) {
        return super.resolveException(request, response, null, ex);
    }
}
