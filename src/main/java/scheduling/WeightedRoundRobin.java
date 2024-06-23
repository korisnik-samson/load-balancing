package scheduling;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class WeightedRoundRobin {
    private List<Server> servers;
    private Integer[] cumulativeWeights;
    private Integer totalWeight;
    private int currentServerIndex;
    private Random random;

    public WeightedRoundRobin(List<Server> servers) {
        this.servers = new ArrayList<>(servers);
        this.totalWeight = calculateTotalWeight(servers);
        this.cumulativeWeights = calculateCumulativeWeights(servers);
        this.currentServerIndex = 0;
        this.random = new Random();
    }

    private Integer[] calculateCumulativeWeights(List<Server> servers) {
        Integer[] cumulativeWeights = new Integer[servers.size()];

        cumulativeWeights[0] = servers.getFirst().getWeight();

        for (int i = 1; i < servers.size(); i++) {
            cumulativeWeights[i] = cumulativeWeights[i - 1] + servers.get(i).getWeight();
        }

        return cumulativeWeights;
    }

    private Integer calculateTotalWeight(List<Server> servers) {
        Integer totalWeights = 0;

        for (Server server : servers) {
            totalWeights += server.getWeight();
        }

        return totalWeights;
    }

    public Server getNextServer() {
        Integer randomWeight = random.nextInt(totalWeight) + 1;

        for (int i = 0; i < servers.size(); i++) {
            if (randomWeight < cumulativeWeights[i]) {
                return servers.get(i);
            }
        }

        return null;
    }

    static class Server {
        private String name;
        private Integer weight;

        public Server(String name, Integer weight) {
            this.name = name;
            this.weight = weight;
        }

        public String getName() {
            return name;
        }

        public Integer getWeight() {
            return weight;
        }
    }
}
