package Builder.interfaces;


import Builder.ConditionBuilder;
import Builder.SQLBuilder;
import Builder.SQLCompareType;

public interface SQLWhereConditions<T> {

    public ConditionBuilder isEqualTo(String value);
    public ConditionBuilder isEqualTo(String value, SQLCompareType type);
    public ConditionBuilder isGraterThan(String value);
    public ConditionBuilder isGraterThanOrEqual(String value);
    public ConditionBuilder isLessThan(String value);
    public ConditionBuilder isLessThanOrEqual(String value);
    public ConditionBuilder isLike(String value);


    public ConditionBuilder isEqualTo(SQLBuilder builder) throws Exception;
    public ConditionBuilder isGraterThan(SQLBuilder builder) throws Exception;
    public ConditionBuilder isGraterThanOrEqual(SQLBuilder builder) throws Exception;
    public ConditionBuilder isLessThan(SQLBuilder builder) throws Exception;
    public ConditionBuilder isLessThanOrEqual(SQLBuilder builder) throws Exception;
    public ConditionBuilder isLike(SQLBuilder builder) throws Exception;

    public ConditionBuilder isTrue();
    public ConditionBuilder isFalse();
    public ConditionBuilder isNull();
    public ConditionBuilder isNotNull();
}
