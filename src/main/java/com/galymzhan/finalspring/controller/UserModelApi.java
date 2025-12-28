package com.galymzhan.finalspring.controller;

import com.galymzhan.finalspring.entity.UserEntity;
import com.galymzhan.finalspring.service.CityService;
import com.galymzhan.finalspring.service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserModelApi {

    private final MyUserService myUserService;
    private final CityService cityService;

    @GetMapping
    public String get123() {
        return "test";
    }

    @PostMapping("/register")
    public void registr(@RequestBody UserEntity model) {
        myUserService.registr(model);
    }

    @GetMapping("/cities")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(cityService.getAll(), HttpStatus.OK);
    }
}