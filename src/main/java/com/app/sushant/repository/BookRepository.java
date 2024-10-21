package com.app.sushant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.sushant.binding.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
