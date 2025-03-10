package librarymanagement_usermanagement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class UserManagementServiceImpl implements UserManagementService {
    private Map<String, String> userMap = new HashMap<>();

    public void addUser(String userId, String userDetails) {
        userMap.put(userId, userDetails);
    }

    public void updateUser(String userId, String userDetails) {
        userMap.put(userId, userDetails);
    }

    public void deleteUser(String userId) {
        userMap.remove(userId);
    }

    public String getUserById(String userId) {
        return userMap.get(userId);
    }

    public List<String> searchUsersByNameOrEmail(String searchQuery) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, String> entry : userMap.entrySet()) {
            if (entry.getValue().contains(searchQuery)) {
                result.add(entry.getValue());
            }
        }
        return result;
    }
}
