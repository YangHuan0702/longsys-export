package com.longsys.export.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author huan.yang
 * @date 2022-03-29
 */
@Controller
public class WizardController {

    @GetMapping("/")
    public String main(){
        return "main";
    }

}
