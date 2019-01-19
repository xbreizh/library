package org.library.impl;

import org.apache.log4j.Logger;
import org.library.contract.BookManager;
import org.library.model.Book;
import org.springframework.security.core.context.SecurityContext;
import org.troparo.entities.book.BookTypeOut;
import org.troparo.entities.book.GetBookByIdRequestType;
import org.troparo.entities.book.GetBookByIdResponseType;
import org.troparo.services.bookservice.BookService;
import org.troparo.services.bookservice.BusinessExceptionBook;

import javax.inject.Named;

@Named
public class BookManagerImpl implements BookManager {
    private static final Logger logger = Logger.getLogger(BookManagerImpl.class);
    /*private String token="";
    private String login="";
    private Book book=null;*/


    @Override
    public Book getBook(SecurityContext context, int id) {
        String token = context.getAuthentication().getDetails().toString();
        String login = context.getAuthentication().getPrincipal().toString();
        logger.info("token: " + token);
        logger.info("login: " + login);
        Book book = null;

        try {
            BookService bookService = new BookService();
            GetBookByIdRequestType requestType = new GetBookByIdRequestType();
            requestType.setToken(token);
            requestType.setReturn(id);
            logger.info("request prepared");
            GetBookByIdResponseType responseType = bookService.getBookServicePort().getBookById(requestType);
            BookTypeOut bookTypeOut = responseType.getBookTypeOut();
            logger.info("title: " + bookTypeOut.getTitle());
            if (responseType.getBookTypeOut() != null) {
                book = convertBookTypeOutIntoBook(bookTypeOut);
            }
        } catch (BusinessExceptionBook e) {
            logger.error(e.getMessage());
        }
        return book;
    }
    @Override
    public Book convertBookTypeOutIntoBook(BookTypeOut bookTypeOut) {
        logger.info("trying to convert book");
        Book book = new Book();
        book.setId(bookTypeOut.getId());
        book.setIsbn(bookTypeOut.getISBN());
        book.setTitle(bookTypeOut.getTitle());
        book.setAuthor(bookTypeOut.getAuthor());
        book.setEdition(bookTypeOut.getEdition());
        book.setPublicationYear(bookTypeOut.getPublicationYear());
        book.setNbPages(bookTypeOut.getNbPages());
        book.setKeywords(bookTypeOut.getKeywords());
        logger.info("book converted: "+book);
        return book;
    }
}
