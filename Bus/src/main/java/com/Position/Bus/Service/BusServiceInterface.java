package com.Position.Bus.Service;


import com.Position.Bus.Model.Bus;

import java.util.List;
import java.util.Map;

public interface BusServiceInterface {

 //  void saveBus();
 Bus getBusById(Long id);
    void saveBus(Bus bus);

    //  Map<String, Double> generateRandomPosition();
    List<Bus> getAllBus();

    Map<String, Double> getPositionByIdBus(Long id);

    void updateBusPositionById(Long idBus, Double newLatitude, Double newLongitude);

}