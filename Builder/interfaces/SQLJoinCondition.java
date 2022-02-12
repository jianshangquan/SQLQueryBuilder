package com.jiantech.SearchQueryForSQL.Builder.interfaces;

import com.jiantech.SearchQueryForSQL.Builder.SQLBuilder;
import com.jiantech.SearchQueryForSQL.Builder.SQLJoinBuilder;

public interface SQLJoinCondition {
    public SQLJoinBuilder isEqualTo(String value);
    public SQLJoinBuilder isGraterThan(String value);
    public SQLJoinBuilder isLessThan(String value);
    public SQLJoinBuilder isGraterThanAndEqual(String value);
    public SQLJoinBuilder isLessThanAndEqual(String value);
    public SQLJoinBuilder isLike(String value);

    public SQLJoinBuilder isEqualTo(SQLBuilder builder) throws Exception;
    public SQLJoinBuilder isGraterThan(SQLBuilder builder) throws Exception;
    public SQLJoinBuilder isLessThan(SQLBuilder builder) throws Exception;
    public SQLJoinBuilder isGraterThanAndEqual(SQLBuilder builder) throws Exception;
    public SQLJoinBuilder isLessThanAndEqual(SQLBuilder builder) throws Exception;
    public SQLJoinBuilder isLike(SQLBuilder builder) throws Exception;
}
