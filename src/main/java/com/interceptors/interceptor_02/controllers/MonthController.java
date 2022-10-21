package com.interceptors.interceptor_02.controllers;

import com.interceptors.interceptor_02.entities.Month;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/months")
public class MonthController {

    @GetMapping("")
    public Month getMonth(HttpServletRequest request) {
        return (Month) request.getAttribute("MonthInterceptor-month");
    }
}
