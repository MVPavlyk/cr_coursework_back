package com.example.simulatorchemicalunit.service;

import com.example.simulatorchemicalunit.models.ApiResponse;
import com.example.simulatorchemicalunit.models.ApiResponseList;
import com.example.simulatorchemicalunit.models.Reservoir;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IReservoirService {
    ApiResponse Create(Reservoir entity);
    ApiResponse GetById(int id);
    ApiResponseList GetAll();
    ApiResponse RunMechanism(int id);
    ApiResponse Update(Reservoir entity);
    ApiResponse Delete(int id);
}
