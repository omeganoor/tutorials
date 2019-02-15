import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Faktorial {
	int a = 1;
	public Faktorial() {
		a = 3;
	}
	public Faktorial(int a) {
		this.a = a;
	}
	{
		a = 4;
	}
	
	public void print(){
		System.out.print(a);
	}
    public static void main(String[] args) {
    	int i = 2;
    	if (i++==--i){
    		System.out.println("0");
    	}else{
    		System.out.println("1");
    	}

    }
	private static int first(int a, int b) {
		if(b==0){
			return a;
		}else{
			return second(b, a-b); 
		}
		 
	}
	private static int second(int a, int b) {
		if(a==0){
			return b;
		}else{
			return first(b, a); 
		}		
	}
}

