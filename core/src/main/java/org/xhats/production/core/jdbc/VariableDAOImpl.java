/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xhats.production.core.jdbc;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.xhats.production.core.dao.VariableDAO;
import org.xhats.production.core.entity.VariableType;

@Repository
public class VariableDAOImpl extends DAOImpl<VariableType> implements VariableDAO {

    private static final RowMapper<VariableType> rowMapper = new BeanPropertyRowMapper(VariableType.class);

    @Override
    @Transactional
    public void createTable() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @Transactional
    public VariableType create(VariableType item) {
        if (getJdbcTemplate().update("INSERT INTO variable SET cf=?, compare=?, name=?, position=?, ruleId=?, rulesetId=?, value=?",
                item.getCf(), item.getCompare(), item.getName(), item.getPosition(), item.getRuleId(), item.getRulesetId(), item.getValue()) == 0) {
            item = findByPositionInRule(item.getPosition(), item.getRulesetId(), item.getRuleId());
        }
        return item;
    }

    @Override
    public List<VariableType> findAll() {
        return getJdbcTemplate().query("SELECT * FROM variable ORDER BY name", rowMapper);
    }

    @Override
    public List<VariableType> findGoalByName(String name, int rulesetId) {
        return getJdbcTemplate().query("SELECT * FROM variable WHERE name=? AND rulesetId=? AND position=0", rowMapper,
                name, rulesetId);
    }

    @Override
    public VariableType findByPositionInRule(int position, int rulesetId, int ruleId) {
        return getJdbcTemplate().queryForObject("SELECT * FROM variable WHERE position=? AND rulesetId=? AND ruleId=?", rowMapper,
                position, rulesetId, ruleId);
    }

    @Override
    public List<VariableType> findByName(String name, int rulesetId) {
        return getJdbcTemplate().query("SELECT * FROM variable WHERE name=? AND rulesetId=? ORDER BY name", rowMapper,
                name, rulesetId);
    }

    @Override
    public List<VariableType> findByRule(int rulesetId, int ruleId) {
        return getJdbcTemplate().query("SELECT * FROM variable WHERE rulesetId=? AND ruleId=? ORDER BY name", rowMapper,
                rulesetId, ruleId);
    }

    @Override
    @Transactional
    public VariableType remove(VariableType item) {
        if (getJdbcTemplate().update("DELETE FROM variable WHERE position=?  AND rulesetId=? AND ruleId=?",
                item.getPosition(), item.getRulesetId(), item.getRuleId()) == 1) {
            return null;
        } else {
            return item;
        }
    }

    @Override
    @Transactional
    public VariableType update(VariableType item) {
        if (getJdbcTemplate().update("UPDATE variable SET cf=?, compare=?, name=?, value=? WHERE position=?, rulesetId=?, ruleId=?",
                item.getCf(), item.getCompare(), item.getName(), item.getValue(), item.getPosition(), item.getRulesetId(), item.getRuleId()) == 0) {
            item = findByPositionInRule(item.getPosition(), item.getRulesetId(), item.getRuleId());
        }
        return item;
    }
}
