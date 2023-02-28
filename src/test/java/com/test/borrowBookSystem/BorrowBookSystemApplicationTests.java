package com.test.borrowBookSystem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.borrowBookSystem.model.Book;
import com.test.borrowBookSystem.repository.BookRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BorrowBookSystemApplicationTests {

	@Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testCreateBook() {
		// Create a new book
        Book book = new Book();
        book.setTitle("Test Book");
        book.setDescription("This is a test book");
        book.setAuthor("Test Author");
        book.setPublishDate(new Date());

		// Send request to create the book
        ResponseEntity<Book> response = restTemplate.postForEntity("/api/books", book, Book.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(book.getTitle(), response.getBody().getTitle());
    }

    @Test
    public void testListBooks() {
		// Create some new book
        List<Book> books = Arrays.asList(
                new Book("Book 1", "Author 1", false, "Description 1", new Date()),
                new Book("Book 2", "Author 2", false, "Description 2", new Date()),
                new Book("Book 3", "Author 3", false, "Description 3", new Date())
        );
        bookRepository.saveAll(books);

		// Send request to list the book
        ResponseEntity<List<Book>> response = restTemplate.exchange("/api/books", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Book>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetBook() {
		// Create a new book
        Book book = new Book("Test Book", "Test Author", false, "This is a test book", new Date());
        bookRepository.save(book);

		// Send Get request to get the book
        ResponseEntity<Book> response = restTemplate.getForEntity("/api/books/{id}", Book.class, book.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(book.getTitle(), response.getBody().getTitle());
    }

    @Test
    public void testDeleteBook() {
		// Create a new book
        Book book = new Book("Test Book", "Test Author", false, "This is a test book", new Date());
        bookRepository.save(book);

		// Send Delete request to delete the book
        restTemplate.delete("/api/books/{id}", book.getId());

		// Verify that the book was deleted in the database
        assertFalse(bookRepository.findById(book.getId()).isPresent());
    }

	@Test
    public void testUpdateBook() throws Exception {
        // Create a new book
        Book book = new Book("Test Book", "Test Author", false, "This is a test book", new Date());
        bookRepository.save(book);

        // Update the book's description
        book.setDescription("Change description test");

        // Send PUT request to update the book
        restTemplate.put("/api/books/" + book.getId(), book);

        // Verify that the book was updated in the database
        Book updatedBook = bookRepository.findById(book.getId()).orElse(null);
        assertNotNull(updatedBook);
        assertEquals(book.getDescription(), updatedBook.getDescription());
    }


}
