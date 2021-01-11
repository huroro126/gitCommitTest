
package com.club.friends.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {

    @RequestMapping(value = "/index")
    public String index(){
        return "test/index";
    }

    @RequestMapping(value = "/index2")
    public String index2(){
        return "test/index";
    }

}

