/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
	public static void main (String[] args) {
	    Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for(int test_case = 0;test_case<t;test_case++){
		    int s = sc.nextInt();
		    int n = sc.nextInt();
		    int m = sc.nextInt();
		    if(n<m){//Num of units to purchase for the first day is less than the num of units being sold
		        System.out.println("-1");
		    }else{
		        int units_left=0;
		        int days_purchased;//Useless
				int day = 1;
		        for(day = 1;day<s+1;day++){//For Each Day
		            //Considering we start from day 1 bcoz of Sundays divisibility test
		            if(day%7!=0){//Not sunday. Shop open
		                units_left = units_left + n;//Buy a box
		            }//Don't buy anything as it's sunday in else case
		            units_left = units_left - m;//Eat essential num of units
		            if(units_left<0){//There is a demand for food not yet fulfilled
		                System.out.println("-1");
		                day = 1000;//To Quit the loop.
		            }
		        }
				if(day>100){//1<=n,s<=50
					//Don't do anything. All is done
				}else{
					//If we reach here, Ishika survived
			        int units_needed=m*s;//Actually
			        if(units_needed%n==0){//We did not waste any. Yaay!!
			            System.out.println(String.valueOf(units_needed/n));
			        }else{//We wasted some so we needed to purchase extra. That's why a +1
			            System.out.println(String.valueOf((units_needed/n)+1));
			        }
				}
		    }
		}
	}
}
