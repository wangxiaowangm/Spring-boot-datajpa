package com.example.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "Teacher")
public class Teacher implements Serializable{
	private Integer id;
	private String teacherName;
	private Set<Student> student;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="generator")
	@SequenceGenerator(sequenceName="Teacher_SEQ",
		name="generator",initialValue=1,allocationSize=1)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "studentName", length = 30)
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	@ManyToMany
	@JoinTable(name="student_teacher",  
			joinColumns= {@JoinColumn(name="teacher_id")}, 
			inverseJoinColumns= {@JoinColumn(name="student_id")})  
	@Cascade(CascadeType.SAVE_UPDATE)
	public Set<Student> getStudent() {
		return student;
	}
	public void setStudent(Set<Student> student) {
		this.student = student;
	}
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", teacherName=" + teacherName + "]";
	}
	
}
