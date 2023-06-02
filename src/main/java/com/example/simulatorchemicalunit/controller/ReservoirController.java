package com.example.simulatorchemicalunit.controller;

import com.example.simulatorchemicalunit.models.ApiResponse;
import com.example.simulatorchemicalunit.models.ApiResponseList;
import com.example.simulatorchemicalunit.models.Reservoir;
import com.example.simulatorchemicalunit.service.IReservoirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/reservoirs")
public class ReservoirController {
    @Autowired
    IReservoirService reservoirService;

    @GetMapping("/info")
    public ResponseEntity<ApiResponse> Info(int id){
        var result = reservoirService.RunMechanism(id);
        if(result.getReservoir() != null){
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> CreateComputer(@RequestBody Reservoir reservoir){
        var result = reservoirService.Create(reservoir);
        if(result.getReservoir() != null){
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ApiResponse> GetById(@PathVariable int id){
        var result = reservoirService.GetById(id);
        if(result.getReservoir() != null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponseList> GetAll(){
        var result = reservoirService.GetAll();
        if(result.getReservoirList() != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> Update(@RequestBody Reservoir model){
        var result = reservoirService.Update(model);
        if(result.getReservoir() != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> Delete(@PathVariable int id){
        var result = reservoirService.Delete(id);
        if(Objects.equals(result.getMessage(), "Успішно видалено!")){
            return  new ResponseEntity<>(result, HttpStatus.OK);
        }
        else{
            return  new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

    }
}
