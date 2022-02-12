package com.jiantech.SearchQueryForSQL.Builder;

import com.jiantech.SearchQueryForSQL.Builder.interfaces.BuildPipeable;

public class OrderBuilder implements BuildPipeable {
    SQLBuilder builder;
    boolean orderApplied = false;
    String field;

    public OrderBuilder(SQLBuilder builder){
        this.builder = builder;
    }
    public OrderBuilder(SQLBuilder builder, String field){
        this.builder = builder;
        this.field = field;
    }


    public OrderBuilder field(String fieldName){
        orderApplied = true;
        builder.pipe.append(" \nORDER BY " + fieldName);
        return this;
    }

    public OrderBuilder ascending(){
        builder.pipe.append(" ASC ");
        return this;
    }

    public OrderBuilder descending(){
        builder.pipe.append(" DESC ");
        return this;
    }

    @Override
    public SQLBuilder nextPipe() {
        return this.builder;
    }
}
