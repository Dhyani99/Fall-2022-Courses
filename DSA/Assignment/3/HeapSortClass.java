import java.util.Arrays;

public class HeapSortClass {

    public static void Heapify(int[] A, int i){
        int l = i*2;
        int r = i*2 + 1;
        int minimum = i;
        if(l <= A[0] && A[l] < A[minimum])
            minimum = l;

        if(r <= A[0] && A[r] < A[minimum])
            minimum = r;

        if(minimum != i) {
            int temp = A[minimum];
            A[minimum] = A[i];
            A[i] = temp;
            Heapify(A, minimum);
        }
    }

    public static void BuildHeap(int[] A){

        int curr_parent = A[0]/2;

        while(curr_parent>0){
            Heapify(A, curr_parent);
            curr_parent -= 1;
        }

    }

    public static void HeapSort(int[] A){
        int length = A[0];
        for(int i = length; i > 1; i--){
            int temp = A[i];
            A[i] = A[1];
            A[1] = temp;
            A[0] -= 1;
            Heapify(A, 1);
        }
        A[0] = length;
    }

    public static void main(String[] args){
        int[] A = {15,2,54,3,67,5,4,56,30,45,23,12,24,50,32,19};
        System.out.println("Note first element in the array will give the size of the array/heap");
        System.out.println("Input array: " + Arrays.toString(A));

        BuildHeap(A);

        System.out.println("After converting into heap: " + Arrays.toString(A));

        HeapSort(A);

        System.out.println("After heap sort: " + Arrays.toString(A));
    }
}
