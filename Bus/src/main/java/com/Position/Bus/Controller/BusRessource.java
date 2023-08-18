package com.Position.Bus.Controller;


import com.Position.Bus.Model.Bus;
import com.Position.Bus.Model.User;
import com.Position.Bus.Repository.BusRepository;
import com.Position.Bus.Repository.UserRepository;
import com.Position.Bus.Service.BusService;
import com.Position.Bus.Service.BusServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/Bus")
@CrossOrigin(origins = "*")
public class BusRessource {
    private final BusServiceInterface busService;

    @Autowired(required=true)
    public BusRessource(BusServiceInterface busService) {
        this.busService = busService;
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

public ResponseEntity<String> saveBus(@RequestBody Bus bus) {
    try {
        busService.saveBus(bus);
        return ResponseEntity.status(HttpStatus.CREATED).body("Bus created successfully");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

    }
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
//    @PutMapping("/{UserId}/{busId}")
//    User assignBusToUser(
//            @PathVariable Long userId,
//            @PathVariable Long busId
//    ) {
//        User user = userRepository.findById(userId).get();
//        Bus bus = busRepository.findById(busId).get();
//        user.setBus(bus);
//        return userRepository.save(user);
//    }


    @PutMapping("/edit")
    public ResponseEntity<Bus> updateBus(@RequestBody Bus bus)
    {
        return new ResponseEntity<> (this.busService.updateBus(bus), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteBus(@RequestParam("id") Long id){
        this.busService.deleteBus(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/find")
    public ResponseEntity<Bus> getBusById(@RequestParam("id") Long id){
        Bus bus= this.busService.getBusById(id);
        return new ResponseEntity<>(bus,HttpStatus.OK);
    }


}



