package team.asd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.asd.entity.Test;
import team.asd.entity.TestMessage;
import team.asd.mapper.TestMapper;

import java.time.LocalDate;

@RestController
public class TestMessageController {
    private final TestMapper testMapper;

    @Autowired
    public TestMessageController(TestMapper testMapper) {
        this.testMapper = testMapper;
    }

    @GetMapping("/test/message")
    public TestMessage sendTestMessage() {
        return new TestMessage(LocalDate.now(), "Test message from Spring Boot Application!");
    }

    @PostMapping("/test/create")
    public ResponseEntity<Object> createTest(@RequestBody Test test) {
        try {
            testMapper.insertValue(test.getValue());
            return new ResponseEntity<>(test, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
