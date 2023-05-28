package com.library.proyect.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BOOKS")
public class BookEntity {

    @Id
    @Column(name = "ISBN", nullable = false)
    private String isbn;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "RELEASE_DATE", nullable = false)
    private Date releaseDate;

    @Column(name = "AUTOR")
    private String autor;
}
