package com.macros.persistence.factory;

import java.util.Objects;
import java.util.logging.Logger;

import com.macros.persistence.dao.IDAO;
import com.macros.persistence.dao.MySQLDAO;
import com.macros.persistence.dao.constants.DBProviders;

public class MySQLDaoFactory implements DAOFactoryMethod {

    private Logger LOG = Logger.getLogger(MySQLDAO.class.getName());

    @Override
    public IDAO dao(DBProviders provider) {
        LOG.info("[ENTERING IDAO dao(DBProviders provider)]");
        if (Objects.isNull(provider))
            return null;
        
        IDAO dao = performComparison(provider);
        LOG.info("[RETURNING IDAO dao(DBProviders provider) " + dao);
        return dao;
    }

    private IDAO performComparison(DBProviders provider) {
        switch(provider) {
            case MYSQL: return new MySQLDAO();
            default: return null;
        }
    }
    
}