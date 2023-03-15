/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gob.pe.icl.api.bike;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.entity.Bike;
import gob.pe.icl.entity.Car;
import gob.pe.icl.service.impl.ServiceBikeImpl;
import gob.pe.icl.service.impl.ServiceCarImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author usuario
 */

@RestController
@RequestMapping("api/bike")
public class BikeController {
    
    @Autowired
    private ServiceBikeImpl carBike;
    
    @PostMapping(value="save", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Bike> save(@RequestBody Bike bike) throws UnknownException{        
        return ResponseEntity.status(HttpStatus.CREATED).body(carBike.saveBike(bike));
        
    }
    
     @PostMapping(value="/{id}")
    public ResponseEntity<Bike> getById(@PathVariable("id") int id) throws UnknownException{        
        Bike car = carBike.getBikeById(id);
        if(car == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(car);
    }
    
    
    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Bike>> getByUserId(@PathVariable("userId") int userId) {
        List<Bike> bikes = carBike.getBikeByUserId(userId);
        if(bikes.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(bikes);
    }
    
}
