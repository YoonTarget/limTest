package test.com.kh.lim;

public class Test2 {
	
	public static void main(String[] args) {
		
		String str1 = "윤관현";
		
		str1 = "림째균";
		
		String str2 = "테이퍼드 핏 혐오자";
		 
//		System.out.println(str1 + " : " + str2);
		
		String str3 = str1 + str2;
		
		String[] arr = str3.split(" ");
		
		String str4 = "동해1물2과5 7백3두산4이 탕";
		
		String result1 = "동해물과백두산이탕";
		int result2 = 123457;
		
		for(int i = 0; i<str4.length(); i++) {
			
			System.out.println("i : " + i);
			
			System.out.println("ch : " + str4.charAt(i));
			
			System.out.println("in : " + str4.indexOf(str4.charAt(i)));
			
		}
			
		
		}
}
