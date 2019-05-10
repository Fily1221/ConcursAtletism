package model;

public interface HasId<T> {
    T getID();

    void setID(T id);
}