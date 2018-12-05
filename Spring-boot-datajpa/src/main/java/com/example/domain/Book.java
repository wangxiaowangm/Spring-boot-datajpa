package com.example.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "Book")
public class Book implements Serializable {
	private Integer id;
	private String bookName;
	private Float price;
	private Author author;
	private Integer flag;

	public Book() {
	}

	public Book(String bookName, Float price) {
		this.bookName = bookName;
		this.price = price;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@SequenceGenerator(sequenceName = "Book_SEQ", name = "generator", initialValue = 1, allocationSize = 1)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "bookName", length = 30)
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	@Column(name = "price", columnDefinition = "decimal(4,2)")
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@ManyToOne
	@Where(clause = "flag = 1")
	@OrderBy("id asc")
	@JoinColumn(name = "author_id")
	@Cascade(CascadeType.SAVE_UPDATE)
	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + ", price=" + price + ", author=" + author + "]";
	}

}
