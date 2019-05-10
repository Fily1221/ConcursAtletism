package model;

public class Trial {
    private int idTrial;
    private int duration;

    public Trial(int idTrial, int duration) {
        this.idTrial = idTrial;
        this.duration = duration;
    }

    public int getIdTrial() {
        return idTrial;
    }

    public void setIdTrial(int idTrial) {
        this.idTrial = idTrial;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Trial{" +
                "idTrial=" + idTrial +
                ", duration=" + duration +
                '}';
    }
}