package co.develhope.interceptormiddleware02.interceptors;

import co.develhope.interceptormiddleware02.entities.Month;
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

    //list of 6 months
    public static List<Month> months = new ArrayList<>(Arrays.asList(
            new Month(1, "Gennaio", "January", "der Januar"),
            new Month(2, "Febbraio", "February", "der Februar"),
            new Month(3, "Marzo", "May", "der März"),
            new Month(4, "Aprile", "April", "der April"),
            new Month(5, "Maggio", "May", "der Mai"),
            new Month(6, "Giugno", "June", "der Juni")
    ));

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String monthNumberString = request.getHeader("monthNumber");
        if (monthNumberString == null || monthNumberString.isEmpty()){
            response.setStatus(400);
            request.getHeader("BAD REQUEST");
            return true;
        }
        int monthNumber = Integer.parseInt(monthNumberString);
        Optional<Month> month = months.stream().filter(SingleMonth ->{
            return SingleMonth.getMonthNumber() == monthNumber;
        }).findAny();

        if(month.isPresent()){
            request.setAttribute("MonthInterceptor",month.get());
        }

        if(month.isEmpty()){
            request.setAttribute("MonthInterceptor",
                    new Month(0,"nope","nope","nope"));
        }
        return true;
    }
}
