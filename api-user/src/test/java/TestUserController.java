
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.entity.User;
import gob.pe.icl.service.impl.ServiceUserImpl;
import gob.pe.icl.service.inter.InterServiceUser;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author usuario
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestUserController extends TestBaseController {
    
   @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Test    
    public void saveUser() throws Exception {
        log.info("saveUser");
        JSONObject user=new JSONObject();        
        user.put("name", "Jona");
        user.put("email", "jona@develtrex.com");
        mockMvc.perform(MockMvcRequestBuilders.post("api/user/save")
                .contentType("application/json").content(user.toString()))                
                .andExpect(status().isOk());
    }
}
