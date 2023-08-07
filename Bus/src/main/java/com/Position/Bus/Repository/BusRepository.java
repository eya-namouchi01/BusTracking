package com.Position.Bus.Repository;

import com.Position.Bus.Model.Bus;
import com.Position.Bus.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusRepository extends JpaRepository<Bus,Long>{
Optional<Bus> findById(Long id);

}





