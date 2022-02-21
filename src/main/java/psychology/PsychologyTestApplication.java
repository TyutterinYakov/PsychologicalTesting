package psychology;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PsychologyTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PsychologyTestApplication.class, args);
	}

}
