package com.jiantech.SearchQueryForSQL.Builder;

public interface SQLJoinOn {
    public SQLJoinCondition on(String fieldName);
    public SQLJoinOn as(String as);
}
