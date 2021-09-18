package me.sseob.demobootweb;

import org.springframework.web.bind.annotation.*;

@RestController
public class SampleController {

	@GetMapping("/hello")
	public String helloRequestParam(@RequestParam("id") Person person) {
		return "hello " + person.getName();
	}

	@GetMapping("/message")
	public String message(@RequestBody String body) {
		return body;
	}

	@GetMapping("jsonMessage")
	public Person jsonMessage(@RequestBody Person person) {
		return person;
	}
	@GetMapping("xmlMessage")
	public Person xmlMessage(@RequestBody Person person) {
		return person;
	}
}
