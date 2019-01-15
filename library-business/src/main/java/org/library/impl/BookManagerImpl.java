package org.library.impl;

import org.library.contract.BookManager;

import javax.inject.Named;

@Named
public class BookManagerImpl implements BookManager {
    @Override
    public void trok() {
        System.out.println("topoko");
    }

  /*  @Override
    public List<Book> getBooks(String token){
        System.out.println("getting into bookmanager");

        return null;
    }*/
}
