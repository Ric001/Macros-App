package com.macros.persistence.factory;

import java.util.Objects;
import java.util.logging.Logger;

import com.macros.persistence.dao.IDAO;
import com.macros.persistence.dao.MySQLDAO;
import com.macros.persistence.dao.constants.DBProviders;

public class MySQLDaoFactory implements DAOFactoryMethod {

    private Logger LOG = Logger.getLogger(MySQLDAO.class.getName());

    @Override
    public IDAO daoByProvider(DBProviders provider) {
        LOG.info("[ENTERING IDAO dao(DBProviders provider)]");
        if (Objects.isNull(provider))
            return null;
        
        IDAO dao = lookupDaoType(provider);
        LOG.info("[RETURNING IDAO dao(DBProviders provider) " + dao);
        return dao;
    }

    private IDAO lookupDaoType(DBProviders provider) {
        LOG.info("[ENTERING IDAO lookupDaoType(DBProviders provider)]");
        IDAO dao = null;
        
        switch(provider) {
            case MYSQL: dao = new MySQLDAO();
        }

        LOG.info("[RETURNING FROM IDAO lookupDaoType(DBProviders provider) -> " + dao + " ]");
        return dao;
    }
}