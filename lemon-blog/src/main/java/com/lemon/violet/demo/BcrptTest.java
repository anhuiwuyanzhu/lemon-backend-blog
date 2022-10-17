package com.lemon.violet.demo;

import com.lemon.violet.utils.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcrptTest {
    public static void main(String[] args) {
        String jwt = JwtUtil.createJWT("3");
        System.out.println(jwt);
    }
}
