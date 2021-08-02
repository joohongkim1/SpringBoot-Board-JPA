package board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

// 첨부파일 관련 스프링 자동 구성을 사용하지 않도록 함.
@SpringBootApplication(exclude= {MultipartAutoConfiguration.class})
public class SpringbootJpaBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaBoardApplication.class, args);
	}

}
