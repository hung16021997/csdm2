package com.codegym;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class EncodingFilter extends GenericFilterBean {

    // set UTF-8 after apply security
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            chain.doFilter(request, response);
    }
}