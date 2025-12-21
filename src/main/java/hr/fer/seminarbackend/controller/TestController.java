package hr.fer.seminarbackend.controller;

import hr.fer.seminarbackend.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @PostMapping("/")
    public ResponseEntity<Double> getSquare(@RequestBody Double base){
        return new ResponseEntity<>(testService.getSquare(base), HttpStatus.OK);
    }
}
