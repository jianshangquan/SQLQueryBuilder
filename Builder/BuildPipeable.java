package com.jiantech.SearchQueryForSQL.Builder;

public interface BuildPipeable {
    public SQLBuilder nextPipe() throws Exception;
}
