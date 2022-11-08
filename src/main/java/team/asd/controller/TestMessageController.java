package team.asd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team.asd.entity.TestMessage;
import team.asd.mapper.TestMapper;

import java.time.LocalDate;

@RestController
public class TestMessageController {

	@GetMapping("/test/message")
	public TestMessage sendTestMessage() {
		return new TestMessage(LocalDate.now(), "Test message from Spring Boot Application!");
	}
}
