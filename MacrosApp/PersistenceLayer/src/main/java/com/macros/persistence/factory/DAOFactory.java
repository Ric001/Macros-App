package com.macros.persistence.factory;

import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

import com.macros.persistence.dao.IDAO;
import com.macros.persistence.dao.MySQLDAO;
import com.macros.persistence.dao.constants.DBProviders;

public class DAOFactory implements DAOFactoryMethod {

    private Logger LOG = Logger.getLogger(MySQLDAO.class.getName());

    @Override
    public Optional<IDAO> daoByProvider(DBProviders provider) {
        LOG.info("[ENTERING IDAO dao(DBProviders provider)]");
        if (Objects.isNull(provider))
            return null;

        Optional<IDAO> dao = lookupDaoType(provider);
        LOG.info("[RETURNING IDAO dao(DBProviders provider) " + dao);
        return dao;
    }

    private Optional<IDAO> lookupDaoType(DBProviders provider) {
        LOG.info("[ENTERING IDAO lookupDaoType(DBProviders provider)]");
        Optional<IDAO> dao = null;

        switch (provider) {
        case MYSQL:
            dao = Optional.of(new MySQLDAO(provider));
            break;
        case UNSUPPORTED_DB_PROVIDER:
            dao = Optional.empty();
            break;
        }

        LOG.info("[RETURNING FROM IDAO lookupDaoType(DBProviders provider) -> " + dao + " ]");
        return dao;
    }
}