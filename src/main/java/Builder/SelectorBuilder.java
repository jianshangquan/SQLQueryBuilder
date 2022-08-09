package Builder;

import com.jiantech.SearchQueryForSQL.Builder.Model.VerificationErrorMsg;
import com.jiantech.SearchQueryForSQL.Builder.interfaces.Verifiable;

public class SelectorBuilder implements Verifiable {
    SQLBuilder builder;
    String fields;
    boolean fromCalled = false;

    public SelectorBuilder(SQLBuilder builder, String fields){
        this.builder = builder;
        this.fields = fields;
        this.builder.syntaxVerificationPipe.add(this);
        this.builder.pipe.append("SELECT ");
    }

    public SQLBuilder from(String tableName){
        fromCalled = true;
        this.builder.pipe.append(this.fields + " FROM `" + tableName + '`');
        return builder;
    }

    public SQLBuilder from(SQLBuilder sqlBuilder, String as) throws Exception {
        fromCalled = true;
        this.builder.pipe.append(this.fields + " FROM ( " + sqlBuilder.build() + ") as `" + as + '`');
        return builder;
    }

    @Override
    public VerificationErrorMsg verify() {
        return new VerificationErrorMsg(
                fromCalled,
                "FROM is not called"
        );
    }

    @Override
    public String getPipeName() {
        return "SELECT";
    }
}
