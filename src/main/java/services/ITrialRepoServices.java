package services;

import model.AgeCategory;
import model.Trial;

import java.util.List;
import java.util.Map;

public interface ITrialRepoServices {
    Map<AgeCategory, Trial> getAgeCategoryTrialMapping();
    boolean validate(String utilizator, String parola);
    public String searchEntry(int dur, String age);
    String printEntry();
    List<String> getEntries();
    int getTrialCountByAgeAndName(int age, String name);
    void addEntry(int age, int duration, String name);
}
