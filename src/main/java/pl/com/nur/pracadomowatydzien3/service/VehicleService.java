package pl.com.nur.pracadomowatydzien3.service;

import org.springframework.stereotype.Service;
import pl.com.nur.pracadomowatydzien3.model.Car;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {

    List<Car> vehicleList;

    public VehicleService() {
        vehicleList= new ArrayList<>();
        vehicleList.add(new Car(1L, "Volvo", "V70", "Czarny"));
        vehicleList.add(new Car(2L, "Subaru", "Forester", "Czerwony"));
        vehicleList.add(new Car(3L, "Fiat", "126p", "Czerwony"));
    }

    public boolean addVehicle(Car car){
        boolean add = vehicleList.add(car);
        if(add){
            return true;
        }
          return  false;
    }

    public List<Car> getVehicleList(){
        return vehicleList;
    }


    // czy to dobry pomysł w springu ???
    public List<Car> searchColor(String color){
        List<Car> colorVehicleList = new ArrayList<>();
        for (int i = 0; i < vehicleList.size(); i++) {
            if(vehicleList.get(i).getColor().equals(color)){
                System.out.println(" kolor pojazdu " + vehicleList.get(i).getColor());
                colorVehicleList.add(vehicleList.get(i));
            }
        }
        return colorVehicleList;
    }


}
