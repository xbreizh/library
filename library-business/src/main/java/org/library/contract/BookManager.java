package org.library.contract;


import org.library.model.Book;
import org.springframework.security.core.context.SecurityContext;
import org.troparo.entities.book.BookTypeOut;

public interface BookManager {

    Book getBook(SecurityContext context, int id);
    Book convertBookTypeOutIntoBook(BookTypeOut bookTypeOut);
}
