package com.macros.persistence.dao.constants;

public enum DBLinks {
    MYSQL;

    @Override
    public String toString() 
    {
        switch(this)
        {
           case MYSQL: return "jdbc:mysql://localhost:3306/";
           default: return "UNSUPORTED Data Base Provider";
        }
    }
}