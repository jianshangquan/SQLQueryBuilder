package com.jiantech.SearchQueryForSQL.Builder.Model;

public class SQLFunctionModel {
    private String compiled, as;

    public SQLFunctionModel(String compiled){
        this.compiled = compiled;
    }

    public SQLFunctionModel(String compiled, String as){
        this.compiled = compiled;
        this.as = as;
    }

    public String getCompiled(){
        return this.compiled;
    }

    public String as(String as){
        return compiled + " as \'" + as + "\'";
    }
}
