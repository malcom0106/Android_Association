package com.example.association.Entities;

public class ParametreOkHttp {
    private String Key;
    private String Value;

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public ParametreOkHttp() {
    }

    public ParametreOkHttp(String key, String value) {
        Key = key;
        Value = value;
    }
}
