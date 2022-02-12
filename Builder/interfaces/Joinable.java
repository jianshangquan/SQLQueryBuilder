package com.jiantech.SearchQueryForSQL.Builder.interfaces;

public interface Joinable {
    public SQLJoinOn innerJoin();
    public SQLJoinOn leftOuterJoin();
    public SQLJoinOn join();
    public SQLJoinOn rightJoin();

    public SQLJoinOn innerJoin(String tableName);
    public SQLJoinOn leftOuterJoin(String tableName);
    public SQLJoinOn join(String tableName);
    public SQLJoinOn rightJoin(String tableName);
}
