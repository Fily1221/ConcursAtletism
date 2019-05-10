package repository;

import model.Entry;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EntryRepo {

    public void addEntry(Entry entry) {
        JdbcConnect.openConnection();
        try(Statement stmt= JdbcConnect.conn.createStatement()){
            PreparedStatement preStmt = JdbcConnect.conn.prepareStatement("insert into entry(idAge, idTrial, childAge,childName) values(?, ?, ?, ?)");

            preStmt.setInt(1, entry.getAgeCategory().getIdAgeCategory());
            preStmt.setInt(2, entry.getTrial().getIdTrial());
            preStmt.setInt(3, entry.getChildAge());
            preStmt.setString(4, entry.getChildName());
            preStmt.executeUpdate();
            //  SpectacolRepo sRepo = new SpectacolRepo();
            // sRepo.updateLocuri(nrLoc_dorit, spectacol_id);
        }catch(SQLException ex){
            System.err.println("EXCEPTION IN EntryRepo: ");
            System.err.println(ex.getSQLState());
            System.err.println(ex.getErrorCode());
            System.err.println(ex.getMessage());
        }
    }

    public String PrintEntry() {
        JdbcConnect.openConnection();
        String result = "\n-------------------------------------------------------\nRegistration:\n-------------------------------------------------------\n";
        try {
            PreparedStatement preStmt = JdbcConnect.conn.prepareStatement(
                    "SELECT * FROM Entry");
            ResultSet rs = preStmt.executeQuery();

            while(rs.next()){
                result += "Age Category Id: " + rs.getString("ageCategoryId") +
                        "\nTrial Id: " + rs.getString("trialId") +
                        "\nChild Age: " + rs.getString("childAge") +
                        "\nChild Name: " + rs.getString("childName") +"\n\n";
            }
            result += "\n-------------------------------------------------------\n";
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState());
            System.err.println(ex.getErrorCode());
            System.err.println(ex.getMessage());
        }
        return result;
    }
}
