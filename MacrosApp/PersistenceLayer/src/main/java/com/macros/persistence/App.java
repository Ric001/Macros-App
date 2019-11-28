package com.macros.persistence;

import java.util.Optional;

import com.macros.persistence.dao.IDAO;
import com.macros.persistence.dao.constants.DBProviders;
import com.macros.persistence.factory.DAOFactory;
import com.macros.persistence.services.IPersistenceService;
import com.macros.persistence.services.PersistenceService;

public class App 
{
    public static void main( String[] args )
    {
        final Optional<IDAO> dao = new DAOFactory().daoByProvider(DBProviders.MYSQL);
        final IPersistenceService persistenceService = new PersistenceService(dao.get()); 
        System.out.println(String.format("=====> IPersistanceService Instance: [%s] <=====", persistenceService));
    }

}
