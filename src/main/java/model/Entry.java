package model;


public class Entry {
    private AgeCategory ageCategory;
    private Trial trial;
    private Integer childAge;
    private String childName;

    public Entry() {
    }

    public Entry( AgeCategory ageCategory, Trial trial, Integer childAge, String childName) {
        this.ageCategory = ageCategory;
        this.trial = trial;
        this.childAge = childAge;
        this.childName = childName;
    }

    public AgeCategory getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(AgeCategory ageCategory) {
        this.ageCategory = ageCategory;
    }

    public Trial getTrial() {
        return trial;
    }

    public void setTrial(Trial trial) {
        this.trial = trial;
    }

    public Integer getChildAge() {
        return childAge;
    }

    public void setChildAge(Integer childAge) {
        this.childAge = childAge;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    @Override
    public String toString() {
        return "Entry{" +
                ", ageCategoryID=" + ageCategory.getMinAge() + " - " + ageCategory.getMaxAge() +
                ", trialID=" + trial.getDuration() +
                ", childAge=" + childAge +
                ", childName='" + childName + '\'' +
                '}';
    }
}