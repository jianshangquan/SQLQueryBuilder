package com.jiantech.SearchQueryForSQL.Builder;

public class SQLFun {
    public static final String CURRENT_TIMESTAMP = "CURRENT_TIMESTAMP";

    public static String curDate(){
        return "CURDATE()";
    }

    public static SQLString currentTimestamp(){
        return new SQLString("CURDATE()");
    }

    public static SQLString ifFunction(String condition, String ifTrue, String ifFalse){
        return new SQLString("IF(" + condition + "," + ifTrue + "," + ifFalse + ")");
    }

    public static SQLString max(String ...args){
        return new SQLString("MAX(" + String.join(",", args) + ")");
    }

    public static SQLString max(String column){
        return new SQLString("MAX(" + column + ")");
    }

    public static SQLString count(String column){
        return new SQLString("COUNT(" + column + ")");
    }

    public static SQLString sum(String column){
        return new SQLString("SUM(" + column + ")");
    }

    public static SQLString concat(String ...args){
        return new SQLString("CONCAT(" + String.join(",", args) + ")");
    }

    public static SQLString add(String ...args){
        return new SQLString( "(" + String.join(" + ", args) + ")");
    }
}




