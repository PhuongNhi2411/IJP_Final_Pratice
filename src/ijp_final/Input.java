package ijp_final;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Input {
	public static Scanner sc = new Scanner(System.in);

	public static String inputString(String myPrint) {
		System.out.println(myPrint);
		return sc.nextLine();
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
					date = sdf.parse(sc.nextLine());
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
