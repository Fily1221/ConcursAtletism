package repository;

import model.AgeCategory;
import model.Trial;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AgeTrialRepo {

    public Map<AgeCategory, Trial> getAgeCategoryTrialMapping() {

        Map<AgeCategory, Trial> ageCategoryTrialsMap = new HashMap<>();

        JdbcConnect.openConnection();
        String sql = "SELECT at.idAgeTrial, ac.idAge,  ac.minAge, ac.maxAge, t.idTrial, t.duration FROM mpp_lab1.agetrial at\n" +
                " join agecategory ac on ac.idAge = at.idAgeCategory \n" +
                " join trial t on t.idTrial = at.idTrial";

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

        return ageCategoryTrialsMap;
    }
}
