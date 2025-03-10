package librarymanagement_bookcatalog;

public interface BookCatalogService {
    void addBook(String bookId, String bookDetails);
    void updateBook(String bookId, String bookDetails);
    void deleteBook(String bookId);
    String searchBookById(String bookId);
}
