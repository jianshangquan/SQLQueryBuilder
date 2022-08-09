# SQLQueryBuilder

Is a simple SQL query builder in Java\
Currently it support only MySQL query


### HOW TO USE?

##### scenario 1
```java
SQLBuilder.getMySQLBuilder().select("fields").from("table").build()

// RESULT : 
//        SELECT fields FROM `table`;
```

##### scenario 2
```java
SQLBuilder.getMySQLBuilder().select("fields").from("table").build()

// RESULT : 
//        SELECT fields FROM `table`;
```

##### scenario 3
```java
SQLBuilder.getMySQLBuilder().select("field1, field2")
        .from("table")
        .where().field("field1").isGraterThan("20")
        .nextPipe().build();

// RESULT : 
//        SELECT field1, field2 FROM `table`
//        WHERE field1 < 20;

```

##### scenario 4
```java

SQLBuilder.getMySQLBuilder()
        .select(
                SQLFun.sum("field").as("sum").transpile()
        ).from("table1")
        .join("table2").leftOuterJoin()
        .on("file2").isEqualTo("table1.field").nextPipe()
        .where().field("field1").isEqualTo("value").nextPipe()
        .orderBy("table1.field").ascending().nextPipe()
        .build();

// RESULT: 
//        SELECT SUM(field) as 'sum' FROM `table1`
//        LEFT OUTER JOIN `table2` ON table2.file2 = table1.field
//        WHERE field1 = value
//        ORDER BY table1.field ASC;

```