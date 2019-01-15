package org.library.impl;

import org.library.contract.BookManager;

import javax.inject.Named;

@Named
public class BookManagerImpl implements BookManager {
    @Override
    public void trok() {
        System.out.println("topoko");
    }


}
