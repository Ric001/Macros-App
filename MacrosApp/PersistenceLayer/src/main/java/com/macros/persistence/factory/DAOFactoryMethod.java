package com.macros.persistence.factory;

import com.macros.persistence.dao.IDAO;
import com.macros.persistence.dao.constants.DBProviders;

@FunctionalInterface
public interface DAOFactoryMethod {
    IDAO dao(DBProviders provider);
}