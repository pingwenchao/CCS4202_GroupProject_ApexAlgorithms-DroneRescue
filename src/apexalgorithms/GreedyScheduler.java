package apexalgorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Scheduler implementing the Greedy Algorithm paradigm.
 * Sorts missions by local value density (Value / Weight) to make fast, heuristic decisions.
 */
public class GreedyScheduler {

    /**
     * Executes the greedy selection process.
     * * @param missions   The list of available rescue missions.
     * @param maxPayload The physical payload limit of the drone.
     * @return A list of selected missions.
     */
    public List<Mission> schedule(List<Mission> missions, int maxPayload) {
        // Step 1: Clone the list to avoid modifying the original dataset
        List<Mission> sortedMissions = new ArrayList<>(missions);

        // Step 2: Sort missions in descending order based on their Density Ratio
        sortedMissions.sort((m1, m2) -> Double.compare(m2.getDensityRatio(), m1.getDensityRatio()));

        List<Mission> selectedMissions = new ArrayList<>();
        int currentWeight = 0;

        // Step 3: Sequentially pack missions until capacity is reached
        for (Mission mission : sortedMissions) {
            if (currentWeight + mission.getWeight() <= maxPayload) {
                selectedMissions.add(mission);
                currentWeight += mission.getWeight();
            }
        }

        return selectedMissions;
    }
}