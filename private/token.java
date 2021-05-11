package study;

import java.util.*;

public class token {
	public static void main(String[] args) {
    
    	   String keyword = " soccer, baseball, basketball ";
           splitT(keyword);
           
           System.out.println(" 		");
           
           stringTokenizerT(keyword);
     	}
     
     //SplitTest 메소드
     public static void splitT(String str) {
     	System.out.println("==== splitTest() Start ====");
        String split[] = str.split(",");
        
        int index = 0;
        for(String data : split) {
        	System.out.println(index + "번째 : " + data);
            index++;
        }
        System.out.println("==== splitTest() End ====");
   }
   
     //stringTokenizerTest 메소드
     public static void stringTokenizerT(String str) {
     	System.out.println("==== stringTokenizerTest() Start ====");
        StringTokenizer st = new StringTokenizer(str, ",");
        
        for(int i = 0; st.hasMoreTokens(); i++) { // 리턴할 다음 토큰이 남아있다면 true, 없으면 false (리턴타입 int)
        	System.out.println( i + "번째 : " + st.nextToken()); // 다음토큰을 리턴한다. (리턴타입 String)
        }
        
        System.out.println("==== stringTokenizerTest() End ====");
        
      }
    }
           
           
