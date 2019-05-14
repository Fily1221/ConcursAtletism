package repository;

import model.AgeCategory;
import model.Trial;

import java.util.List;
import java.util.Map;

public interface IAgeTrialRepo {
    public Map<AgeCategory, Trial> getAgeCategoryTrialMapping();
    public List<String> getEntries();

}
