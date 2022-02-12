package com.jiantech.SearchQueryForSQL.Builder;

import com.jiantech.SearchQueryForSQL.Builder.Model.VerificationErrorMsg;
import com.jiantech.SearchQueryForSQL.Builder.interfaces.BuildPipeable;
import com.jiantech.SearchQueryForSQL.Builder.interfaces.SQLWhereConditions;
import com.jiantech.SearchQueryForSQL.Builder.interfaces.Verifiable;

public class ConditionBuilder implements BuildPipeable, SQLWhereConditions, Verifiable {
    SQLBuilder builder;
    boolean conditionApplied = false;
    boolean fieldCalled = false;
    int numberOfScrope = 0;

    public ConditionBuilder(SQLBuilder builder){
        this.builder = builder;
        conditionApplied = true;
        this.builder.pipe.append(" \nWHERE");
        this.builder.syntaxVerificationPipe.add(this);
    }

    public ConditionBuilder useConditionBuilder(SQLConditionClauseBuilder builder) throws Exception{
        if(!builder.isCompiled()) {
            throw new Exception("The query is not compiled to sql");
        }
        return (ConditionBuilder) field(builder.getQuery());
    }

    public SQLWhereConditions field(String fieldName){
        fieldCalled = true;
        builder.pipe.append(" " + fieldName);
        return this;
    }



    public ConditionBuilder and(){
        builder.pipe.append(" AND");
        return this;
    }

    public ConditionBuilder or(){
        builder.pipe.append(" OR");
        return this;
    }

    public ConditionBuilder startScope(){
        numberOfScrope++;
        builder.pipe.append(" (");
        return this;
    }

    public ConditionBuilder endScope(){
        numberOfScrope--;
        builder.pipe.append(" )");
        return this;
    }

    @Override
    public SQLBuilder nextPipe() throws Exception{
        if(numberOfScrope != 0) {
            throw new Exception("Scrope is not closed properly");
        }
        return builder;
    }


    @Override
    public ConditionBuilder isEqualTo(String value, SQLCompareType type) {
        if(type == SQLCompareType.STRING) return isEqualTo("\'" + value + "\'");
        if(type == SQLCompareType.NUMBER) return isEqualTo(value);
        return isEqualTo(value);
    }


    @Override
    public ConditionBuilder isEqualTo(String value) {
        builder.pipe.append(" = " + value);
        return this;
    }

    @Override
    public ConditionBuilder isGraterThan(String value) {
        builder.pipe.append(" < " + value);
        return this;
    }

    @Override
    public ConditionBuilder isGraterThanOrEqual(String value) {
        builder.pipe.append(" <= " + value);
        return this;
    }

    @Override
    public ConditionBuilder isLessThan(String value) {
        builder.pipe.append(" > " + value);
        return this;
    }

    @Override
    public ConditionBuilder isLessThanOrEqual(String value) {
        builder.pipe.append(" >= " + value);
        return this;
    }

    @Override
    public ConditionBuilder isLike(String value) {
        builder.pipe.append(" like " + value);
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
    public ConditionBuilder isTrue() {
        this.builder.pipe.append(" = TRUE");
        return this;
    }

    @Override
    public ConditionBuilder isFalse() {
        this.builder.pipe.append(" = FALSE");
        return this;
    }

    @Override
    public ConditionBuilder isNull() {
        builder.pipe.append(" IS NULL");
        return this;
    }

    @Override
    public ConditionBuilder isNotNull() {
        builder.pipe.append(" IS NOT NULL");
        return this;
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
