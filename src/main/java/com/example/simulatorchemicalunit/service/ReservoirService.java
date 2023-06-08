package com.example.simulatorchemicalunit.service;

import com.example.simulatorchemicalunit.models.ApiResponse;
import com.example.simulatorchemicalunit.models.ApiResponseList;
import com.example.simulatorchemicalunit.models.Reservoir;
import com.example.simulatorchemicalunit.repository.IReservoirRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservoirService implements IReservoirService{
    private final IReservoirRepository _repository;
    public ReservoirService(IReservoirRepository repository){
        _repository = repository;
    }
    @Override
    public ApiResponse Create(Reservoir entity) {
        var result = _repository.save(entity);
        return new ApiResponse("Резервуар успішно створено", result);
    }

    @Override
    public ApiResponse GetById(int id) {
        var result = _repository.findById(id);
        if(result.isEmpty()) return GetMessageReservoirWithIdNotFound(id);

        return new ApiResponse("",result.get());

    }

    @Override
    public ApiResponseList GetAll() {
        var result = _repository.findAll();
        if(result.isEmpty()) return new ApiResponseList("Нема жодного резервуара", null);

        return new ApiResponseList("", result);
    }

    @Override
    public ApiResponse RunMechanism(int id) {
        var model = _repository.findById(id);
        if(model.isEmpty()) return GetMessageReservoirWithIdNotFound(id);

        var reservoir = model.get();
        if(!reservoir.isWorking()){
            reservoir.setWorking(true);
            reservoir.setOutputPipeConnected(true);
        }

        if(reservoir.getCurrentLevel() <= reservoir.getLowLevelSensor()){
            reservoir.setInputPipeConnected(true);
            reservoir.setOutputPipeConnected(false);
        }

        if(reservoir.getCurrentLevel() >= reservoir.getHighLevelSensor()){
            reservoir.setInputPipeConnected(false);
            reservoir.setOutputPipeConnected(true);
        }
         var result = _repository.save(HandleReservoir(reservoir));
        return new ApiResponse("",result);
    }

    private Reservoir HandleReservoir(Reservoir reservoir) {
        if(reservoir.isInputPipeConnected() && !reservoir.isOutputPipeConnected()){
            reservoir.setCurrentLevel(reservoir.getCurrentLevel() + 2);
        }
        if(reservoir.isOutputPipeConnected() && !reservoir.isInputPipeConnected()){
            reservoir.setCurrentLevel(reservoir.getCurrentLevel() - 2);
        }
        return reservoir;
    }

    @Override
    public ApiResponse Update(Reservoir entity) {
        var existingReservoir = _repository.findById(entity.getId());
        if(existingReservoir.isEmpty()) return GetMessageReservoirWithIdNotFound(entity.getId());

        if(!entity.isWorking()){
            entity.setInputPipeConnected(false);
            entity.setOutputPipeConnected(false);
        }
        var result = _repository.save(entity);
        return new ApiResponse("", result);
    }

    @Override
    public ApiResponse Delete(int id) {
        var existingReservoir = _repository.findById(id);
        if(existingReservoir.isEmpty()) return GetMessageReservoirWithIdNotFound(id);
        _repository.delete(existingReservoir.get());

        return new ApiResponse("Успішно видалено!", null);
    }
    private ApiResponse GetMessageReservoirWithIdNotFound(int id){
        return new ApiResponse("Не знайдено резервуар з Id = " + id,null);
    }
}
