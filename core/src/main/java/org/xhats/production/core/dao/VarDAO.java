/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xhats.production.core.dao;

import java.util.List;
import org.xhats.production.core.entity.VarType;

/**
 *
 * @author Гаврилов АН
 */
public interface VarDAO extends DAO<VarType> {

    public int clear(int rulesetId, int personId, int number);

    public VarType create(int rulesetId, int ruleId, int personId, int number, String name, double value);

    public VarType findByName(String name, int rulesetId, int personId, int number);

    public List<VarType> findByRuleSet(int rulesetId, int personId, int number);
}
