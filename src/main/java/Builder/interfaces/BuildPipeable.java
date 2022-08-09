package Builder.interfaces;

import Builder.SQLBuilder;

public interface BuildPipeable {
    public SQLBuilder nextPipe() throws Exception;
}
