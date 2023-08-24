package com.Position.Bus.Repository;

import com.Position.Bus.Model.Bus;
import com.Position.Bus.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BusRepository extends JpaRepository<Bus,Long>{
Optional<Bus> findById(Long id);

    @Query("UPDATE Bus s SET s.circuit.id = :circuitId WHERE s.id = :busId")
    void affectCircuitToBus(Long circuitId, Long busId);
}





