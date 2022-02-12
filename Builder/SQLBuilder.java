package com.jiantech.SearchQueryForSQL.Builder;

import com.jiantech.SearchQueryForSQL.Builder.interfaces.Buildable;
import com.jiantech.SearchQueryForSQL.Builder.interfaces.Joinable;

public abstract class SQLBuilder {
    protected final StringBuilder pipe = new StringBuilder();

    public abstract Buildable use(String dbName);
    public abstract SelectorBuilder select(String field);
    public abstract SelectorBuilder select(String ...fields);
    public abstract ConditionBuilder where() throws Exception;
    public abstract OrderBuilder orderBy() throws Exception;
    public abstract OrderBuilder orderBy(String field) throws Exception;
    public abstract Joinable join();
    public abstract Joinable join(String tableName);
    public abstract Buildable show(String query);
    public abstract Buildable showCreateTable(String tableName);
    public abstract String build();
    public abstract Buildable set(String query);
}
