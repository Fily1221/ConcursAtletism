package repository;

import model.AgeCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AgeCategoryRepo implements IAgeCategoryRepo {

    public AgeCategoryRepo(){}

    @Override
    public String printAge() {

        JdbcConnect.openConnection();
        String result = "\n------------------------\nToate categoriile de varsta\n------------------------\n";

        try {
            PreparedStatement preStmt = JdbcConnect.conn.prepareStatement("SELECT  minAge,maxAge from AgeCategory");

            for(ResultSet rs = preStmt.executeQuery(); rs.next(); result = result + "Varsta Minima: " + rs.getString(1) + "\nVarsta Maxima: " + rs.getString(2)){
            }

            result = result + "\n------------------------\n";
        } catch (SQLException var4) {
            System.err.println(var4.getSQLState());
            System.err.println(var4.getErrorCode());
            System.err.println(var4.getMessage());
        }

        return result;
    }

    @Override
    public String printTrilal() {


        JdbcConnect.openConnection();
        String result = "\n------------------------\nToate Probele\n------------------------\n";

        try {
            PreparedStatement preStmt = JdbcConnect.conn.prepareStatement("select name , duration from Trial");

            for(ResultSet rs = preStmt.executeQuery(); rs.next(); result = result + "Nume Proba: " + rs.getString(1) + "\n Lungime: "+ rs.getString(2)) {
            }

            result = result + "\n------------------------\n";
        } catch (SQLException var4) {
            System.err.println(var4.getSQLState());
            System.err.println(var4.getErrorCode());
            System.err.println(var4.getMessage());
        }

        return result;
    }


    @Override
    public String cautare(String var1) {
        return null;
    }
}
