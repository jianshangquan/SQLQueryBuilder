package com.jiantech.SearchQueryForSQL;

public class MySqlQueryBuilder {
    private StringBuilder query = new StringBuilder();
    private boolean conditionApplied = false;

    public MySqlQueryBuilder select(String tableName, String field){
        query.append("select " + field + " from " + tableName);
        return this;
    }

    public MySqlQueryBuilder and(){
        query.append(" and ");
        return this;
    }

    public MySqlQueryBuilder or(){
        query.append(" or ");
        return this;
    }

    public MySqlQueryBuilder condition(String colName, String condition, String value){
        if(conditionApplied){
            query.append(colName + " " + condition + " " + value);
        }else{
            query.append(" where " + colName + " " + condition + " " + value);
            conditionApplied = true;
        }
        return this;
    }

    public MySqlQueryBuilder join(){
        return this;
    }

    public MySqlQueryBuilder leftOuterJoin(){
        return this;
    }

    public MySqlQueryBuilder leftInnerJoin(){
        return this;
    }

    public MySqlQueryBuilder startParam(){
        query.append("(");
        return this;
    }

    public MySqlQueryBuilder closeParam(){
        query.append(")");
        return this;
    }

    public MySqlQueryBuilder orderBy(String field, SQLOrder order){
        query.append(" order by " + field + " " + order.toString());
        return this;
    }

    public String build(){
        System.out.println(query.toString());
        return query.toString();
    }
}

