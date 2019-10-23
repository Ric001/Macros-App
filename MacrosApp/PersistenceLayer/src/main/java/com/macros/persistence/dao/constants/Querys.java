package com.macros.persistence.dao.constants;

public enum Querys {
    FIND_ALL, FIND_BY_ID, 
    CREATE, MODIFY, REMOVE,
    FIND_EXECUTED_ORDER_BY_ID,LIST_EXECUTED_ORDERS;

    @Override
    public String toString() 
    {
       switch(this) {
           case FIND_ALL: return "SELECT * FROM Orders";
           case FIND_BY_ID: return "SELECT * FROM ORDERS WHERE ORDERS_ID = ?";
           case CREATE: return "INSERT INTO Orders(NAME, CONTENT, REQUESTED_DATETIME, PARSED_DATE) VALUES(? ,?, ?, ?)";
           case MODIFY: return "UPDATE Orders SET `NAME` = ? ,SET `CONTENT` = ? WHERE ORDERS_ID = ?";
           case REMOVE: return "DELETE FROM Orders WHERE ORDERS_ID = ?";
           case LIST_EXECUTED_ORDERS: return "SELECT * FROM EXECUTIONS";
           case FIND_EXECUTED_ORDER_BY_ID: return "SELECT * FROM EXECUTIONS WHERE EXECUTIONS_ID = ?";
           default: return "TRYING TO USE AN UNSUPPORTED QUERY OPTION";
       }
    }
    
}