package com.example.simulatorchemicalunit.models;

import com.example.simulatorchemicalunit.service.ReservoirService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonAutoDetect
public class ApiResponse {
    private String message;
    private Reservoir reservoir;
}
