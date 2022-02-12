package com.jiantech.SearchQueryForSQL.Builder;

public class SQLValidator {
    public static boolean checkValidField(String field){
        return !field.contains(" ");
    }
}
