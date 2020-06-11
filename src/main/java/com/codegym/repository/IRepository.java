package com.codegym.repository;

import java.util.List;

public interface IRepository<T> {
    List<T> findAll();

    T getById(Long id);

    void save(T model);

    void update(T model);

    void remove(Long id);
}
