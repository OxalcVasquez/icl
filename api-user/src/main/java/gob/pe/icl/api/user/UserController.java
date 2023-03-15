/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gob.pe.icl.api.user;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.entity.User;
import gob.pe.icl.service.impl.ServiceUserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("api/user")
public class UserController {
    
    @Autowired
    private ServiceUserImpl userService;
    
    @PostMapping(value="save", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> save(@RequestBody User user) throws UnknownException{        
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
        
    }
    
     @PostMapping(value="/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id) throws UnknownException{        
        User user = userService.getUserById(id);
        if(user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }
    
    
}
