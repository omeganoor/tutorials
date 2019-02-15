import java.util.Stack;

public class Balancing {

//    private static final char GREATHER_THAN_PAREN    = '<';
//    private static final char LOWER_THAN_PAREN    = '>';


    public static boolean isBalanced(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if      (s.charAt(i) == GREATHER_THAN_PAREN)   stack.push(GREATHER_THAN_PAREN);
            else if (s.charAt(i) == LOWER_THAN_PAREN) {
                if (stack.isEmpty())        return false;
                if (stack.pop() != GREATHER_THAN_PAREN) return false;
            }
        }
        return stack.isEmpty();
    }
	public static void main(String[] args) {
		String bracket = "<><>";
		String []expressions = {"<>","<>>>>"};
		int [] maxReplacements = {2,3};
//		System.out.println(isBalanced(bracket));
		int [] result = new int[expressions.length];
		result = balancedOrNot(expressions, maxReplacements);
		for (int i : result) {
			System.out.println(i);
		}
	}
	
	public static int[] balancedOrNot(String[] expressions, int[] maxReplacements) {
		Stack<Character> stack = new Stack<Character>();
		char rightSign = '<';
		char leftSign = '>';
		int [] balanceRes = new int[expressions.length]; 
		String data = "";
		int count = 0;
		int index = 0;
		for (String exp : expressions) {
			for (char expChar : exp.toCharArray()) {				
				if (expChar == rightSign) {
					stack.push(expChar);
					System.out.println(stack.peek());
				} else if (expChar == leftSign) {
					if (stack.isEmpty()){
						count++;
					}else{
						stack.pop();
					}
				}
			}
			if (count<=maxReplacements[index]) {
				balanceRes[index] = 1;
			} else {
				balanceRes[index] = 0;
			}
			index++;
		}
//		for (int i=0; i<expressions.length; i++){
//			data = expressions[i];
//			for (int j = 0; j < data.length(); j++) {
//	            if (data.charAt(j) == rightSign) {
//	            	stack.push(rightSign);	            
//	            } else if (data.charAt(i) == leftSign) {
//	                if (stack.isEmpty()||stack.pop() != rightSign){
//	                	count++;
//	        			System.out.println(count);
//	                }else{
//	                	System.out.println(data.charAt(j));
//	                }
//	            }
//	        }
//			if (count<=maxReplacements[i]) {
//				balanceRes[i] = 1;
//			}else{
//				balanceRes[i] = 0; 
//			}
//		}
		
		return balanceRes;
        
    }

	private static final char LOWER_THAN_PAREN      = '<';
	private static final char GREATHER_THAN_PAREN   = '>';
	
	public static int[] isBalanced(String[] expressions, int[] maxReplacements) {		
		int [] balanceRes = new int[expressions.length]; 
		String data = "";
		int index = 0;
		for (String exp : expressions) {			
			if (compareChar(exp)<=maxReplacements[index]) {
				balanceRes[index] = 1;
			} else {
				balanceRes[index] = 0;
			}
			index++;
		}		
		return balanceRes;        
    }
	
	public static int compareChar(String exp) {
		Stack<Character> stack = new Stack<Character>();
		int count = 0;
		for (char expChar : exp.toCharArray()) {				
			if (expChar == LOWER_THAN_PAREN) {
				stack.push(expChar);
			} else if (expChar == GREATHER_THAN_PAREN) {
				if (stack.isEmpty()){
					count++;
				}else{
					stack.pop();
				}
			}
		}
		return count;
	}
}
