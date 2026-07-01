package dao;

import java.sql.Connection;

import util.DbConnection;

public abstract class BaseDao {

    protected Connection conn;

    public BaseDao() {
        conn = DbConnection.getConnection();
    }

}
