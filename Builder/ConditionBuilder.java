package com.jiantech.SearchQueryForSQL.Builder;

import com.jiantech.SearchQueryForSQL.Builder.Model.VerificationErrorMsg;
import com.jiantech.SearchQueryForSQL.Builder.interfaces.BuildPipeable;
import com.jiantech.SearchQueryForSQL.Builder.interfaces.SQLWhereConditions;
import com.jiantech.SearchQueryForSQL.Builder.interfaces.Verifiable;

public class ConditionBuilder implements BuildPipeable, SQLWhereConditions, Verifiable {
    SQLBuilder sqlBuilder;
    boolean conditionApplied = false;
    boolean fieldCalled = false;
    int numberOfScrope = 0;

    public ConditionBuilder(SQLBuilder builder){
        this.sqlBuilder = builder;
        conditionApplied = true;
        this.sqlBuilder.pipe.append(" \nWHERE");
        this.sqlBuilder.syntaxVerificationPipe.add(this);
    }

    public ConditionBuilder useConditionBuilder(SQLConditionClauseBuilder builder) throws Exception{
        if(!builder.isCompiled()) {
            throw new Exception("The query is not compiled to sql");
        }
        return (ConditionBuilder) field(builder.getQuery());
    }

    public SQLWhereConditions field(String fieldName){
        fieldCalled = true;
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

    @Override
    public ConditionBuilder isEqualTo(SQLBuilder builder) throws Exception {
        return isEqualTo("( "+builder.build() + " ) ");
    }

    @Override
    public ConditionBuilder isGraterThan(SQLBuilder builder) throws Exception {
        return isGraterThan("( "+builder.build() + " ) ");
    }

    @Override
    public ConditionBuilder isGraterThanOrEqual(SQLBuilder builder) throws Exception {
        return isGraterThanOrEqual("( "+builder.build() + " ) ");
    }

    @Override
    public ConditionBuilder isLessThan(SQLBuilder builder) throws Exception {
        return isLessThan("( "+builder.build() + " ) ");
    }

    @Override
    public ConditionBuilder isLessThanOrEqual(SQLBuilder builder) throws Exception {
        return isLessThanOrEqual("( "+builder.build() + " ) ");
    }

    @Override
    public ConditionBuilder isLike(SQLBuilder builder) throws Exception {
        return isLike("( "+builder.build() + " ) ");
    }

    @Override
    public VerificationErrorMsg verify() {
        if(conditionApplied && !fieldCalled)
            return new VerificationErrorMsg(
                    false,
                    "WHERE is applied without Field"
            );
        return new VerificationErrorMsg(true);
    }

    @Override
    public String getPipeName() {
        return "WHERE";
    }
}
