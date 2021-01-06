package com.lcy.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping(value = "index")
    public String index(){
        return "index";
    }

    @GetMapping(value = "lovePenny")
    public String love1(){
        return "1/Love";
    }

    @GetMapping(value = "pennyLovely")
    public String love2(){
        return "2/index";
    }

    @GetMapping(value = "love3")
    public String love3(){
        return "3/index";
    }

    @GetMapping(value = "love4-1")
    public String love41(){
        return "4/index1";
    }

    @GetMapping(value = "love4-2")
    public String love42(){
        return "4/index2";
    }

    @GetMapping(value = "love4-3")
    public String love4(){
        return "4/index3";
    }

    @GetMapping(value = "love5")
    public String love5(){
        return "5/index";
    }

    @GetMapping(value = "love6")
    public String love6(){
        return "6/index";
    }

    @GetMapping(value = "love7")
    public String love7(){
        return "7/index";
    }

    @GetMapping(value = "love8")
    public String love8(){
        return "8/index";
    }

}
