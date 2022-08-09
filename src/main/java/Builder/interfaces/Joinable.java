package Builder.interfaces;

import com.jiantech.SearchQueryForSQL.Builder.SQLBuilder;

public interface Joinable {
    public SQLJoinOn innerJoin();
    public SQLJoinOn leftOuterJoin();
    public SQLJoinOn join();
    public SQLJoinOn rightJoin();

    public SQLJoinOn innerJoin(String tableName);
    public SQLJoinOn leftOuterJoin(String tableName);
    public SQLJoinOn join(String tableName);
    public SQLJoinOn rightJoin(String tableName);

    public SQLJoinOn innerJoin(SQLBuilder builder, String as) throws Exception;
    public SQLJoinOn leftOuterJoin(SQLBuilder builder, String as) throws Exception;
    public SQLJoinOn join(SQLBuilder builder, String as) throws Exception;
    public SQLJoinOn rightJoin(SQLBuilder builder, String as);
}
