package com.Position.Bus.Service;

import com.Position.Bus.Model.*;
import com.Position.Bus.Repository.BusRepository;
import com.Position.Bus.Repository.UserRepository;
import com.Position.Bus.RequestModel.PasswordChangeRequest;
import com.Position.Bus.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class UserService implements UserServiceInterface{
    @Autowired(required=true)
    private  UserRepository userRepository;
    private BusRepository busRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;

    public UserService(UserRepository userRepository, BusRepository busRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.busRepository = busRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.authenticationService = authenticationService;
    }

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
    public Bus getBusByUser(Long id) {
        Optional<User > user = userRepository.findById(id);
        Bus bus = user.get().getBus();
        return bus;
    }

    @Override
    public void affectUsersToBus(Long id, List<User> users) {
        Optional<Bus> bus = busRepository.findById(id);
        if(bus == null){
            for(User user : users)
            {
                System.out.println(user);
                Optional<User> u =userRepository.findById(user.getId());
                if(user == null)
                {
                    user.setBus(bus.get());
                    userRepository.save(user);
                }


            }
        }


    }

    @Override
    public List<User> getStationsAffectedAndNotAffected(Long id) {
        return userRepository.getStationsAffectedAndNotAffected(id);
    }


    @Override
    public List<User> getUserNotAffected()
    {
        return userRepository.getListUserNotAffected();
    }

    @Override
    public List<User> getAffectedUser(Long id)
    {
        return userRepository.getAffectedUser(id);
    }

    @Override
    public boolean changePassword(PasswordChangeRequest passwordChangeRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            passwordChangeRequest.getCuid(),
                            passwordChangeRequest.getCurrentPassword()
                    )
            );
        } catch (AuthenticationException e) {
            return false;
        }
          User user =  getUserByCode(passwordChangeRequest.getCuid());
          String newPassword = passwordEncoder.encode(passwordChangeRequest.getNewPassword());
          user.setPassword(newPassword);
          userRepository.save(user);
          return true;




    }


}
