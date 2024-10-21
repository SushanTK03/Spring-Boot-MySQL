package com.app.sushant.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.sushant.binding.Book;
import com.app.sushant.repository.BookRepository;

@Controller
public class BookController {

	private static Logger logger = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BookRepository bookRepo;

	@GetMapping("/")
	public String displayForm(Model model) {
		logger.info("*** displayForm () method execution start ***");
		Book bookObj = new Book();
		model.addAttribute("book", bookObj);

		List<Book> booksList = bookRepo.findAll();
		model.addAttribute("books", booksList);

		logger.info("*** displayForm () method execution end ***");
		return "index";
	}

	@PostMapping("/")
	public String saveBook(Book book, Model model) {
		logger.info("*** saveBook () method execution start ***");
		bookRepo.save(book);
		List<Book> booksList = bookRepo.findAll();
		model.addAttribute("books", booksList);
		
		Book bookObj = new Book();
		model.addAttribute("book", bookObj);
		
		
		model.addAttribute("msg", "Book Saved Successfully");
		logger.info("*** saveBook () method execution end ***");
		return "index";
	}
		
		@DeleteMapping("/{bookId}")
		@ResponseBody
		public ResponseEntity<String> deleteBookById(@PathVariable("bookId") Long bookId) {
			logger.info("*** deleteBookById() method execution start ***");

			if (bookRepo.existsById(bookId)) {
				bookRepo.deleteById(bookId);
				logger.info("Book with ID: " + bookId + " deleted successfully.");
				return ResponseEntity.ok("Book deleted successfully.");
			} else {
				logger.error("Book with ID: " + bookId + " not found.");
				return ResponseEntity.status(404).body("Book not found.");
			}
	}
}
