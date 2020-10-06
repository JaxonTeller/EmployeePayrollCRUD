package com.employeepayroll.crud.ModifiedError;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class ErrorModifier implements ErrorController {


    private ErrorAttributes errorAttributes;

    public ErrorModifier(ErrorAttributes errorAttributes)
    {
        this.errorAttributes=errorAttributes;
    }


    @GetMapping("/error")
    public Map<String, Object> handleError(HttpServletRequest httpServletRequest)
    {
        ServletWebRequest servletWebRequest=new ServletWebRequest(httpServletRequest);
       return this.errorAttributes.getErrorAttributes(servletWebRequest,
                ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE,
                        ErrorAttributeOptions.Include.STACK_TRACE,
                ErrorAttributeOptions.Include.EXCEPTION));

    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
