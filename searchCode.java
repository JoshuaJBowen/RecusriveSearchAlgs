package workSpace;

import java.util.Arrays;

public class RecursiveSearchAlgs {

    // simplified user method for binary search
    // calls private recursive binary search
    public static boolean binarySearch(int[] data, int target) {
        return recurBinarySearch(data, target, 0, data.length-1, 1);
    }

    // private recursive binary search
    private static boolean recurBinarySearch(int[] data, int target, int low, int high, int count){
        if (low > high){                                                // interval empty -- no match
            System.out.println(count + " call(s) to binarySearch");
            return false;
        } else{
            int mid = (low + high)/2;
            if (target == data[mid]){                                   // value found
                System.out.println(count + " call(s) to binarySearch");
                return true;
            } else if (target < data[mid]){                             // recur left of the midpoint
                return recurBinarySearch(data, target, low, mid - 1, count + 1);
            } else{                                                     // recur right of the midpoint
                return recurBinarySearch(data, target, mid + 1, high, count + 1);
            }
        }
    }

    // simplified user method for ternary search
    // calls private recursive ternary search
    public static boolean ternarySearch(int[] data, int target){
        return recurTernarySearch(data, target, 0, data.length - 1, 1);
    }

    // private recursive ternary search
    private static boolean recurTernarySearch(int[] data, int target, int low, int high, int count){
        if (low > high){                                                // interval empty -- no match
            System.out.println(count + " call(s) to ternarySearch");
            return false;
        } else{
            int mid1 = (high - low)/3 + low;
            int mid2 = 2*(high - low)/3 + low;
            if (target == data[mid1] || target == data[mid2]){          // value found
                System.out.println(count + " call(s) to ternarySearch");
                return true;
            } else if (target < data[mid1]) {                           // recur in the bottom third
                return recurTernarySearch(data, target, low, mid1 - 1, count + 1);
            } else if (target > data[mid2]) {                           // recur in the top third
                return recurTernarySearch(data, target, mid2 + 1, high, count + 1);
            } else{                                                     // recur in the middle third
                return recurTernarySearch(data, target, mid1 + 1, mid2 - 1, count + 1);
            }
        }
    }

    // simplified user method for section-abstracted search
    // takes an additional parameter for the number of sections to divide input array into
    // calls private recursive section-abstracted search
    public static boolean search(int[] data, int target, int sections){
        if (sections < 2){
            throw new IllegalArgumentException("sections must be greater than 1");
        }
        return mySearch(data, target, sections, 0, data.length - 1, 1);
    }

    // private recursive abstracted search
    private static boolean mySearch(int[] data, int target, int sections, int low, int high, int count){
        if (low > high){                                                // interval empty -- no match
            // the return false statement is executed right after printing
            System.out.println(count + " call(s) to search with " + sections + " sections");
        } else{
            int[] mids = new int[sections - 1];                         // array to keep track of midpoints
            for (int i = 0; i < sections - 1; i++){
                int mid = (i + 1)*(high - low)/sections + low;
                mids[i] = mid;
                if (target == data[mid]){                               // value found
                    System.out.println(count + " call(s) to search with " + sections + " sections");
                    return true;
                } else if (target < data[mid]){
                    if (i == 0){                                        // recur in the bottom most section
                        return mySearch(data, target, sections, low, mid - 1, count + 1);
                    } else{                                             // recur between the closest two midpoints
                        return mySearch(data, target, sections, mids[i - 1] + 1, mid - 1, count + 1);
                    }
                } else if (i == sections - 2){                          // recur in the top most section
                    return mySearch(data, target, sections, mid + 1, high, count + 1);
                }
            }
        }
        return false;
    }
}
