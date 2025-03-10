package librarymanagement_rentalmanagement;

import java.util.HashMap;
import java.util.Map;

public class RentalManagementServiceImpl implements RentalManagementService {
    private Map<String, String> rentalMap = new HashMap<>();

    public void rentBook(String bookId, String userId) {
        rentalMap.put(bookId, userId);
    }

    public void returnBook(String bookId, String userId) {
        rentalMap.remove(bookId, userId);
    }

    public boolean isBookRented(String bookId) {
        return rentalMap.containsKey(bookId);
    }
}
