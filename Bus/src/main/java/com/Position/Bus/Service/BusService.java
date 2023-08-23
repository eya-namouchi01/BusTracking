package com.Position.Bus.Service;

import com.Position.Bus.Exeption.UserNotFoundException;
import com.Position.Bus.Model.*;
import com.Position.Bus.Repository.*;
import com.Position.Bus.Repository.CircuitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class BusService implements BusServiceInterface {

    @Autowired
    private final CircuitRepository circuitRepository;
    private final BusRepository busRepository;
    private final UserRepository userRepository;


    @Autowired
    public BusService(CircuitRepository circuitRepository,BusRepository busRepository,UserRepository userRepository) {
        this.circuitRepository = circuitRepository;
        this.userRepository = userRepository;
        this.busRepository= busRepository;
    }




 //   @Override
//    public void saveBus() {
//        Bus bus = new Bus();
//        var position = this.generateRandomPosition();
//        bus.setLongitude(position.get("long"));
//        bus.setLatitude(position.get("lat"));
//        busRepository.save(bus);
//    }
    @Override
    public Long saveBus(Bus bus) {
        //Bus bus = new Bus();

        var position = this.generateRandomPosition();
//        bus.setLongitude(position.get("long"));
//        bus.setLatitude(position.get("lat"));
//        Circuit circuit = this.circuitRepository.findCircuitBynom(nom).
//                orElseThrow(()->new UserNotFoundException("User with ID "+nom+" was not found" ));
        bus.setLongitude(position.get("long"));
        bus.setLatitude(position.get("lat"));
        Bus b=busRepository.save(bus);
        return b.getId();
    }


    public  Map<String, Double> generateRandomPosition() {
       // Hashtable <List<String>, List<Double>> randomPosition = new Hashtable<List<String>,List<Double>>();
       Map<String, Double> randomPosition = new HashMap<>();

        // Generate random latitude (-90 to 90 degrees)
        Random random = new Random();
        Double  latitude = random.nextDouble() * 180.0 - 90.0;
        Double longitude = random.nextDouble() * 360.0 - 180.0;
        //randomPosition.put({"lat:",latitude,"long:",longitude});
        randomPosition.put("long",longitude);
        randomPosition.put("lat",latitude);
        return randomPosition;}


    public List<Bus> getAllBus(){
        return this.busRepository.findAll();
    }

    public Map<String, Double>  getPositionByIdBus(Long id) {
        Map<String, Double> BusPosition = new HashMap<>();
        Bus bus= this.busRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Bus " + id + " was not found"));
        BusPosition.put("long",bus.getLongitude());
        BusPosition.put("lat",bus.getLatitude());
        return BusPosition;

    }
    public void updateBusPositionById(Long idBus, Double newLatitude, Double newLongitude) {
        // Find the Bus entity by its id
        Optional<Bus> optionalBus = busRepository.findById(idBus);
        // If the Bus exists, get the associated Location entity
        Bus bus = optionalBus.get();

        // Update the latitude and longitude properties
        bus.setLatitude(newLatitude);
        bus.setLongitude(newLongitude);
        // Save the updated Location entity back to the database
        busRepository.save(bus);
    }

    public Bus getBusById(Long id) {
        Optional<Bus> bus = busRepository.findById(id);
        return bus.orElse(null);
    }

    public Bus affectBusToCircuit(Long idBus, Long idCircuit)
    {
       Optional<Bus> bus = busRepository.findById(idBus);
        Optional<Circuit> circuit = circuitRepository.findById(idCircuit);
       bus.get().setCircuit(circuit.get());
        return busRepository.save(bus.get());
    }

    public Bus updateBus(Bus bus) {
       return this.busRepository.save(bus);}

    public void deleteBus(Long id) {
        this.busRepository.deleteById(id);
    }
    public Optional<List<User>> getUsersByBusId(long id) {
        Optional<Bus> bus = busRepository.findById(id);

        if (bus.isPresent()) {
            Optional<List<User>> users = userRepository.findByBus(bus.get());
            return users;
        }
        return null;
    }

    @Override
    public void affectUserToBus(Long userId, Long busId) {
        try
        {
            Bus bus = getBusById(busId);
            Optional<User> userOptional = userRepository.findById(userId);
            if(userOptional.isPresent())
            {
                User user = userOptional.get();
                user.setBus(bus);
                userRepository.save(user);
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }

    @Override
    public void affectUsersListToBus(Long busId, List<User> users) {
        for(User user : users)
        {
            try{
            affectUserToBus(user.getId(), busId); }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }
}
