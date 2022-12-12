import java.util.Scanner;

public class TertiarySearch {

	public static void main(String[] args) {
		
		
		char ans = ' ';
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[] { 1, 5, 7, 8, 10, 15, 16, 19, 21, 36, 65 };			

		do {

			for (int a : arr) {
				
				 System.out.print(a+" ");
				 
			}
			
			System.out.println("\nEnter number to search from the above list: ");
			int n = sc.nextInt();
			
			int searchedIndex = searchElementIndex(arr, 0, arr.length - 1, n);
			
			if (searchedIndex!=-1) {
				 
				System.out.println("Element found at index: "+searchedIndex);
				
			} else {
				
				System.out.println("Element not found!");
			}
			
			System.out.println("Do you wish to continue searching? If yes, press y or Y: ");
			ans = sc.next().charAt(0);
			
		}while(ans=='y' || ans == 'Y');
		
	}

	public static int searchElementIndex(int[] arr, int low, int high, int target) {

		if (low > high) {
			
			return -1;
		
		}

		// dividing the array into 3 parts
		
		int index = (high - low) / 3;
		
		// finding the first midpoint from the front
		
		int mid1 = low + index;
		
		// finding the second midpoint from the end
		
		int mid2 = high - index;
		 
		
		// base cases to return a value when recursive call is made
		
		if (target == arr[mid1]) {
			
			return mid1;
		
		}
		
		if (target == arr[mid2]) {
			
			return mid2;
		
		}

		// searching for the target value by comparing with the first and second midpoint 
		// and reducing search area for the value in the list
		
		if (target < arr[mid1]) {
			
			high = mid1 - 1;
		
		} else if (target > arr[mid1] && target < arr[mid2]) {
			
			low = mid1 + 1;
			high = mid2 - 1;
		
		} else if (target > arr[mid2]) {
			
			low = mid2 + 1;
		
		}
		
		return searchElementIndex(arr, low, high, target);
	}

}
