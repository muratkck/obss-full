package com.day2.demo.filter;

//import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
public class RequestLogFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req,
                            HttpServletResponse res,
                            FilterChain chain) throws IOException, ServletException {
        /*
        if (res instanceof HttpServletResponse) {
            // do some works
            // chain.doFilter(req, res);
            // do some works if necessary
        } else {
            chain.doFilter(req, res);
        }
        */
        log.info("user trying to access: {}", req.getRequestURL());
        chain.doFilter(req, res);
    }
}
