/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gob.pe.icl.oauth;

import org.springframework.web.bind.annotation.GetMapping;
import gob.pe.icl.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
/**
 *
 * @author usuario
 */

@FeignClient(name = "api-user-dev")
public interface UserFeign {
    
     @GetMapping("/user/{username}")
    User fingByUsername(@PathVariable("username") String username);
    
}
