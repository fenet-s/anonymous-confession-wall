package com.confessionwall.filters; // or com.confessionwall.filters



import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@jakarta.servlet.annotation.WebFilter("/*") // This filter will apply to all requests
public class CORSFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) 
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // Allow requests from your React app's origin (change if your port is different)
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000"); 
        
        // Allow common HTTP methods
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        
        // Allow common headers
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        
        // Allow credentials (cookies, etc.)
        response.setHeader("Access-Control-Allow-Credentials", "true");

        // Continue the request chain
        chain.doFilter(req, res);
    }

    // You can leave these methods empty
    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() {}
}