package com.vigil.tony;

public class BinarySearch {

	public static void main(String[] args) {
		
		
		String[] my_list = {"1", "3", "5", "7", "9"};
		
		System.out.println(binarySearch(my_list, "3"));
		System.out.println(binarySearch(my_list, "-1"));
	}
	
	private static String binarySearch(String[] list, String item) {
		int low=0;
		int high = list.length-1;
		
		while(low<=high) {
			//System.out.println("low: "+low+" high: "+high);
			int mid = (low+high)/2;
			String guess = list[mid];
			if(guess.equals(item)) {
				return guess;
			} else if(guess.compareTo(item)<0) {
				low = mid+1;
			} else {
				high = mid-1;
			}
			
		}
		
		//item does not exist in array
		return null;
	}
}
