package com.interceptors.interceptor_02.interceptors;

import com.interceptors.interceptor_02.entities.Month;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class MonthInterceptor implements HandlerInterceptor {

    public static List<Month> months = new ArrayList<>(Arrays.asList(
            new Month(1, "January", "Gennaio", "Januar"),
            new Month(2, "February", "Febbraio", "Februar"),
            new Month(3, "March", "Marzo", "Marz"),
            new Month(4, "April", "Aprile", "April"),
            new Month(5, "May", "Maggio", "Mai"),
            new Month(6, "June", "Giugno", "Juni")
    ));

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String monthNumber = request.getHeader("monthNumber");
        if (monthNumber != null) {
            int intNumber = Integer.parseInt(monthNumber);
            Optional<Month> monthFound = months.stream()
                    .filter(singleMonth -> singleMonth.getMonthNumber() == intNumber)
                    .findFirst();
            if (monthFound.isPresent()) {
                request.setAttribute("MonthInterceptor-month", monthFound.get());
            } else {
                request.setAttribute("MonthInterceptor-month",
                        new Month(0, "nope", "nope", "nope"));
            }
            response.setStatus(200);
            return true;
        } else {
            response.setStatus(400);
            return false;
        }
    }
}
