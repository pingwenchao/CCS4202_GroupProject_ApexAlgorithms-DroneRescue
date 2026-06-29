# 🚁 Apex Algorithms - Drone Rescue Scheduling System

`https://img.shields.io/badge/Course-CCS4202_Design_and_Analysis_of_Algorithm-blue.svg`]()
`https://img.shields.io/badge/Language-Java_11%2B-orange.svg`]()

> **🌐 View Our Online Portfolio:** `https://WenchaoPing.github.io/CCS4202_GroupProject_ApexAlgorithms-DroneRescue/`

This project is a Java-based simulation for solving a critical emergency resource allocation problem, formally modeled as a **0/1 Knapsack Problem**. It cross-evaluates a fast, heuristic-based **Greedy Algorithm** against a globally optimal **Dynamic Programming** solution.

## 👥 Team Members (Apex Algorithms)
* **PING WENCHAO (226969)** - Project Lead & DP Developer
* **CHEN SHUAIPENG (228163)** - Co-Lead & Greedy Developer
* **PAN ZIHENG (226922)** - Data Modeler
* **SHENG JIAWEI (226994)** - Complexity Analyst
* **DAI QIANXIANG (218356)** - Portfolio Manager

---

## 📖 The Scenario

During a severe monsoon flash flood in Seri Kembangan, emergency response teams must deploy heavy-lift rescue drones with a strict payload limit of **30 kg** to deliver critical supplies from the UPM Bukit Ekspo unflooded hub. Each supply mission carries a specific weight and an intrinsic "survival priority" value.

The challenge is to select the exact combination of missions that maximizes the total survival priority value for each drone flight without exceeding its hardware weight capacity. This project demonstrates how different algorithmic paradigms lead to vastly different survival outcomes.

## 🧮 Problem Definition: 0/1 Knapsack

This scenario is mathematically modeled as a classic 0/1 Knapsack problem:

- **Items**: The localized rescue missions, each carrying a `weight` and a `priorityValue`.
- **Knapsack**: The heavy-lift drone, constrained by a maximum capacity (`maxPayload`).
- **Objective**: Maximize the sum of `priorityValue` for the chosen missions, subject to the absolute constraint that the sum of their `weight` $\le$ `maxPayload`.

## ⚙️ Algorithms Implemented

Two distinct algorithmic paradigms are implemented and contrasted:

### 1. Greedy Algorithm (`GreedyScheduler.java`)
- **Strategy**: Sorts all missions in descending order based on their value-to-weight density ratio ($v_i / w_i$). It iterates through the sorted list, packing missions sequentially as long as they fit within the remaining payload capacity.
- **Characteristics**: Executes rapidly but focuses purely on local optima. It easily falls into the "Greedy Trap" by selecting bulky high-ratio items, failing to find the global optimal solution.
- **Time Complexity**: $O(n \log n)$, dominated by the sorting mechanism.

### 2. Dynamic Programming (`DPScheduler.java`)
- **Strategy**: Utilizes a bottom-up 2D tabulation matrix (`dp[i][w]`). It evaluates overlapping subproblems using a strict recurrence relation, evaluating every possible capacity state.
- **Characteristics**: Systematically bypasses heuristic traps and mathematically guarantees the absolute global optimal solution.
- **Time Complexity**: $O(n \cdot W)$, where $n$ is the number of missions and $W$ is the maximum payload capacity constraints.

## 📂 Project Structure

```text
.
└── src
    └── apexalgorithms
        ├── Main.java            # Main entry point, runs the simulation and contrasts metrics.
        ├── Mission.java         # Entity data model for a localized rescue mission.
        ├── GreedyScheduler.java # Implements the heuristic Greedy approach.
        └── DPScheduler.java     # Implements the optimal DP approach.

```

## 🚀 How to Compile and Run

1. Open a terminal and navigate to the project's `src` directory.
2. Compile the Java source files:
```bash
javac apexalgorithms/*.java

```


3. Execute the main program:
```bash
java apexalgorithms.Main

```



## 📊 Example Output

The program output empirically demonstrates the "Greedy Trap". The Greedy algorithm selects the mission with the highest local density first, completely exhausting capacity and scoring only **200**. Dynamic Programming explores all states and finds the true global optimal combination, scoring **270**.

```text
==================================================
  SERI KEMBANGAN DRONE RESCUE SCHEDULING SYSTEM  
==================================================
Drone Max Payload Limit: 30 kg

>>> GREEDY ALGORITHM (Heuristic Baseline)
    - [M1] Klinik Kesihatan (Weight: 16 kg, Value: 160)
    - [M5] South City Plaza (Weight: 8 kg, Value: 40)
    -------------------------
    Total Weight Used : 24 kg
    Total Value Score : 200
    Execution Time    : 0.7114 ms

>>> DYNAMIC PROGRAMMING (Global Optimal)
    - [M3] Sungai Kuyoh (Weight: 15 kg, Value: 135)
    - [M2] Taman Sri Serdang (Weight: 15 kg, Value: 135)
    -------------------------
    Total Weight Used : 30 kg
    Total Value Score : 270
    Execution Time    : 0.0362 ms

```
