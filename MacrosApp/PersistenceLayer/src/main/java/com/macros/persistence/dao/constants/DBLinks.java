package com.macros.persistence.dao.constants;

public enum DBLinks {
    MYSQL_LINK, ORACLE_LINK;

    @Override
    public String toString() 
    {
        switch(this)
        {
           case MYSQL_LINK: return "jdbc:mysql://localhost:3306/";
           case ORACLE_LINK: return "";
           default: return "UNSUPPORTED Data Base Provider";
        }
    }
}