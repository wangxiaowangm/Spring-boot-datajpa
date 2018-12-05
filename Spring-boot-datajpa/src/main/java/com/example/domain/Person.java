package com.example.domain;


import java.io.Serializable;

import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity  
@Table(name = "Person")
public class Person implements Serializable {

	private Integer id;
	private String name;
	private Integer age;
	private IdentityCard identityCard;


	public Person() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="generator")
	@SequenceGenerator(sequenceName="person_SEQ",name="generator",
		initialValue=1,allocationSize=1)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "age")
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	@OneToOne
	@JoinColumn(name="IdentityCard_id")
	@Cascade(value=CascadeType.ALL)
	public IdentityCard getIdentityCard() {
		return identityCard;
	}


	public void setIdentityCard(IdentityCard identityCard) {
		this.identityCard = identityCard;
	}


	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + ", identityCard=" + identityCard + "]";
	}


	

}