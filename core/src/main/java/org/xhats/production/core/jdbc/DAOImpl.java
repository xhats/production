/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xhats.production.core.jdbc;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.xhats.production.core.dao.DAO;

/**
 *
 * @author Гаврилов АН
 * @param <T>
 */
@Repository
public abstract class DAOImpl<T> extends JdbcDaoSupport implements DAO<T> {

    @Resource
    public JdbcTemplate jdbcTemplate;

    @Override
    protected void checkDaoConfig() {
        if (getJdbcTemplate() == null) {
            setJdbcTemplate(jdbcTemplate);
        }
    }

    @Override
    @Transactional
    public List<T> create(List<T> items) {
        final ArrayList<T> list = new ArrayList<>(items.size());
        for (T item : items) {
            list.add(create(item));
        }
        return list;
    }

    @Override
    @Transactional
    public List<T> remove(List<T> items) {
        final ArrayList<T> list = new ArrayList<>(items.size());
        for (T item : items) {
            list.add(remove(item));
        }
        return list;
    }

    @Override
    @Transactional
    public List<T> update(List<T> items) {
        final ArrayList<T> list = new ArrayList<>(items.size());
        for (T item : items) {
            list.add(update(item));
        }
        return list;
    }
}
