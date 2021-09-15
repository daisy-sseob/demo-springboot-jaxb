package me.sseob.demobootweb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/*
	@WebMvcTest 
	Web과 관련된 bean만 등록시켜서 테스트한다.
	@AutoConfigureMockMvc가 포함되어있기 때문에 생략가능 하다.
 */

/*
	@SpringBootTest
	모든 bean들을 등록한다. 
	@Component와 같은 annotation은 Web과 관련된 bean만을 등록시키는 
	@WebMvcTest annotation과는 달리 모든 bean을 등록한다.
 */
@RunWith(SpringRunner.class)
@SpringBootTest 
@AutoConfigureMockMvc
public class SampleControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void hello() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/hello/keesun"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().string("hello keesun"));
	}
	
	@Test
	public void helloRequestParam() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/hello")
						.param("name", "sseob입니다."))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().string("hello sseob입니다."));
	}
}