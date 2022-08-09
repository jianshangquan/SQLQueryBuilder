import Builder.SQLBuilder;
import Builder.SQLCompareType;
import Builder.SQLFun;

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

//        sqlQuery = SQLBuilder.getMySQLBuilder()
//                        .select("voucher_id","item_name",
//                                SQLFun.sum("pawn_amount").as("sum").compile()
//                                )
//                                .from("goldpawndata")
//                .where().field("is_delete").isNotNull().nextPipe()
//                .build();



//        sqlQuery = SQLBuilder.getMySQLBuilder()
//                .createTable("fdsaf")
//                        .addField("col1", "int", "1", "Utf-8")
//                .setDefaultEncoding("fdsafd")
//                                .nextPipe().build();

//        sqlQuery = SQLBuilder.getMySQLBuilder()
//                .select(
//                        SQLFun.sum("pawn_amount").as("sum").transpile()
//                ).from("goldpawndata")
//                .join("goldchangedata").leftOuterJoin()
//                .on("voucher_id").isEqualTo("goldpawndata.voucher_id").nextPipe()
//                .where().field("item_name").isEqualTo("စောက်သံကွင်းကြိုး(၁)", SQLCompareType.STRING).and()
//                .field("goldchangedata.is_delete").isFalse().nextPipe()
//                .orderBy("item_name").ascending().nextPipe().build();


        sqlQuery = SQLBuilder.getMySQLBuilder().select("fields").from("table").build();
//        sqlQuery = SQLBuilder.getMySQLBuilder().select("field1, field2")
//                .from("table")
//                .where().field("field1").isGraterThan("20")
//                .nextPipe().build();

//        sqlQuery = SQLBuilder.getMySQLBuilder()
//                .select(
//                        SQLFun.sum("field").as("sum").transpile()
//                ).from("table1")
//                .join("table2").leftOuterJoin()
//                .on("file2").isEqualTo("table1.field").nextPipe()
//                .where().field("field1").isEqualTo("value").nextPipe()
//                .orderBy("table1.field").ascending().nextPipe()
//                .build();

        System.out.println(sqlQuery);
    }
}
