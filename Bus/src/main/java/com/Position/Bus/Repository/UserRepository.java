package com.Position.Bus.Repository;

import com.Position.Bus.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findById(Long id);
    Optional<User> findByUserCode(String userCode);
    Optional<List<User>> findByBus(Bus bus);

    @Query("SELECT s FROM User s WHERE s.bus.id = null ")
    List<Station> getListUserNotAffected();

    @Query("SELECT s FROM User s WHERE s.bus.id = null or s.bus.id = :id")
    List<Station> getStationsAffectedAndNotAffected(Long id);
}



