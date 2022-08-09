package Builder;

import com.jiantech.SearchQueryForSQL.Builder.SQLValidator;

public class SQLUtils {
    public static String formatValue(String value){
        if(SQLValidator.isNumeric(value)){
            return value;
        }else{
            return "\"" + value + "\"";
        }
    }
}
