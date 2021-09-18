package me.sseob.demobootweb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.oxm.Marshaller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

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
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	Marshaller marshaller;
	
	@Test
	public void hello() throws Exception {
		
		Person person = new Person();
		person.setName("sseob 입니다.");

		Person savedPerson = personRepository.save(person);
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/hello")
						.param("id", savedPerson.getId().toString()))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().string("hello sseob 입니다."));
	}
	
	@Test
	public void helloStatic() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/index.html"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("hello index")))
				;
	}
	
	@Test
	public void helloMobileStatic() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/mobile/index.html"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("hello mobile")))
				.andExpect(MockMvcResultMatchers.header().exists(HttpHeaders.CACHE_CONTROL))
				;
	}
	
	@Test
	public void helloStringMessage() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders.get("/message").content("hello String"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("hello String"))
		;
	}
	
	@Test
	public void jsonMessage() throws Exception{
		
		Person person = new Person();
		person.setId(2019l);
		person.setName("sseob");

		String jsonString = objectMapper.writeValueAsString(person);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/jsonMessage")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON) //accept type은 응답으로 원하는 데이터 유형
						.content(jsonString))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2019))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("sseob"))
		;
	}
	@Test
	public void xmlMessage() throws Exception{
		
		Person person = new Person();
		person.setId(2019l);
		person.setName("sseob");

		StringWriter writer = new StringWriter();
		Result result = new StreamResult(writer);
		marshaller.marshal(person, result);
		
		String xmlString = writer.toString();

		this.mockMvc.perform(MockMvcRequestBuilders.get("/xmlMessage")
						.contentType(MediaType.APPLICATION_XML)
						.accept(MediaType.APPLICATION_XML) //accept type은 응답으로 원하는 데이터 유형
						.content(xmlString))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.xpath("person/name").string("sseob"))
				.andExpect(MockMvcResultMatchers.xpath("person/id").string("2019"))
		;
	}
}