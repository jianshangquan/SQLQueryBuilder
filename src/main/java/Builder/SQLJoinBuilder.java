package Builder;


import Builder.Model.VerificationErrorMsg;
import Builder.interfaces.*;

public class SQLJoinBuilder implements BuildPipeable, SQLJoinCondition, Joinable, SQLJoinOn, Verifiable {
    SQLBuilder builder;
    String tableName;
    boolean isJoinApplied = false;
    int numberOfScrope = 0;

    public SQLJoinBuilder(SQLBuilder builder){
        this.builder = builder;
        this.builder.syntaxVerificationPipe.add(this);
    }

    public SQLJoinBuilder(SQLBuilder builder, String tableName){
        this.builder = builder;
        this.tableName = tableName;
        this.builder.syntaxVerificationPipe.add(this);
    }

    @Override
    public SQLJoinOn innerJoin() {
        isJoinApplied = true;
        builder.pipe.append(" \nINNER JOIN `" + tableName + '`');
        return this;
    }

    @Override
    public SQLJoinOn leftOuterJoin() {
        isJoinApplied = true;
        builder.pipe.append(" \nLEFT OUTER JOIN `" + tableName + '`');
        return this;
    }

    @Override
    public SQLJoinOn join() {
        isJoinApplied = true;
        builder.pipe.append(" \nJOIN `" + tableName + '`');
        return this;
    }

    @Override
    public SQLJoinOn rightJoin() {
        isJoinApplied = true;
        builder.pipe.append(" \nRIGHT OUTER JOIN `" + tableName + '`');
        return this;
    }

    @Override
    public SQLJoinOn innerJoin(String tableName){
        isJoinApplied = true;
        if(this.tableName == null) this.tableName = tableName;
        builder.pipe.append(" \nINNER JOIN `" + tableName + '`');
        return this;
    }

    @Override
    public SQLJoinOn leftOuterJoin(String tableName){
        isJoinApplied = true;
        if(this.tableName == null) this.tableName = tableName;
        builder.pipe.append(" \nLEFT OUTER JOIN `" + tableName + '`');
        return this;
    }

    @Override
    public SQLJoinOn join(String tableName){
        isJoinApplied = true;
        if(this.tableName == null) this.tableName = tableName;
        builder.pipe.append(" \nJOIN `" + tableName + '`');
        return this;
    }

    @Override
    public SQLJoinOn rightJoin(String tableName){
        isJoinApplied = true;
        if(this.tableName == null) this.tableName = tableName;
        builder.pipe.append(" \nRIGHT OUTER JOIN `" + tableName + '`');
        return this;
    }


    @Override
    public SQLJoinOn innerJoin(SQLBuilder builder, String as) throws Exception {
        isJoinApplied = true;
        tableName = as;
        this.builder.pipe.append(" \nINNER JOIN ( " + builder.build() + " ) as " + as);
        return this;
    }

    @Override
    public SQLJoinOn leftOuterJoin(SQLBuilder builder, String as) throws Exception {
        isJoinApplied = true;
        tableName = as;
        this.builder.pipe.append(" \nLEFT OUTER JOIN ( " + builder.build() + " ) as " + as);
        return this;
    }

    @Override
    public SQLJoinOn join(SQLBuilder builder, String as) throws Exception {
        isJoinApplied = true;
        tableName = as;
        this.builder.pipe.append(" \nJOIN ( " + builder.build() + " ) as " + as);
        return this;
    }

    @Override
    public SQLJoinOn rightJoin(SQLBuilder builder, String as) {
        isJoinApplied = true;
        tableName = as;
        this.builder.pipe.append(" \nRIGHT OUTER JOIN ( " + tableName + " ) as " + as);
        return this;
    }

    @Override
    public SQLJoinCondition on(String fieldName){
        builder.pipe.append(" ON " + this.tableName + "." + fieldName);
        return this;
    }

    @Override
    public SQLJoinBuilder isEqualTo(String value, SQLCompareType type) {
        if(type == SQLCompareType.STRING) return isEqualTo("\'" + value + "\'");
        if(type == SQLCompareType.NUMBER) return isEqualTo(value);
        return isEqualTo(value);
    }

    @Override
    public SQLJoinBuilder isEqualTo(String value){
        builder.pipe.append(" = " + value);
        return this;
    }

    @Override
    public SQLJoinBuilder as(String tableNameAs){
        builder.pipe.append(" as `" + tableNameAs + '`');
        this.tableName = tableNameAs;
        return this;
    }

    @Override
    public SQLJoinBuilder isGraterThan(String value) {
        builder.pipe.append(" < " + value);
        return this;
    }

    @Override
    public SQLJoinBuilder isLessThan(String value) {
        builder.pipe.append(" > " + value);
        return this;
    }


    @Override
    public SQLJoinBuilder isGraterThanAndEqual(String value) {
        builder.pipe.append(" <= " + value);
        return this;
    }

    @Override
    public SQLJoinBuilder isLessThanAndEqual(String value) {
        builder.pipe.append(" >= " + value);
        return this;
    }

    @Override
    public SQLJoinBuilder isLike(String value) {
        builder.pipe.append(" LIKE " + value);
        return this;
    }

    @Override
    public SQLJoinBuilder isEqualTo(SQLBuilder builder) throws Exception {
        return isEqualTo("( " + builder.build() + " )");
    }

    @Override
    public SQLJoinBuilder isGraterThan(SQLBuilder builder) throws Exception {
        return isGraterThan("( " + builder.build() + " )");
    }

    @Override
    public SQLJoinBuilder isLessThan(SQLBuilder builder) throws Exception {
        return isLessThan("( " + builder.build() + " )");
    }

    @Override
    public SQLJoinBuilder isGraterThanAndEqual(SQLBuilder builder) throws Exception {
        return isGraterThanAndEqual("( " + builder.build() + " )");
    }

    @Override
    public SQLJoinBuilder isLessThanAndEqual(SQLBuilder builder) throws Exception {
        return isLessThanAndEqual("( " + builder.build() + " )");
    }

    @Override
    public SQLJoinBuilder isLike(SQLBuilder builder) throws Exception {
        return isLike("( " + builder.build() + " )");
    }

    @Override
    public SQLJoinBuilder isTrue() {
        builder.pipe.append(" = TRUE");
        return this;
    }

    @Override
    public SQLJoinBuilder isFalse() {
        builder.pipe.append(" = FALSE");
        return this;
    }

    @Override
    public SQLJoinBuilder isNull() {
        builder.pipe.append(" IS NULL");
        return this;
    }

    @Override
    public SQLJoinBuilder isNotNull() {
        builder.pipe.append(" IS NOT NULL");
        return this;
    }

    @Override
    public SQLBuilder nextPipe() {
        return builder;
    }

    @Override
    public VerificationErrorMsg verify() {
        if(isJoinApplied){
            return new VerificationErrorMsg(
                    isJoinApplied,
                    "JOIN was use before a condiiton"
            );
        }

        if(numberOfScrope != 0) {
            return new VerificationErrorMsg(
                    numberOfScrope != 0,
                    "Scrope is not properly close"
            );
        }
        return new VerificationErrorMsg(true);
    }

    public SQLJoinBuilder or(){
        builder.pipe.append(" OR");
        return this;
    }

    public SQLJoinBuilder startScope(){
        numberOfScrope++;
        builder.pipe.append(" (");
        return this;
    }

    public SQLJoinBuilder endScope(){
        numberOfScrope--;
        builder.pipe.append(" )");
        return this;
    }

    public SQLJoinBuilder field(String fieldName){
        builder.pipe.append(" `" + fieldName + '`');
        return this;
    }

    @Override
    public String getPipeName() {
        return "JOIN";
    }
}
