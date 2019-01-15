package contract;

import org.library.model.Book;

import javax.inject.Named;
import java.util.List;

@Named
public interface BookManager {

    public List<Book> getBooks(String token);
}
