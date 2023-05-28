package com.library.proyect.mapper;

import com.library.proyect.dto.BookDTO;
import com.library.proyect.model.BookEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDTO bookToBookDTO(BookEntity book);

    BookEntity bookDTOtoBook(BookDTO bookDTO);

    List<BookDTO> bookToBookDTOLIST(List<BookEntity> bookEntityList);
}
