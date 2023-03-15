
import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.entity.User;
import gob.pe.icl.service.impl.ServiceUserImpl;
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
public class TestUserService extends TestBaseService {
    
    @Test
    public void createEntity1() throws UnknownException {
        InterServiceUser service = contextService.getBean(ServiceUserImpl.class);
        User user = contextEntity.getBean(User.class);
        user.setEmail("test@test");
        user.setName("Test user");
        user.setIsPersistente(Boolean.TRUE);
        user.setVersion(Long.MIN_VALUE);        
        service.saveUser(user);
    }
}
