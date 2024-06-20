package com.example.usermanagementservice.Config;

import com.example.usermanagementservice.Util.JwtUtil;
import jakarta.servlet.*;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;

@EnableWebSecurity
public class JwtRequestFilter implements Filter {
    public JwtRequestFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
