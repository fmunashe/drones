package com.drones.repositories;


import com.drones.models.DroneAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneAuditRepository extends JpaRepository<DroneAudit, Long> {
}
