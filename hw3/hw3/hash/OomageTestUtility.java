package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int bucketNum;
        int [] buckets = new int[M];
        for (int i = 0; i < M; i++) {
            buckets[i] = 0;
        }
        for (Oomage item : oomages) {
            bucketNum = (item.hashCode() & 0x7FFFFFFF) % M;
            buckets[bucketNum] += 1;
        }
        int i = 0;
        while (i < M) {
            if (buckets[i] < oomages.size() / 50 || buckets[i] > oomages.size() / 2.5) {
                return false;
            }
            i++;
        }
        return true;
    }
}
