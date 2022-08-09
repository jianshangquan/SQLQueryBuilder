package Builder;


import Builder.Model.VerificationErrorMsg;
import Builder.interfaces.BuildPipeable;
import Builder.interfaces.Verifiable;

public class SQLTableBuilder implements Verifiable, BuildPipeable {
    SQLBuilder builder;
    String tableEncoding;

    public SQLTableBuilder(SQLBuilder builder, String tableName){
        this.builder = builder;
        this.builder.pipe.append("CREATE TABLE `" + tableName + "` (\n");
    }

    public SQLTableBuilder addField(String fieldName, String dataType){
        builder.pipe.append("\t`" + fieldName + "` " + dataType);
        return this;
    }

    public SQLTableBuilder addField(String fieldName, String dataType, String defaultValue){
        builder.pipe.append("\t`" + fieldName + "` " + dataType + " default " + defaultValue);
        return this;
    }

    public SQLTableBuilder addField(String fieldName, String dataType, String defaultValue, String encoding){
        builder.pipe.append("\t`" + fieldName + "` " + dataType + " default " + defaultValue);
        return this;
    }



    public SQLTableBuilder setDefaultEncoding(String encoding){
        tableEncoding = encoding;
        return this;
    }

    @Override
    public VerificationErrorMsg verify() {
        return new VerificationErrorMsg(false);
    }

    @Override
    public String getPipeName() {
        return "CREATE TABLE";
    }

    @Override
    public SQLBuilder nextPipe() throws Exception {
        if(tableEncoding != null) builder.pipe.append("\n) " + tableEncoding);
        else builder.pipe.append("\n) ");
        return builder;
    }
}
