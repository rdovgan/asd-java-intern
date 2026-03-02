package team.asd.entities;

public class TestPerson implements IsPerson {

	private String name;

	private Integer age;

	public TestPerson() {
	}

	public TestPerson(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
