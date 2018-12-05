package com.example.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Author;
import com.example.domain.Book;
import com.example.domain.BookRepository;

public class BookService {

	@Inject
	BookRepository BookRepository;

	// 分页查询
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Book> getBookPage() {
		PageRequest pageable = new PageRequest(1, 6);
		Specification<Book> spec = new Specification<Book>() {
			@Override
			public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<>();
				return criteriaBuilder.and(list.toArray(new Predicate[0]));
			}
		};
		Page<Book> pageList = BookRepository.findAll(spec, pageable);
		return pageList.getContent();
	}

	// 按照id查询book
	public  Book findByIdIs(Integer id) {
		Book book = this.BookRepository.findOne(id);
		return book;
	}

	// 按照author查询book
	public Book findAuthor(Author author) {
		Book book = (Book) this.BookRepository.findByAuthor(author);
		return book;

	}

	// 查询book
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Book> findAllBook() {
		List<Book> book = this.BookRepository.findAll();
		return book;
	}

	// 保存book
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveBook(Book book) {
		this.BookRepository.saveAndFlush(book);
	}

	// 删除book
		@Transactional(propagation = Propagation.REQUIRED)
		public void deleteBook(Book book) {			
			this.BookRepository.delete(book);
		}
}