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
import org.xhats.production.core.dao.RulesetDAO;
import org.xhats.production.core.entity.RulesetType;

@Repository
public class RulesetDAOImpl extends DAOImpl<RulesetType> implements RulesetDAO {

    private static final RowMapper<RulesetType> rowMapper = new BeanPropertyRowMapper(RulesetType.class);

    @Override
    @Transactional
    public void createTable() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @Transactional
    public RulesetType create(RulesetType item) {
        if (getJdbcTemplate().update("INSERT INTO ruleset SET goal=?, rulesetId=?, name=?",
                item.getGoal(), item.getRulesetId(), item.getName()) == 0) {
            item = findById(item.getRulesetId());
        }
        return item;
    }

    @Override
    public List<RulesetType> findAll() {
        return getJdbcTemplate().query("SELECT * FROM ruleset r ORDER BY r.rulesetId", rowMapper);
    }

    @Override
    public RulesetType findById(int id) {
        return getJdbcTemplate().queryForObject("SELECT * FROM ruleset WHERE rulesetId=?", rowMapper, id);
    }

    @Override
    public RulesetType findByName(String name) {
        return getJdbcTemplate().queryForObject("SELECT * FROM ruleset WHERE name=?", rowMapper, name);
    }

    @Override
    @Transactional
    public RulesetType remove(RulesetType item) {
        if (getJdbcTemplate().update("DELETE FROM ruleset WHERE rulesetId=?", item.getRulesetId()) == 1) {
            return null;
        } else {
            return item;
        }
    }

    @Override
    @Transactional
    public RulesetType update(RulesetType item) {
        if (getJdbcTemplate().update("UPDATE rule SET goal=?, name=? WHERE rulesetId=?",
                item.getGoal(), item.getName(), item.getRulesetId()) == 0) {
            item = findById(item.getRulesetId());
        }
        return item;
    }
}
