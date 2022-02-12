package com.jiantech.SearchQueryForSQL.Builder;

public class SelectorBuilder{
    SQLBuilder builder;
    String fields;

    public SelectorBuilder(SQLBuilder builder, String fields){
        this.builder = builder;
        this.fields = fields;
    }

    public SQLBuilder from(String tableName){
        this.builder.pipe.append("SELECT " + this.fields + " FROM " + tableName);
        return builder;
    }

}
