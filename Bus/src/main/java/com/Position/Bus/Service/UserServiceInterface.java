package com.Position.Bus.Service;

import com.Position.Bus.Model.*;
import com.Position.Bus.RequestModel.PasswordChangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserServiceInterface {
     List<User> getAll();
  User getUserById(Long id);
    User addUser(User user);
    User getUserByCode(String userCode );

    List<User> getUsersByBus(Bus bus);
    Bus getBusByUser(Long id);

    void affectUsersToBus(Long id, List<User> users);
    List<User> getStationsAffectedAndNotAffected(Long id);
    // void deleteUserById(Long id);
    List<User> getUserNotAffected();
    List<User> getAffectedUser(Long id);

    boolean changePassword(PasswordChangeRequest passwordChangeRequest);
}
