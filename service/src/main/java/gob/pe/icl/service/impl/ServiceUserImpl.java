/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gob.pe.icl.service.impl;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.dao.inter.InterDaoUser;
import gob.pe.icl.entity.Bike;
import gob.pe.icl.entity.Car;
import gob.pe.icl.entity.User;
import gob.pe.icl.service.inter.InterServiceUser;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.Transaction;

/**
 *
 * @author Usuario
 */
@Service
public class ServiceUserImpl implements InterServiceUser {

    @Autowired
    private InterDaoUser dao;

//    @Autowired
//    CarFeign carFeign;
//    @Autowired
//    BikeFeign bikeFeign;
//    @Autowired
//    RestTemplate restTemplate;
    @Override
    public User saveUser(User entidad) throws UnknownException {
        Transaction tx = dao.getSession().beginTransaction();
        entidad.setIsPersistente(Boolean.TRUE);
        entidad.setVersion((new Date()).getTime());
        dao.save(entidad);
        tx.commit();
        return entidad;
    }

    @Override
    public User getUserById(long id) {
        User user = null;
        try {
            Transaction tx = dao.getSession().beginTransaction();
            user = dao.findById(id);
            tx.commit();
        } catch (UnknownException ex) {
            Logger.getLogger(ServiceUserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    @Transactional
    public User getUserWithCars(long id) {
        User user = null;
        try {
            Transaction tx = dao.getSession().beginTransaction();
            user = dao.findById(id);
            if (user != null) {
                Hibernate.initialize(user.getCars());
                List<Car> cars = user.getCars();
                Hibernate.initialize(user.getBikes());
                List<Bike> bikes = user.getBikes();
                dao.getSession().detach(user); //desconecta de la BD, lo mantiene en caché
//              dao.getSession().evict(user); // desconecta de la BD y lo elimina del caché

            }

            tx.commit();
        } catch (UnknownException ex) {
            Logger.getLogger(ServiceUserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

//     public List<Car> getCars(int userId){
//     List<Car> cars = restTemplate.getForObject("http:localhost:9002/api/car/byuser/" + userId, List.class);
//     return cars;
//    }
//    
//    public List<Bike> getBikes(int userId){
//     List<Bike> bikes = restTemplate.getForObject("http:localhost:9003/api/bike/byuser/" + userId, List.class);
//     return bikes;
//    }
//    
//     public Car saveCar(int userId, Car car) throws UnknownException{
//        car.setUserId(userId);
//        Car carNew = carFeign.saveCar(car);
//        return  carNew;
//    }
//    public Bike saveBike(int userId, Bike bike) throws UnknownException {
//        bike.setUserId(userId);
//        Bike bikeNew = bikeFeign.saveBike(bike);
//        return  bikeNew;
//    }
//    
//      public Map<String, Object> getUserAndVehicles(int userId) throws UnknownException {
//        Map<String, Object> result = new HashMap<>();
//        Transaction tx = dao.getSession().beginTransaction(); 
//        User user = dao.findById(userId);
//        if(user == null) {
//            result.put("Mensaje", "no existe el usuario");
//            return result;
//        }
//        result.put("User", user);
//        List<Car> cars = carFeign.getCars(userId);
//        if(cars.isEmpty())
//            result.put("Cars", "ese user no tiene coches");
//        else
//            result.put("Cars", cars);
//        List<Bike> bikes = bikeFeign.getBikes(userId);
//        if(bikes.isEmpty())
//            result.put("Bikes", "ese user no tiene motos");
//        else
//            result.put("Bikes", bikes);
//        tx.commit();
//        return result;
//    }
    @Override
    @Transactional
    public List<Car> findCarsByUserId(Long user_id) throws UnknownException {
        Transaction tx = dao.getSession().beginTransaction();
        try {
            User user = dao.findById(user_id);
            Hibernate.initialize(user.getCars());
            List<Car> cars = user.getCars();
            if (user == null) {
                throw new UnknownException(ServiceUserImpl.class, "No se pudo encontrar el usuario con id " + user_id);
            }

            tx.commit();
            return cars;
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se pudo llamar la lista de motos para el usuario con id " + user_id);
        }
    }

    @Override
    @Transactional
    public List<Bike> findBikesByUserId(Long userId) throws UnknownException {
        Transaction tx = dao.getSession().beginTransaction();
        try {
            User user = dao.findById(userId);
            Hibernate.initialize(user.getBikes());
            List<Bike> bikes = user.getBikes();
            if (user == null) {
                throw new UnknownException(ServiceUserImpl.class, "No se pudo encontrar el usuario con id " + userId);
            }
            tx.commit();
            return bikes;
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se pudo llamar la lista de motos para el usuario con id " + userId);
        }

    }

    @Override
    public User fingByUsername(String username) throws UnknownException {

        User user = null;
        try {
            Transaction tx = dao.getSession().beginTransaction();
            user = (User) dao.allFields("=:username:" + username, null);
            tx.commit();
        } catch (UnknownException ex) {
            Logger.getLogger(MyUserDetailsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
}
