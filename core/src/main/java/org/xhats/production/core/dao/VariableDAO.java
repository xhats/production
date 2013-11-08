/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xhats.production.core.dao;

import java.util.List;
import org.xhats.production.core.entity.VariableType;

/**
 *
 * @author Гаврилов АН
 */
public interface VariableDAO extends DAO<VariableType> {

    public VariableType findByPositionInRule(int position, int rulesetId, int ruleId);

    public List<VariableType> findGoalByName(String name, int rulesetId);

    public List<VariableType> findByName(String name, int rulesetId);

    public List<VariableType> findByRule(int rulesetId, int ruleId);
}
