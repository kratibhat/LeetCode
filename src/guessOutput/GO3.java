package guessOutput;

import java.util.Arrays;

public class GO3 {

public static void main(String[] args) {
    final int [] arr={1,2,3,4,5};
    //arr=new int[]{10,20,30,40,50}; // This will cause a compilation error because we cannot reassign a final variable
    //arr[0]=99; // This is allowed because we are not changing the reference variable arr, we are just changing the value at index 0 of the array that arr is pointing to

    //even if it is final we can change the value of the array but we cannot reassign the reference variable arr to point to another array
    arr[0]=99;
        System.out.println(Arrays.toString(arr));

}
}
