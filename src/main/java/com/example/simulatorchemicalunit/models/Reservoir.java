package com.example.simulatorchemicalunit.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "reservoirs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
public class Reservoir {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "current_level")
    private double currentLevel;

    @Column(name = "low_level_sensor")
    private double lowLevelSensor;

    @Column(name = "high_level_sensor")
    private double highLevelSensor;

    @Column(name = "has_heater")
    private boolean hasHeater;

    @Column(name = "has_mixer")
    private boolean hasMixer;

    @Column(name="is_working")
    private boolean isWorking;

    @Column(name = "input_pipe_connected")
    private boolean inputPipeConnected;

    @Column(name = "output_pipe_connected")
    private boolean outputPipeConnected;
}
