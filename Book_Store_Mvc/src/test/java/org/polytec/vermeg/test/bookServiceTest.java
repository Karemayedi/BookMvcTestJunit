package org.polytec.vermeg.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.polytec.vermeg.dao.BookDAO;
import org.polytec.vermeg.model.Book;
import org.polytec.vermeg.service.BookService;
@ExtendWith ({MockitoExtension.class})
class bookServiceTest {
	Date d=new Date(2020-12-12);
	Book book;
@Mock
BookDAO dookDao;
@InjectMocks
BookService bookService;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetAllBooks() {
		List <Book> books = new ArrayList<Book>();
		books.add(new Book(1,"a","a",1,d)) ;
		books.add(new Book(2,"a","a",1,d)) ;
		books.add(new Book(3,"a","a",1,d)) ;		
		when(dookDao.getAllBooks()).thenReturn(books);		
		List<Book> books1 = bookService.getAllBooks() ;
		assertEquals(books1, books, "sfljf");
	}
		
  //fail("Not yet implemented");

	@Test
	void testGetBook() {
		Book b =new Book(1,"a","a",1,d);
		dookDao.getBook(1);
		
	
		assertEquals(dookDao.getAllBooks(),bookService.getAllBooks(),"incorrecte");
	
	}

	@Test
	void testAddBook() {
		Book b=new Book(1,"a","a",1,d);
		dookDao.addBook(b);
		Book b1=new Book(1,"a","a",1,d);
		verify(dookDao, times(1)).addBook(b); 
		
	}

	@Test
	void testUpdateBook() {
		Book b=new Book(1,"a","a",1,d);
		Book b1=new Book(1,"bb","aa",1,d);
		dookDao.updateBook(b);
		verify(dookDao, times(1)).updateBook(b); 
		//fail("Not yet implemented");
	}

	@Test
	void testDeleteBook() {
		Book b=new Book(1,"a","a",1,d);
		dookDao.deleteBook(1);
		verify(dookDao, times(1)).deleteBook(1); 
	}

}
