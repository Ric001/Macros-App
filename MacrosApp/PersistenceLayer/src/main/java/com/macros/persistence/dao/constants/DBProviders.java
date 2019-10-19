package com.macros.persistence.dao.constants;


public enum DBProviders {
    MYSQL;

    @Override
    public String toString() 
    {
        switch(this) {
            case MYSQL: return "MYSQL";
            default : return "UNSUPPORTED DATABASE PROVIDER";
        }
    }
}