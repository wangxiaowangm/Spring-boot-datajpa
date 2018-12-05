package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="IdentityCard")
public class IdentityCard {
	
	private Integer id;
	private String idNumber;
	private Person person;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="generator")
	@SequenceGenerator(sequenceName="Identify_SEQ",
		name="generator",initialValue=1,allocationSize=1)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "IdNumber", length = 20)
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	
	@OneToOne(mappedBy="identityCard")
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	@Override
	public String toString() {
		return "IdentityCard [id=" + id + ", idNumber=" + idNumber + "]";
	}
	
	

}
