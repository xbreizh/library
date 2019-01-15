package impl;

import contract.BookManager;
import org.library.model.Book;

import javax.inject.Named;
import java.util.List;


public class BookManagerImpl implements BookManager {

    @Override
    public List<Book> getBooks(String token){
        System.out.println("getting into bookmanager");

        return null;
    }
}
