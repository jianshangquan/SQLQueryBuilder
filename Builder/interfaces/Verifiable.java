package com.jiantech.SearchQueryForSQL.Builder.interfaces;

import com.jiantech.SearchQueryForSQL.Builder.Model.VerificationErrorMsg;

public interface Verifiable {
    public VerificationErrorMsg verify();
    public String getPipeName();
}
