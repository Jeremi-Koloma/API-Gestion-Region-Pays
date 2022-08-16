package com.myCompagny.Apigestionregions.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class SwaggerController {
    @RequestMapping(method = RequestMethod.GET)
    public String swaggerui(){
        return "redirect:/swagger-ui.html";
    }
}
