/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gob.pe.icl.service.inter;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.entity.User;

/**
 *
 * @author Usuario
 */
public interface InterServiceUser {
    User saveUser(User entidad)throws UnknownException;
    User getUserById(long id);
}
