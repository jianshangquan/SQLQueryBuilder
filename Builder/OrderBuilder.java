package com.jiantech.SearchQueryForSQL.Builder;

import com.jiantech.SearchQueryForSQL.Builder.Model.VerificationErrorMsg;
import com.jiantech.SearchQueryForSQL.Builder.interfaces.BuildPipeable;
import com.jiantech.SearchQueryForSQL.Builder.interfaces.Verifiable;

public class OrderBuilder implements BuildPipeable, Verifiable {
    SQLBuilder sqlBuilder;
    boolean orderApplied = false;
    boolean isOrderTypeSet = false;
    String field;

    public OrderBuilder(SQLBuilder builder){
        this.sqlBuilder = builder;
        this.sqlBuilder.syntaxVerificationPipe.add(this);
        this.sqlBuilder.pipe.append("\nORDER BY");
    }
    public OrderBuilder(SQLBuilder builder, String field){
        this.sqlBuilder = builder;
        this.field = field;
        this.sqlBuilder.pipe.append("\nORDER BY");
    }


    public OrderBuilder field(String fieldName){
        orderApplied = true;
        sqlBuilder.pipe.append(" " + fieldName);
        return this;
    }

    public OrderBuilder ascending(){
        isOrderTypeSet = true;
        if(field != null) sqlBuilder.pipe.append(" " + field + " ASC");
        else sqlBuilder.pipe.append(" ASC");
        return this;
    }

    public OrderBuilder descending(){
        isOrderTypeSet = true;
        if(field != null) sqlBuilder.pipe.append(" " + field + " DESC");
        else sqlBuilder.pipe.append(" DESC");
        return this;
    }

    @Override
    public SQLBuilder nextPipe() {
        return this.sqlBuilder;
    }

    @Override
    public VerificationErrorMsg verify() {
        if(orderApplied && !isOrderTypeSet){
            return new VerificationErrorMsg(
                    false,
                    "ORDER BY used without Order type"
            );
        }

        return new VerificationErrorMsg(true);
    }

    @Override
    public String getPipeName() {
        return "ORDER BY";
    }
}
