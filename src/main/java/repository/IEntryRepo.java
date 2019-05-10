package repository;

public interface IEntryRepo {
    void addEntry(int id, int ageCategoryId, int trialId, int childAge, String childName);
    String PrintEntry();
}
