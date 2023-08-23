package com.Position.Bus.Service;


import com.Position.Bus.Model.Bus;
import com.Position.Bus.Model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BusServiceInterface {

 //  void saveBus();
 Bus getBusById(Long id);
    Long saveBus(Bus bus);

    //  Map<String, Double> generateRandomPosition();
    List<Bus> getAllBus();

    Map<String, Double> getPositionByIdBus(Long id);

    void updateBusPositionById(Long idBus, Double newLatitude, Double newLongitude);

    Bus affectBusToCircuit(Long idBus, Long idCircuit);
    Bus updateBus(Bus bus);
    void deleteBus(Long id);
    Optional<List<User>> getUsersByBusId(long id);

   void affectUserToBus(Long userId, Long busId);

    void affectUsersListToBus(Long busId, List<User> users);
}