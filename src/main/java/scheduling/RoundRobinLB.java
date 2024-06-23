package scheduling;

import java.util.ArrayList;
import java.util.List;

public class RoundRobinLB {
    private List<String> servers;
    private int currentServerIndex;

    public RoundRobinLB(List<String> servers) {
        this.servers = new ArrayList<>(servers);
        this.currentServerIndex = 0;
    }

    public String getNextServer() {
        // Get the next server in the list
        String server = servers.get(currentServerIndex);

        // Update the current server index to the modulo of the current index and the size of the list
        currentServerIndex = (currentServerIndex + 1) % servers.size();

        return server;
    }
}
