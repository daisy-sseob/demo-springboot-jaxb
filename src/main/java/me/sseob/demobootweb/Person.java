package me.sseob.demobootweb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/*
Spring Data Jpa는 domain class convertor를 제공한다.
별도의 Formatter or Converter 등록하지 않아도 문자열을 객체로 변환 가능하다.
 */
@Entity
public class Person {
	
	@Id @GeneratedValue
	private Long id;
	
	private String name;

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
}
