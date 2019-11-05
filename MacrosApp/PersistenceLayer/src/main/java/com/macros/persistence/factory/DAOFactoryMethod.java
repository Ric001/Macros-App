package com.macros.persistence.factory;

import java.util.Optional;

import com.macros.persistence.dao.IDAO;
import com.macros.persistence.dao.constants.DBProviders;

@FunctionalInterface
public interface DAOFactoryMethod {
    Optional<IDAO> daoByProvider(DBProviders provider);
}