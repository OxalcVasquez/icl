/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gob.pe.icl.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Usuario
 */
@Component
@Scope("prototype")
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(catalog="demotiktok",schema="demotiktok",name = "user")
public class User extends GlobalEntityPkNumeric implements Serializable{
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Car> cars;
     
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Bike> bikes;
    
}
