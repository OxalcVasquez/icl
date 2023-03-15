
import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.entity.Bike;
import gob.pe.icl.entity.Car;
import gob.pe.icl.entity.User;
import gob.pe.icl.service.impl.ServiceBikeImpl;
import gob.pe.icl.service.impl.ServiceCarImpl;
import gob.pe.icl.service.impl.ServiceUserImpl;
import gob.pe.icl.service.inter.InterServiceBike;
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
public class TestBikeService extends TestBaseService {
    
    @Test
    public void createEntity1() throws UnknownException {
        InterServiceBike service = contextService.getBean(ServiceBikeImpl.class);
        Bike bike = contextEntity.getBean(Bike.class);
        bike.setModel("3422");
        bike.setBrand("Goliat");
        bike.setUserId(1);
        bike.setIsPersistente(Boolean.TRUE);
        bike.setVersion(Long.MIN_VALUE);        
        service.saveBike(bike);
    }
}
