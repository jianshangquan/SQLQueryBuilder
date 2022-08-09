package Builder.interfaces;

import Builder.Model.VerificationErrorMsg;

public interface Verifiable {
    public VerificationErrorMsg verify();
    public String getPipeName();
}
