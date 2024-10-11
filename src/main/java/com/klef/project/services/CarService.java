package com.klef.project.services;


import javax.ejb.Remote;
import java.util.List;

import com.klef.project.models.Car;

@Remote
public interface CarService {
  public String addCar(Car car);
  public String updateCar(Car car);
  public String deleteCar(int eid);
  public List<Car> viewallCars();
  public Car viewCarById(int id);
}