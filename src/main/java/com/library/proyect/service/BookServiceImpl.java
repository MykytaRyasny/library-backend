package com.library.proyect.service;

import com.library.proyect.dto.BookDTO;
import com.library.proyect.mapper.BookMapper;
import com.library.proyect.repository.book.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    private BookMapper bookMapper;


    @Override
    public BookDTO getByISBN(String isbn) {
        return null;
    }

    @Override
    public BookDTO save(BookDTO bookDTO) {
        return null;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return null;
    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO) {
        return null;
    }

    @Override
    public void deleteBook(String isbn) {

    }
}
