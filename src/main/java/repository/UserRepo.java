package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepo {
    public boolean validate(String username, String password){
        JdbcConnect.openConnection();
        try {
            PreparedStatement preStmt = JdbcConnect.conn.prepareStatement("select * from user where username=? and password=?");
            preStmt.setString(1, username);
            preStmt.setString(2, password);
            ResultSet rs = preStmt.executeQuery();
            rs.next();

            if(rs.getString("username") != null) {
                System.out.println(rs.getString("username"));
                return true;
            }

        } catch (SQLException ex) {
            System.err.println(ex.getSQLState());
            System.err.println(ex.getErrorCode());
            System.err.println(ex.getMessage());
        }
        return false;
    }
}
