package SlidingWindow;

//leetcode 137
public class GasStation {
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int departure = 0, destiny = 0;
        int volume = 0;
        int ans = -1;
        int stationNum = gas.length;
        for (departure = 0; departure < stationNum; departure = destiny + 1, destiny = departure) {
            volume = 0;
            while (volume + gas[destiny % stationNum] - cost[destiny % stationNum] >= 0) {
                if (destiny - departure + 1 == stationNum) return departure;
                volume += gas[destiny % stationNum] - cost[destiny % stationNum];
                destiny++;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        System.out.println(canCompleteCircuit(gas, cost));
    }
}