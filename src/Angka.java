
public class Angka {

	public static void main(String[] args) {
		 
//        cetakAngka(5);
//        System.out.println("\n");
//        cetakAngka2(5);
		Object obj1 = new Object();
		Object obj2 = new Object();
		
		System.out.println("result : "+(obj1==obj2)+" "+(obj1.equals(obj2))+" ");
		obj1 = obj2;
		System.out.println(obj1.equals(obj2));
    }
//	
//	public static void main(String[] args)
//    {
//        String obj  = "abcdefgh";
//        int length = obj.length();
//        char c[] = new char[length];
//        obj.getChars(0, length, c, 0);
//        CharArrayReader input1 = new CharArrayReader(c);
//        CharArrayReader input2 = new CharArrayReader(c, 1, 4);
//        int i;
//        int j;
//        try 
//        {
//            while((i = input1.read()) == (j = input2.read()))
//            {
//                System.out.print((char)i);
//            }
//               } 
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//    }
	
	
 //rekursif
    static void cetakAngka(int angka) { 
        if (angka <= 10) { 
            System.out.print(angka + " ");
            angka+=1;
            cetakAngka(angka);
 
        }
 
    }
    
    static void cetakAngka2(int angka) {
    	 
        if (angka <= 10) {
 
            System.out.print(angka + " ");
 
            cetakAngka(angka++);
 
        }
 
    }
}
