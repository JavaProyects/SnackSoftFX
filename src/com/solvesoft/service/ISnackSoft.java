package com.solvesoft.service;

import java.util.List;

/**
 *
 * @author theo
 */
public interface ISnackSoft<T> {

    public boolean Create(T t) throws Exception;

    public boolean Update(T t) throws Exception;

    public boolean Delete(T t) throws Exception;

    public List<T> findAll(String t) throws Exception;

    public T findBy(String q) throws Exception;

    public List<T> findByQuery(String q) throws Exception;

    public List<T> findByNativeQuery(String nq) throws Exception;

}
