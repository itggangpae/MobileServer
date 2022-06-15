package com.adam.mobileserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//Java에서는 @가 붙은 단어를 Annotation 이라고 하고 Python에서는 Decorator 라고 합니다.
//자주 사용하는 코드를 하나의 클래스로 만든 후 이 클래스의 수행 코드를 예약어 형태로 만든 것입니다.
//이런 코드를 Annotation을 이용해서 만들 수 도 있고 AOP를 이용해서 설정하는 것도 가능합니다.
@SpringBootApplication
@EnableJpaAuditing
public class MobileServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileServerApplication.class, args);
	}

}
