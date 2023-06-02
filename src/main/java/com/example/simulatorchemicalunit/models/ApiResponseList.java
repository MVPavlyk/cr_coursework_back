package com.example.simulatorchemicalunit.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@JsonAutoDetect

public class ApiResponseList {
    private String message;
    private List<Reservoir> reservoirList;
}
