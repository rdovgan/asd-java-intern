package team.asd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("team.asd.mapper")
public class InternApplication {

	public static void main(String[] args) {
		SpringApplication.run(InternApplication.class, args);
	}
}
