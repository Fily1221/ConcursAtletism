package main;

import controller.Gui;
import model.AgeCategory;
import model.Entry;
import model.Trial;
import repository.AgeTrialRepo;
import repository.EntryRepo;
import repository.UserRepo;

import java.util.List;
import java.util.Map;

public class GuiApp {
    private static Gui gui;
    public static void main(String[] args) {

        UserRepo userRepo = new UserRepo();
        boolean isValid = userRepo.validate("admin","1234");

        if(isValid){

            AgeTrialRepo ageTrialRepo = new AgeTrialRepo();
            Map<AgeCategory, Trial> ageCategoryTrialMap = ageTrialRepo.getAgeCategoryTrialMapping();

            for(Map.Entry<AgeCategory, Trial> entry : ageCategoryTrialMap.entrySet()){
                System.out.println(entry.getKey() + "/" + entry.getValue());
            }
                    /*Entry entry = new Entry();
            entry.setChildAge(8);
            entry.setChildName("Vasile");
            entry.setTrial(new Trial(2, 50));
            entry.setAgeCategory(new AgeCategory(2, 6,8));

            EntryRepo entryRepo = new EntryRepo();
            entryRepo.addEntry(entry);
            System.out.println(entryRepo.printEntry());
            System.out.println(entryRepo.searchEntry());*/

            //List<String> ageTrials = ageTrialRepo.getListOfTrialsByAge();
            gui = new Gui();
            //gui.setEntries(ageTrials);
            gui.startApp(args);

        }
    }
}
