package com.macros.persistence.dao.constants;

public enum Querys {
    FIND_ALL, FIND_BY_ID, 
    CREATE, MODIFY, REMOVE,LIST_EXECUTED_ORDERS;

   public String toString() 
   {
       switch(this) {
           case FIND_ALL: return "SELECT * FROM Orders";
           case FIND_BY_ID: return "SELECT FROM Orders WHERE ID = ?";
           case CREATE: return "INSERT INTO Orders(NAME, CONTENT) VALUES(? ,?)";
           case MODIFY: return "UPDATE Orders SET NAME = ?, SET CONTENT = ? WHERE Orders.ID = ?";
           case REMOVE: return "DELETE FROM Orders WHERE ID = ?";
           case LIST_EXECUTED_ORDERS: return "SELECT * FROM Orders INNER JOIN EXECUTIONS WHRE Orders.OrdersID = EXECUTIONS.EXECUTED_ORDER";
           default: return "TRYING TO USE AN UNSUPPORTED QUERY OPTION";
       }
   }
    
}