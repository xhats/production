package org.xhats.production.core.entity;

public class VarType {

    private double _cf;
    private int _compare;
    private String _name;
    private int _number;
    private int _personId;
    private int _ruleId;
    private int _rulesetId;
    private double _value;

    public double getCf() {
        return _cf;
    }

    public void setCf(double cf) {
        _cf = cf;
    }

    public int getCompare() {
        return _compare;
    }

    public void setCompare(int compare) {
        _compare = compare;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public int getNumber() {
        return _number;
    }

    public void setNumber(int _number) {
        this._number = _number;
    }

    public int getPersonId() {
        return _personId;
    }

    public void setPersonId(int _personId) {
        this._personId = _personId;
    }

    public int getRuleId() {
        return _ruleId;
    }

    public void setRuleId(int ruleId) {
        _ruleId = ruleId;
    }

    public int getRulesetId() {
        return _rulesetId;
    }

    public void setRulesetId(int _rulesetId) {
        this._rulesetId = _rulesetId;
    }

    public double getValue() {
        return _value;
    }

    public void setValue(double value) {
        _value = value;
    }
}
