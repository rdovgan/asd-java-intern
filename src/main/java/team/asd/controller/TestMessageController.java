package team.asd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team.asd.dao.TestDao;
import team.asd.entity.TestMessage;

import java.time.LocalDate;

@RestController
public class TestMessageController {

	@Autowired
	private TestDao testDao;

	@GetMapping("/test/message")
	public TestMessage sendTestMessage() {
		testDao.insertValue("test app");
		return new TestMessage(LocalDate.now(), "Test message from Spring Boot Application!");
	}
}
