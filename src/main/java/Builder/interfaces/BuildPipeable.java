package Builder.interfaces;

import com.jiantech.SearchQueryForSQL.Builder.SQLBuilder;

public interface BuildPipeable {
    public SQLBuilder nextPipe() throws Exception;
}
