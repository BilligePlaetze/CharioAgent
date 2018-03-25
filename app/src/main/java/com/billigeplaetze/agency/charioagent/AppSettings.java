package com.billigeplaetze.agency.charioagent;

import java.net.URL;

/**
 */

public class AppSettings {
    private static String agentName;
    public static String getTransactions = "http://192.168.170.144:8080/transaction/search/findByTransactionIdAgentIdAndState";
    public static String dickPickAddress = "http://192.168.170.144:8080/transaction";
    public static String getAgentName() {
        return agentName;
    }

    public static void setAgentName(String agentName) {
        AppSettings.agentName = agentName;
    }
}
