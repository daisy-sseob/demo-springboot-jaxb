package me.sseob.demobootweb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

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

	/*
		preHandle 1
		preHandle 2
		요청처리
		postHandler 2
		postHandler 1
		view rendering
		afterCompletion 2
		afterCompletion 1
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new GreetingInterceptor()).order(0);
		registry.addInterceptor(new AnotherInterceptor())
				.addPathPatterns("/hi")
				.order(-1);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/m/**").addResourceLocations("classpath:/mobile/")
				.setUseLastModified(true)
				.setCacheControl(CacheControl.maxAge(100, TimeUnit.MINUTES))
				.resourceChain(true) //cache를 쓸지 말지
		;
	}

	@Bean
	public Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setPackagesToScan(Person.class.getPackageName());
		return jaxb2Marshaller;
	}
}
