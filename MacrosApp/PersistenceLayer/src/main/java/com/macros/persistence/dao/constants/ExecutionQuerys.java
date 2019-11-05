package com.macros.persistence.dao.constants;

public enum ExecutionQuerys {
    CREATE, MODIFY, REMOVE, FIND_ALL, FIND_BY_ID, UNSUPPORTED_QUERY_OPTION;

    @Override
    public String toString() {
        switch (this) {
        case CREATE: return "INSERT INTO EXECUTIONS(EXECUTIONS_ID, EXECUTED_ORDER, EXECUTION_DATE) VALUES (?, ?, ?)";
        case MODIFY: return "UPDATE EXECUTIONS SET EXECUTED_ORDER = ?, SET EXECUTION_DATE = ?";
        case REMOVE: return "DELETE FROM EXECUTIONS WHERE EXECUTIONS_ID = ?";
        case FIND_ALL: return "SELECT * FROM EXECUTIONS";
        case FIND_BY_ID: return "SELECT * FROM EXECUTIONS WHERE EXECUTIONS_ID = ?";
        default: return UNSUPPORTED_QUERY_OPTION.name();
        }
    }
}