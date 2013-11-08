/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xhats.production.core.dao;

import org.xhats.production.core.entity.RulesetType;

/**
 *
 * @author Гаврилов АН
 */
public interface RulesetDAO extends DAO<RulesetType> {

    public RulesetType findById(int id);

    public RulesetType findByName(String name);
}
