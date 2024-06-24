package dynamic;

import java.util.Map;
import java.util.HashMap;

public class LeastResponseTimeLoadBalancer {

    private final Map<String, Long> serverResponseTimes;

    public LeastResponseTimeLoadBalancer() {
        this.serverResponseTimes = new HashMap<>();
    }

    public void addServer(String serverName) {
        serverResponseTimes.put(serverName, 0L);
    }

    public String getServerWithLeastResponseTime() {
        long minResponseTime = Long.MAX_VALUE;
        String selectedServer = null;

        for (Map.Entry<String, Long> entry : serverResponseTimes.entrySet()) {
            if (entry.getValue() < minResponseTime) {
                minResponseTime = entry.getValue();
                selectedServer = entry.getKey();
            }
        }

        if (selectedServer != null) {
            serverResponseTimes.put(selectedServer, serverResponseTimes.get(selectedServer) + 1);
        }

        return selectedServer;
    }

}
