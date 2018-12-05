package com.example.domain;

import java.awt.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class Test {

	@PersistenceContext
	EntityManager em;

	public static void main(String[] args) {
		// 1. 创建EntityManagerFactory
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA-1");
		// 2. 创建EntityManager
		EntityManager entityManager = factory.createEntityManager();
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		// 4. 持久化操作
		add(entityManager);
		select(entityManager);
		query(entityManager);
		query1(entityManager);
		query2(entityManager);
		update(entityManager);
		// 5. 提交事务
		transaction.commit();

		// 6. 关闭EntityManager
		entityManager.close();

		// 7. 关闭EntityManagerFactory
		factory.close();
	}

	public static void add(EntityManager entityManager) {
		Author author = new Author();
		author.setId(123);
		author.setAuthorName("旺旺");
		Book book = new Book();
		book.setBookName("时光");
		book.setPrice((float) 12.44);
		book.setId(1);
		List booklist = new List();
		entityManager.persist(author);
		entityManager.persist(book);
	}

	public static void select(EntityManager entityManager) {
		List author = (List) entityManager.createQuery("select b from Book b").getResultList();
		System.out.println(author);
	}

	public static void query(EntityManager entityManager) {
		List count = (List) entityManager.createQuery("select count(b) from Book b").getResultList();
		System.out.println(count);
	}

	public static void query1(EntityManager entityManager) {
		List query = (List) entityManager.createQuery("select p from Person p where p.age=?1 and p.Name=?2");
		((Query) query).setParameter(1, 21);
		((Query) query).setParameter(2, "Jack");
	}

	public static void query2(EntityManager entityManager) {
		Query query = entityManager.createQuery("select p from Person p where p.age=:age and p.Name=:name");
		query.setParameter("age", 21);
		query.setParameter("name", "Jack");

	}
	
	public static void update(EntityManager entityManager) {
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("update Person p set p.Name=:name where p.id=:id");
		query.setParameter("name", "xiaobai");
		query.setParameter("id", 2);
		query.executeUpdate();

		Query query1 = entityManager.createQuery("delete Person p where p.id=:id");
		query1.setParameter("id", 9);
		query1.executeUpdate();
		entityManager.getTransaction().commit();
	}
}
