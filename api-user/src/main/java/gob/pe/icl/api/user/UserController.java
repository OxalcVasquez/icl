package gob.pe.icl.api.user;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.api.user.feign.BikeFeign;
import gob.pe.icl.api.user.feign.CarFeign;
import gob.pe.icl.entity.Bike;
import gob.pe.icl.entity.Car;
import gob.pe.icl.entity.User;
import gob.pe.icl.service.impl.ServiceUserImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author usuario
 */
@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private ServiceUserImpl userService;
    @Autowired
    CarFeign carFeign;
    @Autowired
    BikeFeign bikeFeign;

    @PostMapping(value = "save", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> save(@RequestBody User user) throws UnknownException {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));

    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id) throws UnknownException {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    
     @GetMapping(value = "/{username}")
    public ResponseEntity<User> fingByUsername(@PathVariable("username") String username) throws UnknownException {
        User user = userService.fingByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

//    @GetMapping("/cars/{userId}")
//    public ResponseEntity<List<Car>> getCars(@PathVariable("userId") int userId) {
//        User user = userService.getUserById(userId);
//        if(user == null)
//            return ResponseEntity.notFound().build();
//        List<Car> cars = userService.getCars(userId);
//        return ResponseEntity.ok(cars);
//    }
//
//    @GetMapping("/bikes/{userId}")
//    public ResponseEntity<List<Bike>> getBikes(@PathVariable("userId") int userId) {
//        User user = userService.getUserById(userId);
//        if(user == null)
//            return ResponseEntity.notFound().build();
//        List<Bike> bikes = userService.getBikes(userId);
//        return ResponseEntity.ok(bikes);
//    } 
    @PostMapping("/savecar/{userId}")
    public ResponseEntity<Car> saveCar(@PathVariable("userId") int userId, @RequestBody Car car) throws UnknownException {
        if (userService.getUserById(userId) == null) {
            return ResponseEntity.notFound().build();
        }
        car.setUser(userService.getUserById(userId));
        Car carNew = carFeign.saveCar(car);
        return ResponseEntity.ok(carNew);
    }

    @PostMapping("/savebike/{userId}")
    public ResponseEntity<Bike> saveBike(@PathVariable("userId") int userId, @RequestBody Bike bike) throws UnknownException {
        if (userService.getUserById(userId) == null) {
            return ResponseEntity.notFound().build();
        }
        bike.setUser(userService.getUserById(userId));
        Bike bikeNew = bikeFeign.saveBike(bike);
        return ResponseEntity.ok(bikeNew);
    }

//    @PostMapping("/getAll/{userId}")
//    public ResponseEntity<Map<String, Object>> getAllVehicles(@PathVariable("userId") int userId) throws UnknownException {
//        Map<String, Object> result = new HashMap<>();
//        if(userService.getUserById(userId) == null)
//            return ResponseEntity.notFound().build();
//        result.put("User", userService.getUserById(userId));
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
//        return ResponseEntity.ok(result);
//    }
    @PostMapping("/getAll/{userId}")
    public ResponseEntity<Map<String, Object>> getAllVehicles(@PathVariable("userId") int userId) throws UnknownException {
        Map<String, Object> result = new HashMap<>();
        User user = userService.getUserWithCars(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        result.put("User", user);
        List<Car> cars = user.getCars();

        if(cars.isEmpty())
            result.put("Cars", "ese user no tiene coches");
        else
            result.put("Cars", cars);
        List<Bike> bikes = user.getBikes();
        if (bikes.isEmpty()) {
            result.put("Bikes", "ese user no tiene motos");
        } else {
            result.put("Bikes", bikes);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> findBikesByUserId(@PathVariable("userId") Long userId) throws UnknownException {
        if (userService.getUserById(userId) == null) {
            return ResponseEntity.notFound().build();
        }
        List<Bike> userBikes = userService.findBikesByUserId(userId);
        return ResponseEntity.ok(userBikes);
    }

    @GetMapping("/cars/{user_id}")
    public ResponseEntity<List<Car>> findCarsByUserId(@PathVariable("user_id") Long user_id) throws UnknownException {
        if (userService.getUserById(user_id) == null) {
            return ResponseEntity.notFound().build();
        }
        List<Car> userCars = userService.findCarsByUserId(user_id);
        return ResponseEntity.ok(userCars);
    }
}
