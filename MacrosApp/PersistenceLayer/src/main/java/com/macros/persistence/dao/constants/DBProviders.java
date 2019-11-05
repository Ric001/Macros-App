package com.macros.persistence.dao.constants;


public enum DBProviders {
    MYSQL, UNSUPPORTED_DB_PROVIDER;

    @Override
    public String toString() 
    {
        switch(this) {
            case MYSQL: return MYSQL.name();
            default : return UNSUPPORTED_DB_PROVIDER.name();
        }
    }
}