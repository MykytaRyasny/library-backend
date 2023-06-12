package com.library.proyect.controller;

import com.library.proyect.controller.utils.LoggerUtils;
import com.library.proyect.dto.BookDTO;
import com.library.proyect.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@EnableMethodSecurity
@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/find/{isbn}")
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_shop_vendor', 'ROLE_shop_manager')")
    public ResponseEntity<BookDTO> getEmployeeById(@PathVariable(value = "isbn") String isbn, final Principal user) {
        try {
            BookDTO employeeDTO = bookService.getByISBN(isbn);
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            String className = new Object() {
            }.getClass().getEnclosingClass().getName();
            LoggerUtils.LoggerInfo(methodName, className);
            return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_shop_vendor', 'ROLE_shop_manager')")
    @GetMapping("/all")
    public ResponseEntity<List<BookDTO>> getAllBooks(Principal principal) {
        try {
            List<BookDTO> bookDTOList = bookService.getAllBooks();
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            String className = new Object() {
            }.getClass().getEnclosingClass().getName();
            LoggerUtils.LoggerInfo(methodName, className);
            return new ResponseEntity<>(bookDTOList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/new")
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_shop_manager')")
    public ResponseEntity<BookDTO> newBook(@RequestBody BookDTO bookDTO, final Principal user) {
        try {
            bookService.save(bookDTO);
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            String className = new Object() {
            }.getClass().getEnclosingClass().getName();
            LoggerUtils.LoggerInfo(methodName, className);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LoggerUtils.LOGGER.error("Internar error", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{isbn}")
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_shop_manager')")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "isbn") String isbn, final Principal user) {
        try {
            bookService.deleteBook(isbn);
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            String className = new Object() {
            }.getClass().getEnclosingClass().getName();
            LoggerUtils.LoggerInfo(methodName, className);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit")
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_shop_manager')")
    public ResponseEntity<?> editEmployee(@RequestBody BookDTO bookDTO, final Principal user) {
        try {
            bookService.updateBook(bookDTO);
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            String className = new Object() {
            }.getClass().getEnclosingClass().getName();
            LoggerUtils.LoggerInfo(methodName, className);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
