package co.yedam.test;

//import co.yedam.test.common.SHA256;

public class App 
{
    public static void main( String[] args )
    {
//    	SHA256 sha256 = new SHA256();
//    	String stx = "1234";
//    	String cyperText = sha256.encrypt(stx);
//    	System.out.println(cyperText);
    	MainMenu menu = new MainMenu();
    	menu.run();
    }
}
