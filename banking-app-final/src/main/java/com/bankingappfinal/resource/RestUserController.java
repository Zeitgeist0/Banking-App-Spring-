package com.bankingappfinal.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/")
//@CrossOrigin(origins = {"http://localhost:3000"})
//public class RestUserController {
//
//  @GetMapping("/login")
//  public void basicAuth() {
//    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//    System.out.println(auth);
//
//  }
//}
