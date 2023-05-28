package com.library.proyect.service;

import com.library.proyect.dto.BookDTO;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {

    BookDTO getByISBN(String isbn);

    BookDTO save(BookDTO bookDTO);

    List<BookDTO> getAllBooks();

    BookDTO updateBook(BookDTO bookDTO);

    void deleteBook(String isbn);
}
