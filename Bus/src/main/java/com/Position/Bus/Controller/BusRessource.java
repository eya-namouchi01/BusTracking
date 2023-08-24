package com.Position.Bus.Controller;


import com.Position.Bus.Model.*;
import com.Position.Bus.Model.User;
import com.Position.Bus.Repository.BusRepository;
import com.Position.Bus.Repository.UserRepository;
import com.Position.Bus.Service.BusServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/Bus")
@CrossOrigin(origins = "*")
public class BusRessource {
    private final BusRepository busRepository;
    private final BusServiceInterface busService;
    private final UserRepository userRepository;


    @Autowired(required=true)
    public BusRessource(BusRepository busRepository, BusServiceInterface busService, UserRepository userRepository) {
        this.busRepository = busRepository;
        this.busService = busService;
        this.userRepository = userRepository;
    }

//    @PostMapping("/addBus")
//    public ResponseEntity<String> saveBus() {
//      try {
//            busService.saveBus();
//            return ResponseEntity.status(HttpStatus.CREATED).body("Position created successfully");
//       } catch (Exception e) {
//          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//
//      }
//    }
@PostMapping("/add")

public Long saveBus(@RequestBody Bus bus) {

       return busService.saveBus(bus);


}

    @GetMapping("/all")
    public ResponseEntity<List<Bus>> getAllBus() {
            List<Bus> buss=this.busService.getAllBus();
            return new ResponseEntity<>(buss,HttpStatus.OK);
        }
    @GetMapping("/getPositionById/{id}")
    public ResponseEntity< Map<String, Double>>getPositionbByIdBus(@PathVariable Long id) {
       var position = busService.getPositionByIdBus(id);
        return new ResponseEntity(position, HttpStatus.OK);


   }
    @PostMapping("/updateBusPosition/{busId}")
    public ResponseEntity<String> updateBusPosition(
            @PathVariable Long busId, @RequestBody Bus updateBusPositionById) {
        try {
            // Update the position of the bus with the provided id
            busService.updateBusPositionById
                    (busId, updateBusPositionById.getLatitude(),updateBusPositionById.getLongitude());
            return ResponseEntity.ok("Bus position updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update bus position.");
        }
    }

    @PostMapping("affecterBusCircuit/{idBus}/{idCircuit}")
    public String  affectBusToCircuit(@PathVariable Long idBus,@PathVariable Long idCircuit)
    {
        busService.affectBusToCircuit(idBus, idCircuit);
        return "affected";
    }

    /*
     @PutMapping("/edit/{id}/{busNom}")
    public ResponseEntity<Bus> updateBus(@RequestBody Bus bus, @PathVariable Long id, @PathVariable String busNom)
    {
        bus.setId(id);
        return new ResponseEntity<> (this.busService.updateBus(bus), HttpStatus.CREATED);
    }

    */
   // @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/edit/{id}/{busNom}")
    public Long updateBus(@RequestBody List<User> users, @PathVariable Long id, @PathVariable String busNom)
    {
      Optional<Bus> busOptional = busRepository.findById(id);
      if(busOptional.isPresent())
      {
          Bus bus  = busOptional.get();
          bus.setBus(busNom);
          this.busService.updateBus(bus);
      }

      userRepository.disassociateUserBus(id);
    /*  for(User user : users)
      {
          Bus b = busService.getBusById(id);
          user.setBus(b);
          userRepository.save(user);
      }
      */
        return id;
    }

    @PutMapping("/disassociateUserBus/{busId}")
    public void disassociateUserBus(@PathVariable Long busId,@RequestBody List<Bus> bus)
    {
        userRepository.disassociateUserBus(busId);

    }

    @DeleteMapping("/{id}")
    public void deleteBus(@PathVariable("id") Long id){
        this.busService.deleteBus(id);
    }

    @GetMapping("/find")
    public ResponseEntity<Bus> getBusById(@RequestParam("id") Long id){
        Bus bus= this.busService.getBusById(id);
        return new ResponseEntity<>(bus,HttpStatus.OK);
    }
    @GetMapping("/UsersByBusId/{id}")
    public ResponseEntity<List<User>> getUsersByBusId(@PathVariable Long id) {
        Optional<List<User>> users=this.busService.getUsersByBusId(id);

        return new ResponseEntity<>(users.get(),HttpStatus.OK);
    }

    @PostMapping("/affectUserToBus/{userId}/{busId}")
    public void affectUserToBus(@PathVariable Long userId, @PathVariable Long busId)
    {
        busService.affectUserToBus(userId,busId);
    }
    @PostMapping("/affectUsersListToBus/{busId}")
    public void affectUserToBus(@PathVariable  Long busId,@RequestBody List<User> users)
    {
        busService.affectUsersListToBus(busId,users);
    }

    @PostMapping("/affectCircuitToBus/{CircuitId}/{BusId}")
    public void affectCircuitToBus(@PathVariable Long CircuitId, @PathVariable Long BusId)
    {
        busService.affectCircuitToBus(CircuitId,BusId);
    }









}



