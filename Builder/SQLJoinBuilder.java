package com.jiantech.SearchQueryForSQL.Builder;

import com.jiantech.SearchQueryForSQL.Builder.interfaces.BuildPipeable;
import com.jiantech.SearchQueryForSQL.Builder.interfaces.Joinable;
import com.jiantech.SearchQueryForSQL.Builder.interfaces.SQLJoinCondition;
import com.jiantech.SearchQueryForSQL.Builder.interfaces.SQLJoinOn;

public class SQLJoinBuilder implements BuildPipeable, SQLJoinCondition, Joinable, SQLJoinOn {
    SQLBuilder builder;
    String tableName;

    public SQLJoinBuilder(SQLBuilder builder){
        this.builder = builder;
    }

    public SQLJoinBuilder(SQLBuilder builder, String tableName){
        this.builder = builder;
        this.tableName = tableName;
    }

    @Override
    public SQLJoinOn innerJoin() {
        builder.pipe.append(" \nINNER JOIN " + tableName);
        return this;
    }

    @Override
    public SQLJoinOn leftOuterJoin() {
        builder.pipe.append(" \nLEFT OUTER JOIN " + tableName);
        return this;
    }

    @Override
    public SQLJoinOn join() {
        builder.pipe.append(" \nJOIN " + tableName);
        return this;
    }

    @Override
    public SQLJoinOn rightJoin() {
        builder.pipe.append(" \nRIGHT OUTER JOIN " + tableName);
        return this;
    }

    @Override
    public SQLJoinOn innerJoin(String tableName){
        if(this.tableName == null) this.tableName = tableName;
        builder.pipe.append(" \nINNER JOIN " + tableName);
        return this;
    }

    @Override
    public SQLJoinOn leftOuterJoin(String tableName){
        if(this.tableName == null) this.tableName = tableName;
        builder.pipe.append(" \nLEFT OUTER JOIN " + tableName);
        return this;
    }

    @Override
    public SQLJoinOn join(String tableName){
        if(this.tableName == null) this.tableName = tableName;
        builder.pipe.append(" \nJOIN " + tableName);
        return this;
    }

    @Override
    public SQLJoinOn rightJoin(String tableName){
        if(this.tableName == null) this.tableName = tableName;
        builder.pipe.append(" \nRIGHT OUTER JOIN " + tableName);
        return this;
    }

    @Override
    public SQLJoinCondition on(String fieldName){
        builder.pipe.append(" ON " + this.tableName + "." + fieldName);
        return this;
    }


    @Override
    public SQLJoinBuilder isEqualTo(String value){
        builder.pipe.append(" = " + value);
        return this;
    }

    @Override
    public SQLJoinBuilder as(String tableNameAs){
        builder.pipe.append(" as " + tableNameAs);
        this.tableName = tableNameAs;
        return this;
    }

    @Override
    public SQLJoinBuilder isGraterThan(String value) {
        builder.pipe.append(" < " + value);
        return this;
    }

    @Override
    public SQLJoinBuilder isLessThan(String value) {
        builder.pipe.append(" > " + value);
        return this;
    }


    @Override
    public SQLJoinBuilder isGraterThanAndEqual(String value) {
        builder.pipe.append(" <= " + value);
        return this;
    }

    @Override
    public SQLJoinBuilder isLessThanAndEqual(String value) {
        builder.pipe.append(" >= " + value);
        return this;
    }

    @Override
    public SQLJoinBuilder isLike(String value) {
        builder.pipe.append(" LIKE " + value);
        return this;
    }

    @Override
    public SQLBuilder nextPipe() {
        return builder;
    }
}
