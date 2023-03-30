/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gob.pe.icl.dao.impl;

import com.jofrantoba.model.jpa.daoentity.AbstractJpaDao;
import gob.pe.icl.dao.inter.InterDaoParametrias;
import gob.pe.icl.entity.Parametrias;
import java.util.Collection;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author usuario
 */

@Repository
public class DaoParametrias extends AbstractJpaDao<Parametrias> implements InterDaoParametrias{
    
    public DaoParametrias(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super();
        setClazz(Parametrias.class);
        this.setSessionFactory(sessionFactory);
    }

    
    
    
}
