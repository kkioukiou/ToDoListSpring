package ToDoService.Security.SecurityService;

import ToDoService.Security.SecurityModels.User;

public interface UserService {
    User findUserByEmail(String email);
    void saveUser(User user);
}
