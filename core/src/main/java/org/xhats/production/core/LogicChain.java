/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xhats.production.core;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.xhats.production.core.dao.VarDAO;
import org.xhats.production.core.dao.VariableDAO;
import org.xhats.production.core.entity.RulesetType;
import org.xhats.production.core.entity.VarType;
import org.xhats.production.core.entity.VariableType;

/**
 *
 * @author Гаврилов АН
 */
@Component
public class LogicChain {

    @Resource
    private VariableDAO variableDAOImpl;
    @Resource
    private VarDAO varDAOImpl;
    private final ArrayList<String> stack = new ArrayList<>();
    private static boolean modifed = false;

    public void addIntoStack(String key) {
        stack.add(key);
        modifed = true;

    }

    public void delFromStack(String key) {
        stack.remove(key);
        modifed = true;
    }

    public void reverseChain(RulesetType ruleset, final int personId, final int number) {
        modifed = false;
        final VarType r = varDAOImpl.findByName(ruleset.getGoal(), ruleset.getRulesetId(), personId, number);
        if (r == null) {
            final List<VariableType> variables = variableDAOImpl.findGoalByName(stack.get(stack.size() - 1), ruleset.getRulesetId());
            for (VariableType goal : variables) {
                if (goal.getPosition() == 0) {
                    boolean active = true;
                    List<VariableType> conjunctions = variableDAOImpl.findByRule(ruleset.getRulesetId(), goal.getRuleId());
                    for (VariableType variable : conjunctions) {
                        if (variable.getPosition() > 0) {
                            VarType var = varDAOImpl.findByName(variable.getName(), ruleset.getRulesetId(), personId, number);
                            if (var == null) {
                                addIntoStack(variable.getName());
                                reverseChain(ruleset, personId, number);
                                var = varDAOImpl.findByName(variable.getName(), ruleset.getRulesetId(), personId, number);
                            }
                            if (!compare(variable, var)) {
                                active = false;
                            }
                        }
                    }
                    if (active) {
                        VarType v = new VarType();
                        v.setCompare(goal.getCompare());
                        v.setName(goal.getName());
                        v.setRuleId(goal.getRuleId());
                        v.setRulesetId(goal.getRulesetId());
                        v.setValue(goal.getValue());
                        varDAOImpl.create(v);
                        delFromStack(goal.getName());
                    } else {
                        for (VariableType variable : variableDAOImpl.findByRule(ruleset.getRulesetId(), goal.getRuleId())) {
                            int i = stack.indexOf(variable.getName());
                            if (i > -1) {
                                delFromStack(variable.getName());
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean compare(VariableType variable, VarType var) {
        if (variable == null || var == null) {
            return false;
        } else if (variable.getCompare() == 0) {
            return var.getValue() == variable.getValue();
        } else if (variable.getCompare() > 0) {
            return var.getValue() > variable.getValue();
        } else if (variable.getCompare() < 0) {
            return var.getValue() <= variable.getValue();
        } else {
            return false;
        }
    }

    public boolean isModifed() {
        return modifed;
    }

    public boolean isEmptyStack() {
        return stack.isEmpty();
    }
}
