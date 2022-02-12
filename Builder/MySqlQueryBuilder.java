package com.jiantech.SearchQueryForSQL.Builder;


import com.jiantech.SearchQueryForSQL.Builder.Model.VerificationErrorMsg;
import com.jiantech.SearchQueryForSQL.Builder.interfaces.Buildable;
import com.jiantech.SearchQueryForSQL.Builder.interfaces.Joinable;
import com.jiantech.SearchQueryForSQL.Builder.interfaces.SQLConfiguration;

public class MySqlQueryBuilder extends SQLBuilder implements Buildable, SQLConfiguration {

    boolean whereClauseApplied = false;
    boolean orderByClauseApplied = false;
    boolean selectedApplied = false;

    private static final SQLBuilder instance = new MySqlQueryBuilder();

    protected static SQLBuilder getInstance(){
        return instance;
    }


    @Override
    public Buildable use(String dbName){
        pipe.append("USE " + dbName);
        return this;
    }

    @Override
    public SelectorBuilder select(String ...fields){
        selectedApplied = true;
        return new SelectorBuilder(this, String.join(", ", fields));
    }

    @Override
    public SelectorBuilder select(String field){
        selectedApplied = true;
        return new SelectorBuilder(this, field);
    }

    @Override
    public SelectorBuilder selectStar() {
        return select("*");
    }

    @Override
    public ConditionBuilder where() throws Exception {
        if(orderByClauseApplied){
            throw new Exception("WHERE should apply before ORDER BY");
        }else if(whereClauseApplied) {
            throw new Exception("WHERE clause can be use only once");
        }else if(!selectedApplied){
            throw new Exception("Can't use SELECT without SELECT");
        }
        whereClauseApplied = true;
        return new ConditionBuilder(this);
    }

    @Override
    public OrderBuilder orderBy() throws Exception{
        if (orderByClauseApplied) {
            throw new Exception("ORDER BY can be use only once");
        }else if(!selectedApplied){
            throw new Exception("Can't use ORDER BY without SELECT");
        }
        orderByClauseApplied = true;
        return new OrderBuilder(this);
    }

    @Override
    public OrderBuilder orderBy(String field) throws Exception{
        if (orderByClauseApplied) {
            throw new Exception("ORDER BY can be use only once");
        }else if(!selectedApplied){
            throw new Exception("Can't use ORDER BY without SELECT");
        }
        orderByClauseApplied = true;
        return new OrderBuilder(this, field);
    }

    @Override
    public Joinable join(){
        return new SQLJoinBuilder(this);
    }

    @Override
    public Joinable join(String tableName) {
        return new SQLJoinBuilder(this, tableName);
    }


    @Override
    public String build() throws Exception {
        boolean verified = true;
        for (int i = 0; i < syntaxVerificationPipe.size(); i++){
            VerificationErrorMsg verification = syntaxVerificationPipe.get(i).verify();
            if(!verification.getStatus()){
                verified = false;
                System.out.println(verification.getMessage());
            }
        }
        if(!verified) throw new Exception("There is some error within build pipe");
        return pipe.append(";").toString();
    }

    @Override
    public Buildable show(String query) {
        pipe.append("SHOW " + query);
        return this;
    }

    @Override
    public Buildable showCreateTable(String tableName) {
        pipe.append("SHOW CREATE TABLE " + tableName);
        return this;
    }

    @Override
    public Buildable set(String query) {
        return this;
    }

    @Override
    public SQLBuilder getProcessedPipe() {
        return this;
    }
}

