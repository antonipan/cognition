package ru.antonio.cognition.repositories;

public interface Observable {
    void registerObserver(Observer o);
    void removeObserver (Observer o);
    void notifyObserver (Observer o);
}
