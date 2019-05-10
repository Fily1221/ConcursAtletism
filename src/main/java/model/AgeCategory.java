package model;

public class AgeCategory{
    private int idAgeCategory;
    private int minAge;
    private int maxAge;

    public AgeCategory(int idAgeCategory, int minAge, int maxAge) {
        this.idAgeCategory = idAgeCategory;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    public int getIdAgeCategory() {
        return idAgeCategory;
    }

    public void setIdAgeCategory(int idAgeCategory) {
        this.idAgeCategory = idAgeCategory;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    @Override
    public String toString() {
        return "AgeCategory{" +
                "idAgeCategory=" + idAgeCategory +
                ", minAge=" + minAge +
                ", maxAge=" + maxAge +
                '}';
    }
}