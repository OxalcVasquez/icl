/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gob.pe.icl.service.feign;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.entity.Car;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author usuario
 */

@FeignClient(name = "api-car-dev")
@Component
public interface CarFeign {
    
    @PostMapping("/api/car/save")
    Car saveCar(@RequestBody Car car) throws UnknownException;
    
    @GetMapping("/api/car/byuser/{userId}")
    List<Car> getCars(@PathVariable("userId") int userId);
}
