package com.library.proyect.service;

import com.library.proyect.dto.BookDTO;

import java.util.List;

public interface BookService {

    BookDTO getByISBN(String isbn);

    BookDTO save(BookDTO bookDTO);

    List<BookDTO> getAllBooks();

    BookDTO updateBook(BookDTO bookDTO);

    void deleteBook(String isbn);
}
