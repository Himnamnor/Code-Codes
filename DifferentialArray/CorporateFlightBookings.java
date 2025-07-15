package DifferentialArray;

public class CorporateFlightBookings {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] ans = new int[n];
        int[] d = new int[n + 1];
        for (int[] booking : bookings) {
            int first = booking[0];
            int last = booking[1];
            int seats = booking[2];
            d[first - 1] += seats;
            d[last] -= seats;
        }
        ans[0] = d[0];
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] + d[i];
        }
        return ans;
    }
}

