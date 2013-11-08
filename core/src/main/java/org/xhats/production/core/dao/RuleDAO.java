/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xhats.production.core.dao;

import java.util.List;
import org.xhats.production.core.entity.RuleType;

/**
 *
 * @author Гаврилов АН
 */
public interface RuleDAO extends DAO<RuleType> {

    public RuleType findById(int id, int rulesetId);

    public List<RuleType> findByRuleSet(int rulesetId);

    public List<RuleType> findReason(int rulesetId, int personId, int number);
}
