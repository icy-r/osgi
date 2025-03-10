package librarymanagement_bookcatalog;

import java.util.List;

public interface BookCatalogService {
    void addBook(String bookId, String bookDetails);
    void updateBook(String bookId, String bookDetails);
    void deleteBook(String bookId);
    String searchBookById(String bookId);
    List<String> getAllBooks();
}
