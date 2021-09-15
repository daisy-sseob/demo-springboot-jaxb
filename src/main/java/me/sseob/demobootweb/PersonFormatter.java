package me.sseob.demobootweb;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

/*
	Formatter는 두가지 interface를 하나로 합친것이다.
	Printer<T>  : 객체를 문자열로 어떻게 보여줄 것이냐.
	Parser<T>   : 문자를 객체로 어떻게 변환할 것이냐
 */
@Component
public class PersonFormatter implements Formatter<Person> {

	@Override
	public Person parse(String s, Locale locale) throws ParseException {
		
		Person person = new Person();
		person.setName(s);
		return person;
	}

	@Override
	public String print(Person person, Locale locale) {
		return person.toString();
	}
}
