package apexalgorithms;

/**
 * Entity class representing a single disaster rescue mission.
 * Implements standard POJO structure with attributes for the 0/1 Knapsack problem.
 */
public class Mission {
    private String missionId;
    private String locationName;
    private int weight;         // The payload/battery cost of the mission (in kg)
    private int priorityValue;  // The intrinsic survival value of the mission

    public Mission(String missionId, String locationName, int weight, int priorityValue) {
        this.missionId = missionId;
        this.locationName = locationName;
        this.weight = weight;
        this.priorityValue = priorityValue;
    }

    // Getters
    public String getMissionId() { return missionId; }
    public String getLocationName() { return locationName; }
    public int getWeight() { return weight; }
    public int getPriorityValue() { return priorityValue; }

    /**
     * Calculates the value-to-weight ratio used for the Greedy Algorithm.
     */
    public double getDensityRatio() {
        return (double) priorityValue / weight;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (Weight: %d kg, Value: %d)",
                missionId, locationName, weight, priorityValue);
    }
}