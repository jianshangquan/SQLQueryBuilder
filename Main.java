package com.jiantech.SearchQueryForSQL;

import com.jiantech.SearchQueryForSQL.Builder.SQLBuilder;

public class Main {

    public static void main(String[] args) throws Exception {
//        QueryForSQL.getQuery(
//                "(=123|>123)&<123&L123|>=\"235\"",
//                "event",
//                "uid"
//        );

//        SQLBuilder sqlBuilder = new MySqlQueryBuilder();
//        String sqlQuery = SQLBuilder.getMySQLBuilder()
//                .select("col1", "col2", "col3")
//                .from("db")
//                .join().leftOuterJoin("tun").on("col2").isEqualTo("fds").or().field("fds").isEqualTo("fds").nextPipe()
//                .where().field("fdsafds").isEqualTo("fds").and()
//                .field("fdsafds").isEqualTo(new MySqlQueryBuilder().select("max(uid)").from("db")).nextPipe().build();




        String sqlQuery = "";

        sqlQuery = SQLBuilder.getMySQLBuilder()
                        .selectStar().from("goldpawndata")
                        .where().field("column1").isEqualTo("123").nextPipe()
                        .orderBy("voucher_id").ascending().nextPipe()
                        .build();



        System.out.println(sqlQuery);
    }
}
