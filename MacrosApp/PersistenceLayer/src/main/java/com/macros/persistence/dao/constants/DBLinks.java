package com.macros.persistence.dao.constants;

public enum DBLinks {
    MYSQL_LINK;

    @Override
    public String toString() 
    {
        switch(this)
        {
           case MYSQL_LINK: return "jdbc:mysql://localhost:3306/";
           default: return "UNSUPPORTED Data Base Provider";
        }
    }
}