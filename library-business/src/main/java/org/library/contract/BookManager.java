package org.library.contract;


import org.library.model.Book;
import org.springframework.security.core.context.SecurityContext;

import java.util.List;

public interface BookManager {

   public Book getBook(SecurityContext context, int id);
}
