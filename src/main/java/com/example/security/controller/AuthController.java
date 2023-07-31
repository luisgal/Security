package com.example.security.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin
public class AuthController {
    @GetMapping("/v1")
    public String noAuth(){
        return "Hola, no autentificado";
    }

    @GetMapping("/v2")
    public String needAuth(){
        return "Hola, autentificado";
    }

    @PostMapping("/v3")
    public String needAuthHello(@RequestBody RequestHello name){
        return "Hola, " + name.name;
    }

    @GetMapping("/v4")
    public String needAuthHelloUser(){
        return "Hola, USER";
    }

    record RequestHello(String name){}
}
