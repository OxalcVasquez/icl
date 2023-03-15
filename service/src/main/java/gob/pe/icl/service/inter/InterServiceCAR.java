/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gob.pe.icl.service.inter;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.entity.Car;
import gob.pe.icl.entity.User;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface InterServiceCar {
    Car saveCar(Car entidad)throws UnknownException;
    Car getCarById(long id);
    List<Car> getCarByUserId(int userId);
}
