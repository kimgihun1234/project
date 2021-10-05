package cse.knu.cdp1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication// (exclude={DataSourceAutoConfiguration.class}) // exclude 부분은 Test 용으로만 사용됩니다. 실제 DB 연동 시 주석처리 부탁드립니다.

public class MmsApplication {
	public static void main(String[] args) {
		SpringApplication.run(MmsApplication.class, args);
	}

}