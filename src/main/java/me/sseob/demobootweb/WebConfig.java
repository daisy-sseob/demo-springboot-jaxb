package me.sseob.demobootweb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	/*
	* Person formatter등록.
	* 이제는 문자열을 Person객체로 변환할 수 있도록.
	* 
	* 그치만 SpringBoot는 Formatter가 Bean으로 등록되어있으면 자동으로 Formatter를 등록시키기 때문에 
	* 아래 설정은 필요없다.
	* */
//	@Override
//	public void addFormatters(FormatterRegistry registry) {
//		registry.addFormatter(new PersonFormatter());
//	}
}
