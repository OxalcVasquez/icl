/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gob.pe.icl.service.impl;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.dao.inter.InterDaoBike;
import gob.pe.icl.dao.inter.InterDaoCar;
import gob.pe.icl.dao.inter.InterDaoUser;
import gob.pe.icl.entity.Bike;
import gob.pe.icl.entity.Car;
import gob.pe.icl.entity.User;
import gob.pe.icl.service.inter.InterServiceBike;
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
public class ServiceBikeImpl implements InterServiceBike {

    @Autowired
    private InterDaoBike dao;

    @Override
    public Bike saveBike(Bike entidad) throws UnknownException {
        Transaction tx = dao.getSession().beginTransaction();
        entidad.setIsPersistente(Boolean.TRUE);
        entidad.setVersion((new Date()).getTime());
        dao.save(entidad);
        tx.commit();
        return entidad;
    }

    @Override
    public Bike getBikeById(long id) {
        try {
            return dao.findById(id);
        } catch (UnknownException ex) {
            Logger.getLogger(ServiceUserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Bike> getBikeByUserId(int userId) {
        try {
            List<Bike> list = new ArrayList<>();
            for (Bike bike : dao.allFields()) {
                if (bike.getUserId() == userId) {
                    list.add(bike);
                }
            }

            return list;

        } catch (UnknownException ex) {
            Logger.getLogger(ServiceUserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
