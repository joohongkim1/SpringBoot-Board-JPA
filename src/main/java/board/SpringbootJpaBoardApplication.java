package board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

// ÷������ ���� ������ �ڵ� ������ ������� �ʵ��� ��.
@SpringBootApplication(exclude= {MultipartAutoConfiguration.class})
public class SpringbootJpaBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaBoardApplication.class, args);
	}

}
