
package kacke;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionId {

    @SerializedName("agentId")
    @Expose
    private String agentId;
    @SerializedName("donationCode")
    @Expose
    private String donationCode;

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getDonationCode() {
        return donationCode;
    }

    public void setDonationCode(String donationCode) {
        this.donationCode = donationCode;
    }

}
