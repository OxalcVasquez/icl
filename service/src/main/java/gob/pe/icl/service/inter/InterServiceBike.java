/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gob.pe.icl.service.inter;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.entity.Bike;
import gob.pe.icl.entity.Car;
import gob.pe.icl.entity.User;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface InterServiceBike {
    Bike saveBike(Bike entidad)throws UnknownException;
    Bike getBikeById(long id);
    List<Bike> getBikeByUserId(int userId);
}
