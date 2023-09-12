package com.spinetracker.spinetracker.global.security.command.application.controller;

import com.spinetracker.spinetracker.global.common.annotation.CurrentMember;
import com.spinetracker.spinetracker.global.security.token.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    public AuthController() {}

    @GetMapping("name")
    public ResponseEntity<?> issueToken (@CurrentMember UserPrincipal userPrincipal) {

        System.out.println("userPrincipal = " + userPrincipal);

        System.out.println("userPrincipal.getName() = " + userPrincipal.getName());

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("name", userPrincipal.getName());
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
