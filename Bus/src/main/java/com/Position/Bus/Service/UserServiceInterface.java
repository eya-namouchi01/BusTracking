package com.Position.Bus.Service;

import com.Position.Bus.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserServiceInterface {
     List<User> getAll();
  User getUserById(Long id);
    User addUser(User user);
    User getUserByCode(String userCode );

    List<User> getUsersByBus(Bus bus);
    Bus getBusByUser(Long id);
    // void deleteUserById(Long id);
}
