package librarymanagement_bookcatalog;

import java.util.HashMap;
import java.util.Map;

public class BookCatalogServiceImpl implements BookCatalogService {
    private Map<String, String> bookMap = new HashMap<>();

    public void addBook(String bookId, String bookDetails) {
        bookMap.put(bookId, bookDetails);
    }

    public void updateBook(String bookId, String bookDetails) {
        bookMap.put(bookId, bookDetails);
    }

    public void deleteBook(String bookId) {
        bookMap.remove(bookId);
    }

    public String searchBookById(String bookId) {
        return bookMap.get(bookId);
    }
}
