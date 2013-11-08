/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xhats.production.core.dao;

import java.util.List;

/**
 *
 * @author Гаврилов АН
 * @param <T extends Object>
 */
public interface DAO<T extends Object> {

    public void createTable();

    public T create(T item);

    public List<T> create(List<T> items);

    public List<T> findAll();

    public T remove(T item);

    public List<T> remove(List<T> items);

    public T update(T item);

    public List<T> update(List<T> items);
}
