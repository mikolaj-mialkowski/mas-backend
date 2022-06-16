package pjatk.mas_backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;


@Controller
@RequestMapping("${mas.backend.basePath}")
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<String> getTestResponse(){
        String response = (LocalDateTime.now()) + " " + (Math.random() * 100);
        return ResponseEntity.ok(response);
    }

}
