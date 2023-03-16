/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gob.pe.icl.service.impl;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.dao.inter.InterDaoCar;
import gob.pe.icl.dao.inter.InterDaoUser;
import gob.pe.icl.entity.Car;
import gob.pe.icl.entity.User;
import gob.pe.icl.service.inter.InterServiceCar;
import gob.pe.icl.service.inter.InterServiceUser;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.Transaction;

/**
 *
 * @author Usuario
 */
@Service
public class ServiceCarImpl implements InterServiceCar {

    @Autowired
    private InterDaoCar dao;

    @Override
    public Car saveCar(Car entidad) throws UnknownException {
        Transaction tx = dao.getSession().beginTransaction();
        entidad.setIsPersistente(Boolean.TRUE);
        entidad.setVersion((new Date()).getTime());
        dao.save(entidad);
        tx.commit();
        return entidad;
    }

    @Override
    public Car getCarById(long id) {
        Car car = null;
        try {
            Transaction tx = dao.getSession().beginTransaction();
            car = dao.findById(id);
            tx.commit();
        } catch (UnknownException ex) {
            Logger.getLogger(ServiceUserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return car;
    }

    @Override
    public List<Car> getCarByUserId(int userId) {
        List<Car> list = new ArrayList<>();
        try {
            Transaction tx = dao.getSession().beginTransaction();
            for (Car car : dao.allFields()) {
                if (car.getUserId() == userId) {
                    list.add(car);
                }
            }
            tx.commit();
        } catch (UnknownException ex) {
            Logger.getLogger(ServiceUserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
