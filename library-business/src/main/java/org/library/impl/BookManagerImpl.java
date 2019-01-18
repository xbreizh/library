package org.library.impl;

import org.library.contract.BookManager;
import org.library.model.Book;

import javax.inject.Named;
import java.util.logging.Logger;

@Named
public class BookManagerImpl implements BookManager {
    private Logger logger = Logger.getLogger(this.getClass().getName());


    @Override
    public Book getBook(int id) {
        return null;
    }
}
