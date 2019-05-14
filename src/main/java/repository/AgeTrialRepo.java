package repository;

import model.AgeCategory;
import model.Trial;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AgeTrialRepo implements IAgeTrialRepo{

    public Map<AgeCategory, Trial> getAgeCategoryTrialMapping() {

        Map<AgeCategory, Trial> ageCategoryTrialsMap = new LinkedHashMap<>();

        JdbcConnect.openConnection();
       // String sql = "SELECT at.idAgeTrial, ac.idAge,  ac.minAge, ac.maxAge, t.idTrial, t.duration FROM mpp_lab1.agetrial at\n" +
         //      " join agecategory ac on ac.idAge = at.idAgeCategory \n" +
           //     " join trial t on t.idTrial = at.idTrial";
        String sql="SELECT at.idAgeTrial, ac.idAge,  ac.minAge, ac.maxAge, t.idTrial, t.duration FROM mpp_lab1.agetrial at\n" +
                "                 join agecategory ac on ac.idAge = at.idAgeCategory  \n" +
                "                 join trial t on t.idTrial = at.idTrial\n" +
                "                 order by minAge,maxAge;";

        try {
            PreparedStatement preStmt = JdbcConnect.conn.prepareStatement(sql);
            ResultSet rs = preStmt.executeQuery();

            while (rs.next()) {

                int idAge = rs.getInt("idAge");
                int minAge = rs.getInt("minAge");
                int maxAge = rs.getInt("maxAge");
                int idTrial = rs.getInt("idTrial");
                int duration = rs.getInt("duration");

                AgeCategory ageCategory = new AgeCategory(idAge, minAge, maxAge);
                Trial trial = new Trial(idTrial, duration);

                ageCategoryTrialsMap.put(ageCategory, trial);

            }


        } catch (SQLException ex) {
            System.err.println(ex.getSQLState());
            System.err.println(ex.getErrorCode());
            System.err.println(ex.getMessage());
        }
        //Map<AgeCategory, Trial> treeMap = new TreeMap<AgeCategory, Trial>(ageCategoryTrialsMap);
        //Map<AgeCategory, Trial> treeMap = new TreeMap<>(ageCategoryTrialsMap);
        //treeMap.putAll(ageCategoryTrialsMap);

        //printMap(treeMap);
        return ageCategoryTrialsMap;
    }


    public List<String> getEntries(){
        List<String> trialsByAge = new ArrayList<>();
        JdbcConnect.openConnection();
        String sql = "SELECT CONCAT(t.duration, \" metri\") distanta, CONCAT(ac.minAge,\"-\",ac.maxAge, \" ani\") gr FROM mpp_lab1.agetrial at\n" +
                "join agecategory ac on ac.idAge = at.idAgeCategory \n" +
                "join trial t on t.idTrial = at.idTrial ";

        try {
            PreparedStatement preStmt = JdbcConnect.conn.prepareStatement(sql);
            ResultSet rs = preStmt.executeQuery();

            while (rs.next()) {

                String distanta = rs.getString("distanta");
                String gr = rs.getString("gr");

                trialsByAge.add(distanta + " , " +gr);

            }


        } catch (SQLException ex) {
            System.err.println(ex.getSQLState());
            System.err.println(ex.getErrorCode());
            System.err.println(ex.getMessage());
        }

        return trialsByAge;
    }

}
