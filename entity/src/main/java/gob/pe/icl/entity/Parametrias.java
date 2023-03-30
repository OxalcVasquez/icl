/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gob.pe.icl.entity;

/**
 *
 * @author usuario
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.springframework.stereotype.Component;

import org.springframework.context.annotation.Scope;

import lombok.Getter;

import lombok.EqualsAndHashCode;

import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Component
@Scope("prototype")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(catalog = "icl", schema = "public", name = "parametrias")
public class Parametrias extends GlobalEntityPkNumeric implements Serializable {
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @JsonIgnoreProperties({"parent","children"})
    @ManyToOne
    @JoinColumn(name = "id_parametria_padre", referencedColumnName = "id")
    private Parametrias parent;
    
    @JsonIgnoreProperties({"parent","children"})    
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Parametrias> children;
    
    public void setTransformer(Object[] os, String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            switch (strings[i]) {
                case "id":                   
                    this.setId((Long) os[i]);
                break; 
                case "descripcion":                   
                    this.setDescripcion(os[i]!=null?os[i].toString():null);
                break; 
                case "idParent":      
                    if(parent==null){
                        this.setParent(new Parametrias());
                    }
                    this.getParent().setId((Long) os[i]);                    
                break;
                case "descripcionParent":                   
                    if(parent==null){
                        this.setParent(new Parametrias());
                    }
                    this.getParent().setDescripcion(os[i]!=null?os[i].toString():null);
                break;
            }
        }
    }
}
