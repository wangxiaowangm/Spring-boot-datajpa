package com.example.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "Author")
public class Author implements Serializable{
	private Integer id;
	private String authorName;
	private Set<Book> bookList;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="generator")
	@SequenceGenerator(sequenceName="Author_SEQ",
		name="generator",initialValue=1,allocationSize=1)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "authorName", length = 30)
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	@OneToMany(mappedBy="author")  
	@Cascade(value= {CascadeType.ALL}) 
	public Set<Book> getBookList() {
		return bookList;
	}
	public void setBookList(Set<Book> bookList) {
		this.bookList = bookList;
	}
	@Override
	public String toString() {
		return "Author [id=" + id + ", authorName=" + authorName + "]";
	}
	
}
