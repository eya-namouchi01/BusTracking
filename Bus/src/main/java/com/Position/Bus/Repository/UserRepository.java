package com.Position.Bus.Repository;

import com.Position.Bus.Model.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findById(Long id);
    Optional<User> findByUserCode(String userCode);
    Optional<List<User>> findByBus(Bus bus);

    @Query("SELECT s FROM User s WHERE s.bus.id IS NULL")
    List<User> getListUserNotAffected();

    @Query("SELECT s FROM User s WHERE s.bus.id IS NULL OR s.bus.id = :id")
    List<User> getStationsAffectedAndNotAffected(@Param("id") Long id);


    @Query("SELECT s FROM User s WHERE s.bus.id = :id")
    List<User> getAffectedUser(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE User s SET s.bus.id = null WHERE s.bus.id = :busId")
    void disassociateUserBus(Long busId);


}



