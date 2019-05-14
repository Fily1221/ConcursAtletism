package services;

import model.AgeCategory;
import model.Trial;
import repository.*;

import java.util.List;
import java.util.Map;


public class TrialRepoServices implements  ITrialRepoServices{

    private IEntryRepo entry = new EntryRepo();
    private IAgeTrialRepo agetrial = new AgeTrialRepo();

    private IUserRepo userRepo = new UserRepo();
    @Override
    public Map<AgeCategory, Trial> getAgeCategoryTrialMapping() {
        return agetrial.getAgeCategoryTrialMapping();
    }

    public List<String> getEntries() {
        return agetrial.getEntries();
    }

    @Override
    public int getTrialCountByAgeAndName(int age, String name) {
        return  entry.getTrialCountByAgeAndName(age, name);
    }

    @Override
    public void addEntry(int age, int duration, String name) {
        entry.addEntry(age,duration, name);
    }

    @Override
    public boolean validate(String username, String password) {
        return userRepo.validate(username, password);
    }

    @Override
    public String searchEntry(int dur, String age) {
        return entry.searchEntry(dur, age);
    }


    @Override
    public String printEntry() {
        return entry.printEntry();
    }
}
