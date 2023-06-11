package com.library.proyect.service;

import com.library.proyect.dto.BookDTO;
import com.library.proyect.mapper.BookMapper;
import com.library.proyect.model.BookEntity;
import com.library.proyect.repository.book.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    private BookMapper bookMapper;


    @Override
    public BookDTO getByISBN(String isbn) {
        return bookMapper.bookToBookDTO(bookRepository.findById(isbn).orElse(null));
    }

    @Override
    public BookDTO save(BookDTO bookDTO) {
        BookEntity bookEntity = bookMapper.bookDTOtoBook(bookDTO);
        bookRepository.save(bookEntity);
        return bookDTO;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<BookEntity> bookList = bookRepository.findAll();
        return bookMapper.bookToBookDTOLIST(bookList);
    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO) {
        BookEntity bookToEdit = bookRepository.getReferenceById(bookDTO.getIsbn());
        bookToEdit.setAutor(bookDTO.getAutor());
        bookToEdit.setTitle(bookDTO.getTitle());
        bookToEdit.setReleaseDate(bookDTO.getReleaseDate());
        bookRepository.save(bookToEdit);
        return bookDTO;
    }

    @Override
    public void deleteBook(String isbn) {
        bookRepository.deleteById(isbn);
    }
}
