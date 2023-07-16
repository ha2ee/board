package hello.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BoardApplication {

	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.yml,"
			+ "/home/ec2-user/app/config/cicd/real-application.yml";

	public static void main(String[] args) {
//		SpringApplication.run(BoardApplication.class, args);
		new SpringApplicationBuilder(BoardApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}

}
