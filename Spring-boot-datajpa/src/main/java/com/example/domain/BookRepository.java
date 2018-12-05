package com.example.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {
	// private Integer id;
	// private String bookName;
	// private Float price;
	// private Author author;
	@Query("select  b from Book b where b.flag=1")
	List<Book> findAll();

	@Query("select b from Book b where b.price=:price")
	List<Book> findByPrice(Float price);

	@Query("select b from Book b where b.author=:author")
	List<Book> findByAuthor(Author author);

	@Query("select b from Book b where b.id=:id")
	Book findByIdIs(Integer id);

	List<Book> findAllByIdNotNull();

	@Query("select b from Book b where b.bookName=:%bookName%")
	List<Book> findAllByBookNameLike(String bookName);

	List<Book> findAllByIdGreaterThanEqualOrderByBookNameDesc(Integer start);

	@Query("select b from Book b where b.parent is null and b.flag=1  order by b.id")
	List<Book> findRoots();

	@Query("select b from Book b where b.id=:id")
	Book findById(@Param("id") Integer id);

	@Modifying
	@Query("update Book b set b.flag=0 where b.id=:id")
	void updateFlag(@Param("id") String id);

	@Modifying
	@Query(value = "delete from Book b where b.id=?1 and bookName=?2)", nativeQuery = true)
	void delete(@Param("id") Integer id, @Param("bookName") String bookName);

}
