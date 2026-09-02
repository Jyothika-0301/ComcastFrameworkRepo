
public class javacode {
	public static void main(String[] args) {
		String s = "fgy78899";
		String res="";
		for(int i=0;i<s.length();i++)
		{
			char ch = s.charAt(i);
			if(ch>='0' && ch<='9')
			{
				res+=ch;
			}
			
		}
		System.out.println(res);
		
	}
	

}
