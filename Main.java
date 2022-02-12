package com.jiantech.SearchQueryForSQL;

import com.jiantech.SearchQueryForSQL.Builder.MySqlQueryBuilder;
import com.jiantech.SearchQueryForSQL.Builder.SQLBuilder;

public class Main {

    public static void main(String[] args) throws Exception {
//        QueryForSQL.getQuery(
//                "(=123|>123)&<123&L123|>=\"235\"",
//                "event",
//                "uid"
//        );

        SQLBuilder sqlBuilder = new MySqlQueryBuilder();
        String sqlQuery = sqlBuilder
                .select("col1", "col2", "col3")
                .from("tunlinpawnshop")
                .join("fdsafd").innerJoin().on("sfdas").isEqualTo("fds").nextPipe()
                .where().startScope().field("fdsfadsa").isLessThanOrEqual("fsaf").or()
                .field("fdsa").isEqualTo("fdsf").endScope().nextPipe()
                .build();





        System.out.println(sqlQuery);
    }
}
