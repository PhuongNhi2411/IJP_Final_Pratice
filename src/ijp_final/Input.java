package ijp_final;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Input {
	public static Scanner sc = new Scanner(System.in);
	
//	public static void main(String[] args){
//		inputPhoneNumber("Vui lòng nhập số điện thoại:");
//	}

	public static String inputString(String myPrint,int length) {
		System.out.println(myPrint +"(giới hạn "+length+" ký tự)");
		String input =sc.nextLine();
		input = input.trim();		
	    if (input.length() > length) {
	    	System.out.println("Nhập quá giới "+length+" ký tự. Vui lòng nhập lại");
	    }
		return input;
	}

	public static int inputInt(String myPrint) {
		try {
			System.out.println(myPrint);
			return Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Nhập sai định dạng số. Vui lòng nhập lại");
			return inputInt(myPrint);
		}
	}

	public static double inputDouble(String myPrint) {
		try {
			System.out.println(myPrint);
			return Double.parseDouble(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Nhập sai định dạng số. Vui lòng nhập lại");
			return inputDouble(myPrint);
		}
	}

	public static long inputLong(String myPrint) {
		try {
			System.out.println(myPrint);
			return Long.parseLong(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Nhập sai định dạng số. Vui lòng nhập lại");
			return inputLong(myPrint);
		}
	}	

	
	public static String inputEmail(String myPrint) {	
		System.out.println(myPrint);
		String email = sc.nextLine();
		email = email.trim();
		if(!email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")){
			
			System.out.println("Nhập sai định dạng email. Vui lòng nhập lại");
			return inputEmail(myPrint);
		}
		return email;		
	}
	
	public static String inputPhoneNumber(String myPrint) {  
	    System.out.println(myPrint);
		String input = sc.nextLine();
		input = input.trim();

		// Kiểm tra xem số điện thoại có đúng 10 chữ số hay không
	    if (input.length() != 10) {
	    	System.out.println("Nhập sai định dạng số điện thoại. Vui lòng nhập lại");
			return inputPhoneNumber(myPrint);
	    }

	    // Kiểm tra xem tất cả các ký tự trong số điện thoại có phải là chữ số hay không
	    for (int i = 0; i < input.length(); i++) {
	        if (!Character.isDigit(input.charAt(i))) {
	        	System.out.println("Nhập sai định dạng số điện thoại. Vui lòng nhập lại");
				return inputPhoneNumber(myPrint);
	        }
	    }
		return input;	
	}
	
	public static String inputSTK(String myPrint) {  
	    System.out.println(myPrint);
		String input = sc.nextLine();
		input = input.trim().toUpperCase();

		// Kiểm tra xem có đúng 7 chữ số hay không
	    if (input.length() != 7) {
	    	System.out.println("Nhập sai định dạng số tài khoản. Vui lòng nhập lại");
			return inputSTK(myPrint);
	    }
	    
	    if ( input.charAt(0)!='S') {
        	System.out.println("Nhập sai định dạng số tài khoản. Vui lòng nhập lại");
			return inputSTK(myPrint);
        }
	    
	    if (input.charAt(1)!='T') {
        	System.out.println("Nhập sai định dạng số tài khoản. Vui lòng nhập lại");
			return inputSTK(myPrint);
        }
	    
	    if (input.charAt(2)!='K') {
        	System.out.println("Nhập sai định dạng số tài khoản. Vui lòng nhập lại");
			return inputSTK(myPrint);
        }
	  
	    for (int i = 3; i < input.length(); i++) {
	        if (!Character.isDigit(input.charAt(i))) {
	        	System.out.println("Nhập sai định dạng số tài khoản. Vui lòng nhập lại");
				return inputSTK(myPrint);
	        }
	    }
		return input;	
	}

	public static Date inputDate(String myPrint) {
		Date date = null;
		boolean flag = false;
		do {
			try {
				System.out.println(myPrint);
				// "Nhập hạn sử dụng (yyyy-MM-dd): "
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				// format ngày phải giống format yêu cầu người dùng nhập
				// MM: định dạng tháng trong java (nhớ ghi hoa, nếu không sẽ nhầm với minute)
				sdf.setLenient(false);
				// true: tự động covert ngày nhập sang 1 ngày khác hợp lệ, ví dụ 30/02 -> 02/03
				// false: bắt buộc nhập đúng
				try {
					String input = sc.nextLine();
					if (input.contains("-")) {
	                    sdf.applyPattern("yyyy-MM-dd");
	                } else if (input.contains("/")) {
	                    sdf.applyPattern("yyyy/MM/dd");
	                } else {
	                    throw new IllegalArgumentException();
	                }
					date = sdf.parse(input);
					flag = true;
					return date;
				} catch (ParseException e) {
					e.printStackTrace();
					System.out.println("Nhập không đúng định dạng vui lòng nhập lại");
				}
			} catch (IllegalArgumentException e) {
				System.out.println("Nhập không đúng định dạng vui lòng nhập lại");

			}
		} while (flag == false);
		return date;
	}

	public static double inputDoubleDoWhile(String myPrint) {
		double number = 0;
		boolean flag = false;
		do {
			try {
				System.out.println(myPrint);
				number = Double.parseDouble(sc.nextLine());
				flag = true;
			} catch (NumberFormatException e) {
				System.out.println("Nhập sai định dạng số. Vui lòng nhập lại");
			}
		} while (!flag);
		return number;
	}
}
