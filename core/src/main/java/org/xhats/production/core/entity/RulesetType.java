package org.xhats.production.core.entity;

public class RulesetType {

    private String _goal;
    private int _rulesetId;
    private String _name;

    public String getGoal() {
        return _goal;
    }

    public void setGoal(String goal) {
        _goal = goal;
    }

    public int getRulesetId() {
        return _rulesetId;
    }

    public void setRulesetId(int id) {
        _rulesetId = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }
}
