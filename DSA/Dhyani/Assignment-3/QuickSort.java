public class QuickSort {

	public static void main(String[] args) {
		int arr []= new int[] {7,234,2,5,9,12,87,3,8,23,44,6,6,78,45};
		
		// Printing Input Array
		
		System.out.print("Input Array : ");
		for(int i=0; i<arr.length; i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println();
		
		// Calling Quick Sort Method for Ascending order
		
		ascQuickSort(arr, 0, arr.length-1);
		
		// Printing Ascending Order
		
		System.out.print("\nAscending order: ");
		for(int i=0; i<arr.length; i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println();
		
		// Calling Quick Sort Method for Descending order
		
		descQuickSort(arr, 0, arr.length-1);
		
		// Printing Descending Order
		
		System.out.print("\nDescending order: ");
		for(int i=0; i<arr.length; i++){
			System.out.print(arr[i]+" ");
		}
	
	}
	
	public static void ascQuickSort(int arr[], int low, int high){
	    
        if(low == high || low > high){
            return;
        }
        
        int leftPtr = low+1;
        int rightPtr = high;
        int pivot = low;
        
        if(leftPtr < rightPtr){
    
            while(leftPtr!=rightPtr) {
                
                while(arr[rightPtr] >= arr[pivot] && rightPtr>leftPtr){
                    rightPtr--;
                }
                
                while(arr[leftPtr] <= arr[pivot] && leftPtr<rightPtr){
                    leftPtr++;
                }
                
                // Swapping leftPtr element and rightPtr element when indexes for both the leftPtr and rightPtr are different
                
                if(leftPtr!=rightPtr) {
                    
                    int temp = arr[leftPtr];
                    arr[leftPtr] = arr[rightPtr];
                    arr[rightPtr] = temp;
                    
                }                          
                
            }
            
        }
        
        // Swapping leftPtr element with pivot element when indexes for both the leftPtr and rightPtr are same 
        // and pivot element is greater than leftPtr element
        
        if(leftPtr == rightPtr && arr[pivot] >= arr[leftPtr]){
                    
            int temp = arr[pivot];
            arr[pivot] = arr[leftPtr];
            arr[leftPtr] = temp;
            pivot = leftPtr;
                    
        }
       
        ascQuickSort(arr, low, pivot-1);
        ascQuickSort(arr, pivot+1, high);
        
    }
    
     public static void descQuickSort(int arr[], int low, int high){
    
        if(low == high || low>high){
            return;
        }
        
        int leftPtr = low+1;
        int rightPtr = high;
        int pivot = low;
      
        if(leftPtr < rightPtr){
    
            while(leftPtr!=rightPtr) {
                
                while(arr[rightPtr] <= arr[pivot] && rightPtr>leftPtr){
                    rightPtr--;
                }
                
                while(arr[leftPtr] >= arr[pivot] && leftPtr<rightPtr){
                    leftPtr++;
                }
                
                // Swapping leftPtr element and rightPtr element when indexes for both the leftPtr and rightPtr are different
                
                if(leftPtr != rightPtr) {
                    
                    int temp = arr[leftPtr];
                    arr[leftPtr] = arr[rightPtr];
                    arr[rightPtr] = temp;
                                      
                }
            }            
        }
        
        // Swapping leftPtr element with pivot element when indexes for both the leftPtr and rightPtr are same 
        // and pivot element is smaller than leftPtr element
        
        if(leftPtr == rightPtr && arr[pivot] <= arr[leftPtr]){
                    
            int temp = arr[pivot];
            arr[pivot] = arr[leftPtr];
            arr[leftPtr] = temp;
            pivot = leftPtr;
                        
        }                
                
        descQuickSort(arr, low, pivot-1);
        descQuickSort(arr, pivot+1, high);
        
        
    }

	

}
