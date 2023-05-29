package com.drones.models;

import com.drones.enums.DroneEventStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@Entity
@Table(name = "drone_events")
@AllArgsConstructor
@NoArgsConstructor
public class DroneEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "droneSerialNumber",unique = true)
    private String droneSerialNumber;
    @Column(unique = true)
    private double currentLoad;
    @Column(unique = true)
    private LocalDateTime dateTime;
    @NotNull
    @Enumerated(EnumType.STRING)
    private DroneEventStatus eventStatus;
    @ElementCollection
    @CollectionTable(name = "medication_carried_by_drone", joinColumns = @JoinColumn(name = "droneSerialNumber"))
    @Column(name = "medication")
    private List<String> medication;

}
