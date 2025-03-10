package librarymanagement_rentalmanagement;

public interface RentalManagementService {
    void rentBook(String bookId, String userId);
    void returnBook(String bookId, String userId);
    boolean isBookRented(String bookId);
}
