package librarymanagement_duefeecalculation;

public interface DueFeeCalculationService {
    double calculateDueFee(String bookId, int overdueDays);
}
