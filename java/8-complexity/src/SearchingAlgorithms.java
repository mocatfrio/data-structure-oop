public class SearchingAlgorithms {

    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) return -1;
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) return mid;
        if (arr[mid] < target) return binarySearchRecursive(arr, target, mid + 1, right);
        return binarySearchRecursive(arr, target, left, mid - 1);
    }

    public static int jumpSearch(int[] arr, int target) {
        int n = arr.length;
        int step = (int) Math.sqrt(n);
        int prev = 0;

        while (arr[Math.min(step, n) - 1] < target) {
            prev = step;
            step += (int) Math.sqrt(n);
            if (prev >= n) return -1;
        }

        while (arr[prev] < target) {
            prev++;
            if (prev == Math.min(step, n)) return -1;
        }

        if (arr[prev] == target) return prev;
        return -1;
    }

    public static int interpolationSearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;

        while (low <= high && target >= arr[low] && target <= arr[high]) {
            if (low == high) {
                if (arr[low] == target) return low;
                return -1;
            }

            int pos = low + ((target - arr[low]) * (high - low)) / (arr[high] - arr[low]);

            if (arr[pos] == target) return pos;
            if (arr[pos] < target) low = pos + 1;
            else high = pos - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("=== SEARCHING ALGORITHMS ===\n");

        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        int target = 13;

        System.out.println("Array: [1, 3, 5, 7, 9, 11, 13, 15, 17, 19]");
        System.out.println("Target: " + target + "\n");

        System.out.println("Linear Search: index " + linearSearch(arr, target));
        System.out.println("Binary Search: index " + binarySearch(arr, target));
        System.out.println("Binary Search (Recursive): index " + binarySearchRecursive(arr, target, 0, arr.length - 1));
        System.out.println("Jump Search: index " + jumpSearch(arr, target));
        System.out.println("Interpolation Search: index " + interpolationSearch(arr, target));

        System.out.println("\nSearching for 100 (not in array):");
        System.out.println("Linear Search: " + linearSearch(arr, 100));
        System.out.println("Binary Search: " + binarySearch(arr, 100));
    }
}
