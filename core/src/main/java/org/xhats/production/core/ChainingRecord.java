/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xhats.production.core;

import org.xhats.production.core.entity.RuleType;

/**
 *
 * @author Гаврилов АН
 */
public class ChainingRecord {

    private String _var;
    private RuleType _rule;
    private int _ifNumber;
    private int _thenNumber;
    private Object _value;

    public int getIfNumber() {
        return _ifNumber;
    }

    public void setIfNumber(int value) {
        _ifNumber = value;
    }

    public RuleType getRule() {
        return _rule;
    }

    public void setRule(RuleType value) {
        _rule = value;
    }

    public int getThenNumber() {
        return _thenNumber;
    }

    public void setThenNumber(int value) {
        _thenNumber = value;
    }

    public Object getValue() {
        return _value;
    }

    public void setValue(Object value) {
        _value = value;
    }

    public String getVar() {
        return _var;
    }

    public void setVar(String value) {
        _var = value;
    }
}
