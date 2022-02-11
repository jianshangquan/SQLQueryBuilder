package com.jiantech.SearchQueryForSQL;

public class Main {

    public static void main(String[] args) {
        QueryForSQL.getQuery(
                "(=123|>123)&<123&L123|>=\"235\"",
                "event",
                "uid"
        );

        MySqlQueryBuilder mySqlQueryBuilder = new MySqlQueryBuilder();
        mySqlQueryBuilder
                .select("Table", "*")
                .condition("col1", "=", "1")
                .and()
                .condition("col2", "=", "2")
                .orderBy("col2", SQLOrder.DESC)
                .build();
    }
}
