package com.Position.Bus.Service;

import com.Position.Bus.Model.*;
import com.Position.Bus.Repository.BusRepository;
import com.Position.Bus.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
@Service
public class UserService implements UserServiceInterface{
    @Autowired(required=true)
    private  UserRepository userRepository;
    private BusRepository busRepository;

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


}
