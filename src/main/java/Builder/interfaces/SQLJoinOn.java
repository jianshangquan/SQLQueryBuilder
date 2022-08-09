package Builder.interfaces;

public interface SQLJoinOn {
    public SQLJoinCondition on(String fieldName);
    public SQLJoinOn as(String as);
}
