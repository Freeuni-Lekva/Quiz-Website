package dao;

import models.Account;

import java.sql.Connection;

public class UsersDao {
    private Connection conn;


    public UsersDao(Connection conn) {
        this.conn = conn;
    }


    public void add(Account account) {

    }

    public String getPassword(String userName) {
        return null;
    }

    public Account getAccount(String userName) {
        return null;
    }
}
