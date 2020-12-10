package org.polytec.vermeg.test;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;



import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.polytec.vermeg.controller.BookController;
import org.polytec.vermeg.model.Book;
import org.polytec.vermeg.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

class ControllerTestJunit {
	Date d =new Date(2020-12-12);
	Book book;
	@Mock 
	BookService bookService;
	@InjectMocks 
	BookController bookController;
	@Autowired
	private MockMvc mockMvc ;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		 MockitoAnnotations.initMocks(this);	
			
		 mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetBooks() throws Exception {
		List<Book> books = new ArrayList<Book>() ;
		
		books.add(new Book(1,"a","a",1,d));
		books.add(new Book(1,"a","a",1,d));
	when(bookService.getAllBooks()).thenReturn((List)books ) ;
	 mockMvc.perform(get("/book/getAllBook"))
     .andExpect(status().isOk())
     .andExpect(jsonPath("$", hasSize(2)))
     .andDo(print()) ;
	 
	
	}

	@Test
	void testGetBookById() {
		//fail("Not yet implemented");
	}

	@Test
	void testAddBook() {
		
		
		Book b =new  Book(1,"a","a",1,d);
		bookService.addBook(b);
		verify(bookService, times(1)).addBook(b);
		try {
			this.mockMvc.perform(post("/book/addBook")).andExpect(status().isOk()).andDo(print());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@Test
	void testUpdateBook() {
		
		Book b =new  Book(1,"a","a",1,d);
		int idBook =b.getId();
		bookService.updateBook(b);
		verify(bookService, times(1)).updateBook(b);
		try {
			this.mockMvc.perform(put("/book/updateBook/"+idBook)).andExpect(status().isOk()).andDo(print());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

	@Test
	void testDeleteBook() throws Exception {
		Book b =new  Book(1,"a","a",1,d);
		int idBook=b.getId();
		bookService.deleteBook(idBook);
		verify(bookService, times(1)).deleteBook(idBook);
		 this.mockMvc.perform(delete("/book/deleteBook/"+idBook)).andExpect(status().isOk()).andDo(print());
	}

	@Test
	void testCalSommePrixTotal() throws Exception {
		Book b= new Book(1, "jhon", "jack", 4, d); 
		Book b0= new Book(2, "jhon", "jack", 5, d);
		Book b1= new Book(3, "jhon", "jack", 1, d);
		List <Integer> liste = new ArrayList <Integer>();
		liste.add(b.getId());
		liste.add(b0.getId());
		liste.add(b1.getId());
		
		this.mockMvc.perform(post("/book/calSommePrixTotal")).andExpect(status().isOk()).andDo(print());
		
		assertEquals(bookController.calSommePrixTotal(liste),10);
		
	}

}
