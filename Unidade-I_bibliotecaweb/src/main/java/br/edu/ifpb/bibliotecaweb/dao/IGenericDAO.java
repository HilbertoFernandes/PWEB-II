package br.edu.ifpb.bibliotecaweb.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDAO<T, PK extends Serializable> {
    T insert(T t);
    T find(PK id);
    List<T> findAll();
    T update(T t);
    void delete(T t);
    void beginTransaction();
    void commit();
    void rollback();
    void refresh(T t);
}
