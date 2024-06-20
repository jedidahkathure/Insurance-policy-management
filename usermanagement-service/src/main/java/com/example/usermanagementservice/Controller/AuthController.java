package com.example.usermanagementservice.Controller;

import com.example.usermanagementservice.Model.User;
import com.example.usermanagementservice.Service.UserService;
import com.example.usermanagementservice.Util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


        private final UserService userService;
        private final JwtUtil jwtUtil;
        private final AuthenticationManager authenticationManager;

        public AuthController(UserService userService, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
            this.userService = userService;
            this.jwtUtil = jwtUtil;
            this.authenticationManager = authenticationManager;
        }

        @PostMapping("/register")
        public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
            String username = request.get("username");
            String email = request.get("email");
            String password = request.get("password");
            try {
                com.example.usermanagementservice.Model.User user = userService.registerUser(username, email, password);
                return ResponseEntity.ok(user);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
            String username = request.get("username");
            String password = request.get("password");
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                final UserDetails userDetails = userService.loadUserByUsername(username);
                final String jwt = jwtUtil.generateToken(userDetails.getUsername());
                return ResponseEntity.ok(Map.of("token", jwt));
            } catch (Exception e) {
                return ResponseEntity.status(401).body("Invalid credentials");
            }
        }

        @GetMapping("/profile")
        public ResponseEntity<?> profile(@RequestHeader("Authorization") String token) {
            try {
                String username = jwtUtil.extractUsername(token.substring(7));
                User user = userService.findUserByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
                return ResponseEntity.ok(user);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token or user not found");
            }
        }

}


