package util;

public class Utils{

	public static Integer converterParaInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return null;
		}
	}
	
	public static boolean verificarNumero(String str) {
	    try {
	        Integer.parseInt(str); 
	        return true;
	    } catch (NumberFormatException ex) {
	        return false;
	    }
	}
}
