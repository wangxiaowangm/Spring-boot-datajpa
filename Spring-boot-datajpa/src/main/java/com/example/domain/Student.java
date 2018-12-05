package com.example.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
@Entity  
@Table(name = "Student")
public class Student implements Serializable{
	private Integer id;
	private String studentName;
	private Set<Teacher> teacher;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="generator")
	@SequenceGenerator(sequenceName="Student_SEQ",
		name="generator",initialValue=1,allocationSize=1)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "studentName", length = 30)
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)   
	@JoinTable(name="student_teacher",  
			joinColumns= {@JoinColumn(name="student_id")}, 
			inverseJoinColumns= {@JoinColumn(name="teacher_id")})  
	@Cascade(CascadeType.SAVE_UPDATE)
	public Set<Teacher> getTeacher() {
		return teacher;
	}
	public void setTeacher(Set<Teacher> teacher) {
		this.teacher = teacher;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", studentName=" + studentName + ", teacher=" + teacher + "]";
	}
	
	
}
