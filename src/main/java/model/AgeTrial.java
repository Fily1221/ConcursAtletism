package model;

public class AgeTrial {
    int ageCategoryId;
    int trialId;

    public AgeTrial(int ageCategoryId, int trialId) {
        this.ageCategoryId = ageCategoryId;
        this.trialId = trialId;
    }

    public int getAgeCategoryId() {
        return ageCategoryId;
    }

    public void setAgeCategoryId(int ageCategoryId) {
        this.ageCategoryId = ageCategoryId;
    }

    public int getTrialId() {
        return trialId;
    }

    public void setTrialId(int trialId) {
        this.trialId = trialId;
    }
}
