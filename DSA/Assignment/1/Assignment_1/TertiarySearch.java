import java.util.Arrays;

public class TertiarySearch {

    int search(int array[], int l, int h, int value)
    {
        if(h >= l){
            int b1 = l + (h - l)/3;
            int b2 = h - (h - l)/3;

            if(array[b1]==value)
                return b1;
            if(array[b2]==value)
                return b2;
            if(value<array[b1])
                return search(array, l, b1-1, value);
            else if(array[b2]<value)
                return search(array, b2+1, h, value);
            else
                return search(array, b1+1, b2-1, value);
        }
        return -1;

    }

    public static void print(int num)
    {
        if(num!=-1){
            System.out.println("Element found at "+num+"th index");
        }else{
            System.out.println("Element not found");
        }
    }

    public static void main(String args[])
    {
        TertiarySearch tertiarySearch = new TertiarySearch();
        int[] givenArray = {1,6,7,8,34,65,88,99,101,767,890,900};
        System.out.println("Given Array:");
        System.out.println(Arrays.toString(givenArray));
        int n = givenArray.length;
        int find1 = 88;
        System.out.println("Find element " + find1);
        print(tertiarySearch.search(givenArray, 0, n-1, find1));
        System.out.println("-----------------");
        int find2 = 100;
        System.out.println("Find element " + find2);
        print(tertiarySearch.search(givenArray, 0, n-1, find2));
        System.out.println("-----------------");

    }
}
