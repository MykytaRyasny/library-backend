package com.library.proyect.controller;

import com.library.proyect.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private BookService bookService;

    @PreAuthorize("hasAnyRole('admin')")
    @GetMapping("/all")
    public ResponseEntity<?> getAllBooks(Principal principal) {
        return new ResponseEntity<>("uwu", HttpStatus.OK);
    }
}
