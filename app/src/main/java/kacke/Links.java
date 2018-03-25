
package kacke;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links {

    @SerializedName("self")
    @Expose
    private Self self;
    @SerializedName("transaction")
    @Expose
    private Transaction_ transaction;

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }

    public Transaction_ getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction_ transaction) {
        this.transaction = transaction;
    }

}
