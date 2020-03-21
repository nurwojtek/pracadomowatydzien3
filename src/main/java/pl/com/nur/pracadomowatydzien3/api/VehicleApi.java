package pl.com.nur.pracadomowatydzien3.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.nur.pracadomowatydzien3.model.Car;
import pl.com.nur.pracadomowatydzien3.service.VehicleService;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
public class VehicleApi {

    private VehicleService vehicleList;

    public VehicleApi(VehicleService vehicleList) {
        this.vehicleList = vehicleList;
    }

    @GetMapping(produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})    // dodaje XML
    public ResponseEntity<VehicleService> getVehicles(){
        return new ResponseEntity(vehicleList.getVehicleList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleService> getVehicles(@PathVariable Long id){
        Optional<Car> first = vehicleList.getVehicleList().stream()
                              .filter(vehicleList -> vehicleList.getId()==id).findFirst();
        if(first.isPresent()){
            return new ResponseEntity(first, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/query")
    //    http://localhost:8080/vehicles/query?color=Czerwony
    public ResponseEntity<VehicleService> getVehicles(@RequestParam String color){
        return new ResponseEntity(vehicleList.searchColor(color), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addVehicle(@RequestBody Car car){
       boolean add = vehicleList.addVehicle(car);
       if(add){
           return new ResponseEntity(HttpStatus.CREATED);
       }
       return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity modVehicle(@RequestBody Car newCar){
        Optional<Car> first = vehicleList.getVehicleList().stream()
                .filter(vehicleList -> vehicleList.getId()==newCar.getId()).findFirst();
        if(first.isPresent()){
            vehicleList.getVehicleList().remove(first.get());
            vehicleList.getVehicleList().add(newCar);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PatchMapping
    public ResponseEntity modElementVehicle(@RequestParam Long id,
                                            @RequestParam(required =false) String mark,
                                            @RequestParam(required =false) String model,
                                            @RequestParam(required =false) String color){
        Optional<Car> first = vehicleList.getVehicleList().stream()
                .filter(vehicleList -> vehicleList.getId()==id).findFirst();
        if(first.isPresent()){
            if(mark != null){
                first.get().setMark(mark);
            }
            if(model != null){
                first.get().setModel(model);
            }
            if(color != null){
                first.get().setColor(color);
            }
            return new ResponseEntity(HttpStatus.CREATED);
        }
    return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeVehicle(@PathVariable Long id){
        Optional<Car> first = vehicleList.getVehicleList().stream()
                .filter(vehicleList -> vehicleList.getId()==id).findFirst();
        if(first.isPresent()){
            vehicleList.getVehicleList().remove(first.get());
            return new ResponseEntity(first, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
    }




}
