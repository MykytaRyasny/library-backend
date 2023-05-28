package com.library.proyect.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BookDTO {

    private String isbn;

    private String title;

    private Date releaseDate;

    private String autor;
}
