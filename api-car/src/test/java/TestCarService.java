
import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.entity.Car;
import gob.pe.icl.entity.User;
import gob.pe.icl.service.impl.ServiceCarImpl;
import gob.pe.icl.service.impl.ServiceUserImpl;
import gob.pe.icl.service.inter.InterServiceCar;
import gob.pe.icl.service.inter.InterServiceUser;
import org.junit.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author usuario
 */
public class TestCarService extends TestBaseService {
    
    @Test
    public void createEntity1() throws UnknownException {
        InterServiceCar service = contextService.getBean(ServiceCarImpl.class);
        Car car = contextEntity.getBean(Car.class);
        car.setModel("234");
        car.setBrand("Tesla");
        car.setUserId(1);
        car.setIsPersistente(Boolean.TRUE);
        car.setVersion(Long.MIN_VALUE);        
        service.saveCar(car);
    }
}
