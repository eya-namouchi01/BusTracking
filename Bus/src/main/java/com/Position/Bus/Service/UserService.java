package com.Position.Bus.Service;

import com.Position.Bus.Model.*;
import com.Position.Bus.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
@Service
public class UserService implements UserServiceInterface{
    @Autowired(required=true)
    private  UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }
    public User getUserByCode(String userCode ) {
        Optional<User> user = userRepository.findByUserCode(userCode );
        return user.orElse(null);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

  /*  public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }*/

    public List<User> getUsersByBus(Bus bus ) {
        Optional<List<User> > user = userRepository.findByBus(bus );
        return user.orElse(null);
    }



}