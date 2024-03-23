package com.pablo.interserctor.springbootintersector.intercectors;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("loadingtimeinterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HandlerMethod controller = ((HandlerMethod) handler);
        logger.info("Loading time intersection: pre hadle lasiendo ...." + controller.getMethod().getName());
        long start = System.currentTimeMillis();
        request.setAttribute("start", start);
        Random random = new Random();
        var delay = random.nextInt(200);
        Thread.sleep(delay);
        // Map<String, Object> json = new HashMap<String,Object>(); 
        // json.put("Error", "No tienes acceso a esta pagina");
        // json.put("date", new Date().toString());
        // ObjectMapper mapper = new ObjectMapper();
        // var jsonString = mapper.writeValueAsString(json);
        // response.setContentType("application/json");
        // response.setStatus(HttpStatus.BAD_REQUEST.value());
        // response.getWriter().write(jsonString);
        // return false;
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
                long end = System.currentTimeMillis();
                long start = (long) request.getAttribute("start");
                var result = end - start;
                logger.info("Tiempo transcurrido: "+result);
        logger.info("Loading time intersection: Post hadle lasiendo ...."
                + ((HandlerMethod) handler).getMethod().getName());
    }

}
