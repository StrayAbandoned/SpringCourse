package ru.lapshina.dao;

import java.util.List;

public interface Dao<T> {
    public  List<T> findAll();
    public  T findById(int id);
    public  void insertOrUpdate(T t);
    public void delete(int id);
}
