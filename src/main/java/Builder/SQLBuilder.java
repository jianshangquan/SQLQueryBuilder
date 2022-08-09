package Builder;

import com.jiantech.SearchQueryForSQL.Builder.interfaces.Buildable;
import com.jiantech.SearchQueryForSQL.Builder.interfaces.Joinable;
import com.jiantech.SearchQueryForSQL.Builder.interfaces.Verifiable;

import java.util.ArrayList;
import java.util.List;

public abstract class SQLBuilder {
    protected final StringBuilder pipe = new StringBuilder();
    protected final List<Verifiable> syntaxVerificationPipe = new ArrayList<>();

    public static SQLBuilder getMySQLBuilder(){
        return MySqlQueryBuilder.getInstance();
    }

    public abstract SQLBuilder append(String query);
    public abstract Buildable rawQuery(String query);

    public abstract SQLTableBuilder createTable(String tableName);
    public abstract Buildable use(String dbName);
    public abstract SelectorBuilder select(String field);
    public abstract SelectorBuilder selectStar();
    public abstract SelectorBuilder select(String ...fields);
    public abstract ConditionBuilder where() throws Exception;
    public abstract OrderBuilder orderBy() throws Exception;
    public abstract OrderBuilder orderBy(String field) throws Exception;
    public abstract Joinable join();
    public abstract Joinable join(String tableName);
    public abstract Buildable show(String query);
    public abstract Buildable showCreateTable(String tableName);
    public abstract String build() throws Exception;
    public abstract Buildable set(String query);
    public abstract SQLBuilder getProcessedPipe();
}
