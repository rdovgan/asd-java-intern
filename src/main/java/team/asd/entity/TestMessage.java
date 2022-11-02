package team.asd.entity;

import java.time.LocalDate;

public class TestMessage {
	private final LocalDate date;
	private final String content;

	public TestMessage(LocalDate date, String content) {
		this.date = date;
		this.content = content;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getContent() {
		return content;
	}
}
