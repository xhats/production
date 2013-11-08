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
import org.xhats.production.core.dao.RuleDAO;
import org.xhats.production.core.entity.RuleType;

@Repository
public class RuleDAOImpl extends DAOImpl<RuleType> implements RuleDAO {

    private static final RowMapper<RuleType> rowMapper = new BeanPropertyRowMapper(RuleType.class);

    @Override
    @Transactional
    public void createTable() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @Transactional
    public RuleType create(RuleType item) {
        if (getJdbcTemplate().update("INSERT INTO rule SET cf=?, description=?, ruleId=?, rule=?, rulesetId=?",
                item.getCf(), item.getDescription(), item.getRuleId(), item.getRule(), item.getRuleset()) == 0) {
            item = findById(item.getRuleId(), item.getRuleset());
        }
        return item;
    }

    @Override
    public List<RuleType> findAll() {
        return getJdbcTemplate().query("SELECT * FROM rule r ORDER BY r.ruleId", rowMapper);
    }

    @Override
    public RuleType findById(int id, int rulesetId) {
        return getJdbcTemplate().queryForObject("SELECT * FROM rule r WHERE r.ruleId=? AND r.rulesetId=?",
                rowMapper, id, rulesetId);
    }

    @Override
    public List<RuleType> findByRuleSet(int rulesetId) {
        return getJdbcTemplate().query("SELECT * FROM rule r WHERE r.rulesetId=? ORDER BY r.ruleId", rowMapper, rulesetId);
    }

    @Override
    public List<RuleType> findReason(int rulesetId, int personId, int number) {
        return getJdbcTemplate().query("SELECT * FROM rule r "
                + "JOIN var v ON v.rulesetId=r.rulesetId AND v.ruleId = r.ruleId AND v.personId = ? AND v.number = ? "
                + "WHERE r.rulesetId=? AND v.ruleId > 0 "
                + "ORDER BY r.ruleId", rowMapper, personId, number, rulesetId);
    }

    @Override
    @Transactional
    public RuleType remove(RuleType item) {
        if (getJdbcTemplate().update("DELETE FROM rule WHERE ruleId=? AND rulesetId=?", item.getRuleId(), item.getRuleset()) == 1) {
            return null;
        } else {
            return item;
        }
    }

    @Override
    @Transactional
    public RuleType update(RuleType item) {
        if (getJdbcTemplate().update("UPDATE rule SET cf=?, description=?, rule=? WHERE ruleId=? AND rulesetId=?",
                item.getCf(), item.getDescription(), item.getRule(), item.getRuleId(), item.getRuleset()) == 0) {
            item = findById(item.getRuleId(), item.getRuleset());
        }
        return item;
    }
}
