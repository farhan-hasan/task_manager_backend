package com.example.task_manager_backend.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_GATEWAY;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class ResponseWriter {

    public static void writeResponse(HttpServletResponse response,String header, Exception e) throws IOException {
        response.setHeader(header, e.getMessage());
        response.setStatus(BAD_GATEWAY.value());
        Map<String,String> data = new HashMap<>();
        data.put(header + "_message", e.getMessage());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), data);
    }

}
