package com.jiantech.SearchQueryForSQL.Builder.interfaces;

import com.jiantech.SearchQueryForSQL.Builder.ConditionBuilder;

public interface SQLWhereConditions<T> {
    public ConditionBuilder isEqualTo(String value);
    public ConditionBuilder isGraterThan(String value);
    public ConditionBuilder isGraterThanOrEqual(String value);
    public ConditionBuilder isLessThan(String value);
    public ConditionBuilder isLessThanOrEqual(String value);
    public ConditionBuilder isLike(String value);
}
