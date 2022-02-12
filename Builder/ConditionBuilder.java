package com.jiantech.SearchQueryForSQL.Builder;

import com.jiantech.SearchQueryForSQL.Builder.interfaces.BuildPipeable;
import com.jiantech.SearchQueryForSQL.Builder.interfaces.SQLWhereConditions;

public class ConditionBuilder implements BuildPipeable, SQLWhereConditions {
    SQLBuilder sqlBuilder;
    boolean conditionApplied = false;
    int numberOfScrope = 0;

    public ConditionBuilder(SQLBuilder builder){
        this.sqlBuilder = builder;
        this.sqlBuilder.pipe.append(" WHERE");
    }

    public ConditionBuilder useConditionBuilder(SQLConditionClauseBuilder builder) throws Exception{
        if(!builder.isCompiled()) {
            throw new Exception("The query is not compiled to sql");
        }
        return (ConditionBuilder) field(builder.getQuery());
    }

    public SQLWhereConditions field(String fieldName){
        sqlBuilder.pipe.append(" " + fieldName);
        return this;
    }



    public ConditionBuilder and(){
        sqlBuilder.pipe.append(" AND");
        return this;
    }

    public ConditionBuilder or(){
        sqlBuilder.pipe.append(" OR");
        return this;
    }

    public ConditionBuilder startScope(){
        numberOfScrope++;
        sqlBuilder.pipe.append(" (");
        return this;
    }

    public ConditionBuilder endScope(){
        numberOfScrope--;
        sqlBuilder.pipe.append(" )");
        return this;
    }

    @Override
    public SQLBuilder nextPipe() throws Exception{
        if(numberOfScrope != 0) {
            throw new Exception("Scrope is not closed properly");
        }
        return sqlBuilder;
    }

    @Override
    public ConditionBuilder isEqualTo(String value) {
        sqlBuilder.pipe.append(" = " + value);
        return this;
    }

    @Override
    public ConditionBuilder isGraterThan(String value) {
        sqlBuilder.pipe.append(" < " + value);
        return this;
    }

    @Override
    public ConditionBuilder isGraterThanOrEqual(String value) {
        sqlBuilder.pipe.append(" <= " + value);
        return this;
    }

    @Override
    public ConditionBuilder isLessThan(String value) {
        sqlBuilder.pipe.append(" > " + value);
        return this;
    }

    @Override
    public ConditionBuilder isLessThanOrEqual(String value) {
        sqlBuilder.pipe.append(" >= " + value);
        return this;
    }

    @Override
    public ConditionBuilder isLike(String value) {
        sqlBuilder.pipe.append(" like " + value);
        return this;
    }
}
