package com.macros.persistence;

import com.macros.persistence.dao.constants.DBProviders;
import com.macros.persistence.factory.DAOFactoryMethod;
import com.macros.persistence.factory.MySQLDaoFactory;
import com.macros.persistence.model.Order;
import com.macros.persistence.services.IPersistenceService;
import com.macros.persistence.services.PersistenceService;

public class App 
{
    public static void main( String[] args )
    {
        final DAOFactoryMethod daoFactoryMethod = new MySQLDaoFactory();
        final IPersistenceService persistenceService = new PersistenceService(daoFactoryMethod.daoByProvider(DBProviders.MYSQL));
        persistenceService.create(new Order());
    }

}
