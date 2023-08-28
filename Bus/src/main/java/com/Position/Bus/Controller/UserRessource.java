package com.Position.Bus.Controller;

import com.Position.Bus.Model.*;
import com.Position.Bus.Repository.BusRepository;
import com.Position.Bus.Repository.UserRepository;
import com.Position.Bus.Service.BusServiceInterface;
import com.Position.Bus.Service.UserService;
import com.Position.Bus.Service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
@CrossOrigin(origins = "*")

public class UserRessource {
    @Autowired
    UserRepository userRepository;

    @Autowired(required=true)

    BusRepository busRepository;
    private final UserServiceInterface userService;
    private final BusServiceInterface busService;




    @Autowired(required = true)
    public UserRessource(UserServiceInterface userService, BusServiceInterface busService, UserService userServiceImp) {
        this.userService = userService;
        this.busService = busService;

    }



    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = this.userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/Code/{userCode}")
    public ResponseEntity<User> getUserByUser(@PathVariable String userCode) {
        User user = this.userService.getUserByCode(userCode);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/Codes/{id}")
    public ResponseEntity<List<User>> getUsersByBus(@PathVariable Long id) {
        Bus bus = busService.getBusById(id);


        List<User> users = this.userService.getUsersByBus(bus);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }



    @PostMapping("/{userId}/thisbus/{busId}")
    public ResponseEntity<String> assignBusToUser(@PathVariable Long userId, @PathVariable Long busId) {
        try {
            User user = userService.getUserById(userId);
            if (user == null) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }

            Bus bus = busService.getBusById(busId);
            if (bus == null) {
                return new ResponseEntity<>("Bus not found", HttpStatus.NOT_FOUND);
            }

            user.setBus(bus);
            userService.addUser(user);

            return new ResponseEntity<>("Bus assigned to user successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





    @GetMapping("/busbyiduser/{userId}")
    public ResponseEntity<Bus> getBusByUserId(@PathVariable Long userId) {
        Bus bus = this.userService.getBusByUser(userId);
        return new ResponseEntity<>(bus, HttpStatus.OK);
    }

    @PostMapping("/add")

    public ResponseEntity<String> saveUser(@RequestBody User user) {
        try {
            userService.addUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }
    @GetMapping("/getUserNotAffected")
    public  List<User>  getUserNotAffected()
    {
        return userRepository.getListUserNotAffected();

    }

    @GetMapping("/getAffectedUser/{idBus}")
    public  List<User> getAffectedUser(@PathVariable Long idBus)

    {
        return userService.getAffectedUser(idBus);
    }

    @GetMapping("/getAffectedAndNotAffectedUser/{idBus}")
    public  List<User> getAffectedAndNotAffectedUser(@PathVariable Long idBus)
    {
        return userService.getStationsAffectedAndNotAffected(idBus);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id)
    {
        userRepository.deleteById(id);
    }


}



