/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xhats.production.core.jdbc;

import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.xhats.production.core.dao.VarDAO;
import org.xhats.production.core.entity.VarType;

@Repository
public class VarDAOImpl extends DAOImpl<VarType> implements VarDAO {

    private static final RowMapper<VarType> rowMapper = new BeanPropertyRowMapper(VarType.class);

    @Override
    @Transactional
    public void createTable() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @Transactional
    public VarType create(VarType item) {
        if (getJdbcTemplate().update("INSERT INTO var SET rulesetId=?, ruleId=?, personId=?, number=?, name=?, value=?",
                item.getRulesetId(), item.getRuleId(), item.getPersonId(), item.getNumber(), item.getName(), item.getValue()) == 0) {
            item = findByName(item.getName(), item.getRulesetId(), item.getPersonId(), item.getNumber());
        }
        return item;
    }

    @Override
    @Transactional
    public VarType create(int rulesetId, int ruleId, int personId, int number, String name, double value) {
        if (getJdbcTemplate().update("INSERT INTO var SET rulesetId=?, ruleId=?, personId=?, number=?, name=?, value=?",
                rulesetId, ruleId, personId, number, name, value) == 0) {
            return findByName(name, rulesetId, personId, number);
        } else {
            return null;
        }
    }

    @Override
    public List<VarType> findAll() {
        return getJdbcTemplate().query("SELECT * FROM var ORDER BY rulesetId, ruleId, name", rowMapper);
    }

    @Override
    public VarType findByName(String name, int rulesetId, int personId, int number) {
        try {
            return getJdbcTemplate().queryForObject("SELECT * FROM var WHERE name=? AND rulesetId=? AND personId=? AND number=?", rowMapper,
                    name, rulesetId, personId, number);
        } catch (EmptyResultDataAccessException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            return null;
        }
    }

    @Override
    public List<VarType> findByRuleSet(int rulesetId, int personId, int number) {
        return getJdbcTemplate().query("SELECT * FROM var WHERE rulesetId=? AND personId=? AND number=? ORDER BY rulesetId, ruleId, name", rowMapper,
                rulesetId, personId, number);
    }

    @Override
    @Transactional
    public VarType remove(VarType item) {
        if (getJdbcTemplate().update("DELETE FROM var WHERE name=? AND number=? AND personId=? AND rulesetId=?",
                item.getName(), item.getNumber(), item.getPersonId(), item.getRulesetId()) == 1) {
            return null;
        } else {
            return item;
        }
    }

    @Override
    @Transactional
    public int clear(int rulesetId, int personId, int number) {
        return getJdbcTemplate().update("DELETE FROM var WHERE rulesetId=? AND personId=? AND number=?",
                rulesetId, personId, number);
    }

    @Override
    @Transactional
    public VarType update(VarType item) {
        if (getJdbcTemplate().update("UPDATE var SET value=?, ruleId=? WHERE number=?, personId=?, rulesetId=?, name=?",
                item.getValue(), item.getRuleId(), item.getNumber(), item.getPersonId(), item.getRulesetId(), item.getName()) == 0) {
            item = findByName(item.getName(), item.getRulesetId(), item.getPersonId(), item.getNumber());
        }
        return item;
    }
}
