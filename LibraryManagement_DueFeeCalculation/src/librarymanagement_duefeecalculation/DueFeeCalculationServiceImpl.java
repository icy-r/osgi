package librarymanagement_duefeecalculation;

import java.util.HashMap;
import java.util.Map;

public class DueFeeCalculationServiceImpl implements DueFeeCalculationService {
    private Map<String, Double> feeMap = new HashMap<>();

    public double calculateDueFee(String bookId, int overdueDays) {
        double feePerDay = 0.5; // Example fee per day
        double dueFee = overdueDays * feePerDay;
        feeMap.put(bookId, dueFee);
        return dueFee;
    }
}
