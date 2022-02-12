package com.jiantech.SearchQueryForSQL.Builder;

public interface SQLJoinCondition {
    public SQLJoinBuilder isEqualTo(String value);
    public SQLJoinBuilder isGraterThan(String value);
    public SQLJoinBuilder isLessThan(String value);
    public SQLJoinBuilder isGraterThanAndEqual(String value);
    public SQLJoinBuilder isLessThanAndEqual(String value);
    public SQLJoinBuilder isLike(String value);
}
