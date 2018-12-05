package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.domain.Author;
import com.example.domain.Book;
import com.example.service.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AppTest {

	private static final Logger log = LoggerFactory.getLogger(AppTest.class);

	@Inject
	BookService bookService;
    //  Book
	//  Integer id;
	//  String bookName;
	//  Float price;
	//  Author author;
	@Ignore
	@Test
	public void saveTest() {
		Book book = bookService.findByIdIs(1);
		Author author = new Author();
		author.setAuthorName("wang");
		book.setBookName("aaa");
		book.setPrice((float) 12.30);
		book.setAuthor(author);
		book.setFlag(1);
		bookService.saveBook(book);
		List<Book> list = bookService.findAllBook();
		System.out.println(list.size());
	}

	@Ignore
	@Test
	public void test2() {
		Book book = bookService.findByIdIs(1);
		System.out.println(book + "----book");
	}

	@PersistenceContext
	EntityManager em;

	@Ignore
	@Test
	public void test3() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Book> c = cb.createQuery(Book.class);
		Root<Book> book = c.from(Book.class);
		CriteriaQuery query = c.select(book).where(cb.equal(book.get("bookName"), "asd"));
		TypedQuery query1 = em.createQuery(query);
		List<Book> list = query1.getResultList();
		System.out.println(query1.getResultList());

	}

	@Ignore
	@Test
	public void getBookPageTest() {
		List<Book> book = bookService.getBookPage();
		System.out.println(book);
	}

	@Ignore
	@Test
	public void deleteTest(Book book) {
		bookService.deleteBook(book);
	}

}
