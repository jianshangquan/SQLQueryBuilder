package com.jiantech.SearchQueryForSQL;

public class SQLString {
    String value;
    public SQLString(String value){
        this.value = value;
    }

    public SQLString as(String as){
        value = value + " as \'" + as + '\'';
        return this;
    }

    public String compile(){
        return toString();
    }

    @Override
    public String toString() {
        return this.value;
    }
}
