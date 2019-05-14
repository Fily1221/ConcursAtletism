package repository;

import model.Entry;

public interface IEntryRepo {
    void addEntry(Entry entry);

    String printEntry();
    int getTrialCountByAgeAndName(int age, String name);
    void addEntry(int age, int duration, String name);
     String searchEntry(int dur, String age);
}
