package org.library.contract;


import org.library.model.Book;
import org.springframework.security.core.context.SecurityContext;
import org.troparo.entities.book.BookTypeOut;

import java.util.HashMap;
import java.util.List;

public interface BookManager {



    List<Book> searchBooks(String token, HashMap criterias);
}
