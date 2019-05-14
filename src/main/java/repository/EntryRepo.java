package repository;

import model.Entry;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EntryRepo implements IEntryRepo {

    public void addEntry(Entry entry) {
        JdbcConnect.openConnection();
        try (Statement stmt = JdbcConnect.conn.createStatement()) {
            PreparedStatement preStmt = JdbcConnect.conn.prepareStatement("insert into entry(idAge, idTrial, childAge,childName) values(?, ?, ?, ?)");

            preStmt.setInt(1, entry.getAgeCategory().getIdAgeCategory());
            preStmt.setInt(2, entry.getTrial().getIdTrial());
            preStmt.setInt(3, entry.getChildAge());
            preStmt.setString(4, entry.getChildName());
            preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("EXCEPTION IN EntryRepo: ");
            System.err.println(ex.getSQLState());
            System.err.println(ex.getErrorCode());
            System.err.println(ex.getMessage());
        }
    }

    public void addEntry(int age, int duration, String name ){
        int age1=age;
        int age2=age;

        /*String sql = "insert into entry(idAge, idTrial, childAge,childName) \n" +
                "select (select idAge from agecategory where minAge >= 6 and maxAge <= 8) a,\n" +
                " (select idTrial from trial where duration = 50) b,\n" +
                " 7,\n" +
                " \"Laura\" ";*/
        String sql="insert into entry(idAge, idTrial, childAge,childName) \n" +
                "select (select idAge from agecategory where minAge <= ? and maxAge >= ?) a,\n" +
                " (select idTrial from trial where duration = 50) b,\n" +
                " ?,\n" +
                " \"?\" ";

       /* JdbcConnect.conn.prepareStatement("insert into entry(idAge, idTrial, childAge,childName) \n\" +\n" +
                "                \"select (select idAge from agecategory where minAge <= ? and maxAge >= ?) a,\n\" +\n" +
                "                \" (select idTrial from trial where duration = ?) b,\\n\" +\n" +
                "                \" ?,\n\" +\n" +
                "                \" \"?\" ");*/
        JdbcConnect.openConnection();
        try (Statement stmt = JdbcConnect.conn.createStatement()) {
            PreparedStatement preStmt = JdbcConnect.conn.prepareStatement("insert into entry(idAge, idTrial, childAge,childName) \n" +
                    "select (select idAge from agecategory where minAge <= ? and maxAge >= ?) a,\n" +
                            " (select idTrial from trial where duration = ?) b,\n" +
                            " ?,\n" +
                            " ? ");


            preStmt.setInt(1, age1);
            preStmt.setInt(2, age2);
            preStmt.setInt(3, duration);
            preStmt.setInt(4, age);
            preStmt.setString(5, name);

            preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("EXCEPTION IN EntryRepo: ");
            System.err.println(ex.getSQLState());
            System.err.println(ex.getErrorCode());
            System.err.println(ex.getMessage());
        }
    }

    public String searchEntry(int dur, String age) {
        String result = "\n-------------------------------------------------------\nChild from category of age and trial:\n-------------------------------------------------------\n";
        JdbcConnect.openConnection();
        String[] tokens =age.split("-");
        int min=Integer.valueOf(tokens[0]);
        int max=Integer.valueOf(tokens[1]);
        //int min=Character.getNumericValue(age.charAt(0));
        //int max=Character.getNumericValue(age.charAt(2)+age.charAt(3));

        try (Statement stmt = JdbcConnect.conn.createStatement()) {
            PreparedStatement preStmt = JdbcConnect.conn.prepareStatement(
                    "select e1.childName ,e1.childAge ,(select count(*) from Entry e " +
                            "where e.childAge=e1.childAge and e.childName=e1.childName)  as \"Numar Probe\"\n" +
                            "from entry e1\n" +
                            "where e1.idTrial = (select idTrial from trial where duration=?) and e1.idAge= (select idAge from agecategory where minAge=? and maxAge=?) \n" +
                            "group by childName,childAge;");

            preStmt.setInt(1, dur);
           preStmt.setInt(2, min);

           preStmt.setInt(3, max);

            ResultSet rs = preStmt.executeQuery();


            while (rs.next()) {
                result += "Child Name: " + rs.getString(1) +
                        "\nChild Age: " + rs.getString(2) +
                        "\nNumar Probe " + rs.getString(3) + "\n";
                result += "\n-------------------------------------------------------\n";
            }


            // preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("EXCEPTION IN EntryRepo: ");
            System.err.println(ex.getSQLState());
            System.err.println(ex.getErrorCode());
            System.err.println(ex.getMessage());
        }
        return result;
    }
    public List<String> getSearchedEntries()
    {
        List<String> trialsByAge = new ArrayList<>();
        JdbcConnect.openConnection();
        return trialsByAge;
    }

    public String printEntry() {
        JdbcConnect.openConnection();
        String result = "\n-------------------------------------------------------\nRegistration:\n-------------------------------------------------------\n";
        try {
            PreparedStatement preStmt = JdbcConnect.conn.prepareStatement(
                    "SELECT * FROM Entry");
            ResultSet rs = preStmt.executeQuery();

            while (rs.next()) {
                result += "Age Category Id: " + rs.getString("idAge") +
                        "\nTrial Id: " + rs.getString("idTrial") +
                        "\nChild Age: " + rs.getString("childAge") +
                        "\nChild Name: " + rs.getString("childName") + "\n\n";
            }
            result += "\n-------------------------------------------------------\n";
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState());
            System.err.println(ex.getErrorCode());
            System.err.println(ex.getMessage());
        }
        return result;
    }

    public int getTrialCountByAgeAndName(int age, String name) {
        JdbcConnect.openConnection();
        int count = 0;
        try {
            PreparedStatement preStmt = JdbcConnect.conn.prepareStatement(
                    "SELECT count(*) cnt FROM mpp_lab1.entry where childAge = ? and childName = ?;");

            preStmt.setInt(1, age);
            preStmt.setString(2, name);
            ResultSet rs = preStmt.executeQuery();

            while (rs.next()) {
                count = rs.getInt("cnt");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getSQLState());
            System.err.println(ex.getErrorCode());
            System.err.println(ex.getMessage());
        }
        return count;
    }
}
