package org.xhats.production.core.entity;

public class RuleType {

    private double _cf;
    private String _description;
    private int _ruleId;
    private String _rule;
    private int _ruleset;

    public double getCf() {
        return _cf;
    }

    public void setCf(double cf) {
        _cf = cf;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public int getRuleId() {
        return _ruleId;
    }

    public void setRuleId(int id) {
        _ruleId = id;
    }

    public String getRule() {
        return _rule;
    }

    public void setRule(String rule) {
        _rule = rule;
    }

    public int getRuleset() {
        return _ruleset;
    }

    public void setRuleset(int ruleset) {
        _ruleset = ruleset;
    }
}
