package librarymanagement_usermanagement;

import java.util.List;

public interface UserManagementService {
    void addUser(String userId, String userDetails);
    void updateUser(String userId, String userDetails);
    void deleteUser(String userId);
    String getUserById(String userId);
    List<String> searchUsersByNameOrEmail(String searchQuery);
}
