package com.jiantech.SearchQueryForSQL;



// select * from Table where uid = 123
// or          ->   |
// and         ->   &
// grater than ->   >
// less than   ->   <
// like        ->   L
//
// Sample query
//         =123|>123&<123       => where uid = 123 or uid > 123 and uid < 123
//         [ = , 123 , | , > , 123 , & , < , 123 , & , >= , 123 ]


import java.util.*;
import java.util.stream.Collectors;

public class QueryForSQL {
    public static final Map<String, String> compileDictionary = new HashMap<>() {{
       put("|", " or ");
       put("&", " and ");
    }};

    public static String getQuery(String rawQuery, String tableName, String columnName){
//        System.out.println("Given query " + rawQuery);
        List lexical = split(rawQuery);
        String sqlQuery = compileToSQlQuery(lexical, columnName);
//        System.out.println("sql query " + sqlQuery);
        return sqlQuery;
    }

    private static String compileToSQlQuery(List<String> lexical, String colName){
        lexical = lexical.stream().map(keyword -> {
            if("|".equals(keyword)){
                return " or ";
            }else if("&".equals(keyword)){
                return " and ";
            }else if("L".equals(keyword)){
                return colName + " like ";
            }else if(">=".equals(keyword)){
                return colName + " >= ";
            }else if("<=".equals(keyword)){
                return colName + " <= ";
            }else if(">".equals(keyword)){
                return colName + " > ";
            }else if("<".equals(keyword)){
                return colName + " < ";
            }else if("=".equals(keyword)){
                return colName + " = ";
            }else if("(".equals(keyword) || ")".equals(keyword)){
                return keyword;
            }else if(isNumeric(keyword)){
                return keyword;
            }else if(isString(keyword) && !(keyword.startsWith("\"") && keyword.endsWith("\""))){
                return "\"" + keyword + "\"";
            }
            return keyword;
        }).collect(Collectors.toList());
        return String.join("", lexical);
    }

    private static List<String> split(String rawQuery){
        List<String> lexical = new ArrayList<>();
        String character = "";
        String context = "";
        for (int i = 0; i < rawQuery.length(); i++){
            character = String.valueOf(rawQuery.charAt(i));
            CombinedSyntax combinedSyntax = checkCombinedSyntax(rawQuery, i);
            if(combinedSyntax.isSyntax){
                lexical.add(rawQuery.substring(combinedSyntax.startIndex, combinedSyntax.endIndex));
                i = combinedSyntax.endIndex - 1;
            }else if(isSyntax(character)){
                if(!context.isEmpty()) {
                    lexical.add(context);
                }
                context = "";
                lexical.add(character);
            }else{
                context = context + character;
            }
        }
        lexical.add(context);
//        printList(lexical);
        return lexical;
    }


    private static CombinedSyntax checkCombinedSyntax(String query, int startCheckIndex){
        String startQuery = query.substring(startCheckIndex, query.length());
        if(startQuery.startsWith(">=")){
            return new CombinedSyntax(
                    startCheckIndex,
                    2,
                    true
            );
        }else if(startQuery.startsWith("<=")){
            return new CombinedSyntax(
                    startCheckIndex,
                    2,
                    true
            );
        }
        return new CombinedSyntax();
    }

    private static boolean isSyntax(String syntax){
        return "=".equals(syntax) ||
                "|".equals(syntax) ||
                "&".equals(syntax) ||
                "(".equals(syntax) ||
                ")".equals(syntax) ||
                ">".equals(syntax) ||
                "<".equals(syntax) ||
                ">=".equals(syntax) ||
                "<=".equals(syntax) ||
                "L".equals(syntax);
    }

    private static boolean isSyntax(char syntax){
        return isSyntax(String.valueOf(syntax));
    }


    private static boolean isNumeric(String value){
        if (value == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(value);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private static boolean isString(String value){
        return !isNumeric(value);
    }

    private static void printList(List<String> list){
        for (String l : list){
            System.out.print(l + ",");
        }
        System.out.println();
    }
}



class CombinedSyntax{
    int startIndex;
    int endIndex;
    boolean isSyntax = false;

    public CombinedSyntax(int startIndex, int endIndex, boolean isSyntax){
        this.startIndex = startIndex;
        this.endIndex = startIndex + endIndex;
        this.isSyntax = isSyntax;
    }

    public CombinedSyntax(){

    }
}
