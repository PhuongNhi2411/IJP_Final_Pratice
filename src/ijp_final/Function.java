package ijp_final;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Function {
	public static Scanner sc = new Scanner(System.in);
	public static Connection conn;
	public static ArrayList<BankBook> bankBooks = new ArrayList<>();
	
	
	public static void main(String[] args){
//		inputPhoneNumber("Vui lòng nhập số điện thoại:");
//		BankBook bankBook = inputBankBook();
//		
//		insert(bankBook);
//		bankBooks.add(bankBook);
		
		getBankBooksByDate(inputNgay());
	}
	


	/**
	 * Hàm thêm mới dữ liệu vào bảng
	 */
//	public static void insertEmployee(Scanner sc) {
//		BankBook emp = inputEmployee();
//		insert(emp);
//		employees.add(emp);
//	}
//
	public static BankBook inputBankBook() {
		BankBook bankBook = new BankBook();
		System.out.println("-------Thêm nhân viên----------");
		bankBook.maSoTK = Input.inputSTK("Nhập SoTK:");
		bankBook.hoTen = Input.inputString("Nhập Họ tên:",250);		
		bankBook.cmnd = Input.inputString("Nhập Họ tên:",10);
		bankBook.diaChi = Input.inputString("Nhập Họ tên:",250);
		bankBook.soDienThoai = Input.inputPhoneNumber("Nhập Số điện thoại:");		
		bankBook.ngayGuiTK = Input.inputDate("Nhập ngày gửi:");
		bankBook.soTien = (float) Input.inputDouble("Nhập số tiền:");			
		return bankBook;
	}
	
	public static Date[] inputNgay() {
	
	
		Date batdau = Input.inputDate("Nhập ngày bắt đầu:");
		Date ketthuc = Input.inputDate("Nhập ngày kết thúc:");
		int   comparison = ketthuc.compareTo(batdau);	       

        
        if(comparison<0) {
            System.out.println("vui lòng nhập ngay bắt đầu nhỏ hơn ngày kết thúc");
        	inputNgay();
        }     
        
        Date[] dates = new Date[2];
        dates[0] = batdau;
        dates[1] = ketthuc;

        return dates;
		
	}
	
	public static BankBook mapResultSetToObject(ResultSet rs) throws SQLException {
		BankBook bankBook = new BankBook();

		bankBook.maSoTK = rs.getString("maSoTK");
//		bankBook.hoTen = rs.inputString("Nhập Họ tên:",250);		
//		bankBook.cmnd = rs.inputString("Nhập Họ tên:",10);
//		bankBook.diaChi = rs.inputString("Nhập Họ tên:",250);
//		bankBook.soDienThoai = rs.inputPhoneNumber("Nhập Số điện thoại:");		
//		bankBook.ngayGuiTK = rs.inputDate("Nhập ngày gửi:");
//		bankBook.soTien = (float) rs.inputDouble("Nhập số tiền:");
//		
//		prstmt.setString(1, bankBook.maSoTK);
//		prstmt.setString(2, bankBook.hoTen);
//		prstmt.setString(3, bankBook.cmnd);
//		prstmt.setString(4, bankBook.diaChi);
//		prstmt.setString(5, bankBook.soDienThoai);	
//		prstmt.setDate(6, java.sql.Date.valueOf(converToSqlDateString(bankBook.ngayGuiTK)));
//		prstmt.setDouble(7, bankBook.soTien);
        return bankBook;
    }
	
	public static void getBankBooksByDate(Date[] input) {
		List<BankBook> emps = new ArrayList<>();
		conn = ConnectionUtil.getConnection();
	
		String sql = "SELECT * FROM BankBooks where NgayGuiTK between ? and ? ";
		try {
			
			PreparedStatement prstmt = conn.prepareStatement(sql);
			prstmt.setDate(1, java.sql.Date.valueOf(converToSqlDateString(input[0])));
			prstmt.setDate(2, java.sql.Date.valueOf(converToSqlDateString(input[1])));
			
			ResultSet rs = prstmt.executeQuery();
			while (rs.next()) {
				BankBook bankBook = new BankBook();
				bankBook = mapResultSetToObject(rs);
				System.out.println(bankBook.toString());
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Xảy ra lỗi khi truy vấn dữ liệu");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static String insert(BankBook bankBook) {
		conn = ConnectionUtil.getConnection();

		String sql = "INSERT INTO BankBooks (MaSoTK,HoTen,CMND,DiaChi,SoDienThoai,NgayGuiTK,SoTien) VALUES (" + generateQuestionMarks(7) + ")";

		// Insert dữ liệu vào sql
		try {
			PreparedStatement prstmt = conn.prepareStatement(sql);
			prstmt.setString(1, bankBook.maSoTK);
			prstmt.setString(2, bankBook.hoTen);
			prstmt.setString(3, bankBook.cmnd);
			prstmt.setString(4, bankBook.diaChi);
			prstmt.setString(5, bankBook.soDienThoai);	
			prstmt.setDate(6, java.sql.Date.valueOf(converToSqlDateString(bankBook.ngayGuiTK)));
			prstmt.setDouble(7, bankBook.soTien);
			prstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Insert thất bại";
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "Insert thành công";
	}
	
	
	public static String converToSqlDateString(Date input){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    return formatter.format(input);
	}
//	/**
//	 * Hàm lấy toàn bộ dữ liệu
//	 */
//	public static void getAllEmployee() {
//		List<BankBook> emps = new ArrayList<>();
//		conn = ConnectionUtil.getConnection();
//
//		String sql = "SELECT * FROM EMPLOYEE";
//		try {
//			PreparedStatement stt = conn.prepareStatement(sql);
//			ResultSet rs = stt.executeQuery();
//			while (rs.next()) {
//				BankBook emp = new BankBook();
//				emp.id = rs.getLong("ID");
//				emp.name = rs.getString("Name");
//				emp.age = rs.getInt("Age");
//				emp.address = rs.getString("Address");
//				emp.salary = rs.getDouble("salary");
//				emp.sumTimeWork = rs.getInt("sumTimeWork");
//				emps.add(emp);
//
//			}
//			// Hàm hiển thị
//			for (int i = 0; i < emps.size(); i++) {
//				System.out.println(emps.get(i).toString());
//
//			}
//
//		} catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			System.out.println("Xảy ra lỗi khi truy vấn dữ liệu");
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//
//	/**
//	 * Hàm tìm kiếm thông tin theo tên
//	 */
//	public static void getEmployeeByName() {
//		conn = ConnectionUtil.getConnection();
//		List<BankBook> emps = new ArrayList<>();
//		String name = Input.inputString("Nhập tên nhân viên muốn tìm kiếm");
//		String sql = "SELECT * FROM EMPLOYEE WHERE Name=?";
//		try {
//			PreparedStatement prst = conn.prepareStatement(sql);
//
//			prst.setString(1, name);
//			ResultSet rs = prst.executeQuery();
//			while (rs.next()) {
//				Employee emp = new Employee();
//				emp.id = rs.getLong("ID");
//				emp.name = rs.getString("Name");
//				emp.age = rs.getInt("Age");
//				emp.address = rs.getString("Address");
//				emp.salary = rs.getDouble("salary");
//				emp.sumTimeWork = rs.getInt("sumTimeWork");
//				emps.add(emp);
//
//			}
//			// Hàm hiển thị
//			for (int i = 0; i < emps.size(); i++) {
//				System.out.println(emps.get(i).toString());
//			}
//			if (emps.size() == 0) {
//				System.out.println("Không tìm thấy tên nhân viên");
//				System.out.println("---------------------------");
//
//			}
//
//		} catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			System.out.println("Xảy ra lỗi khi truy vấn dữ liệu");
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//
//	/**
//	 * Hàm xóa thông tin theo tên
//	 */
//	public static void deleteEmployeeByName() {
//		conn = ConnectionUtil.getConnection();
//		String name = Input.inputString("Nhập tên nhân viên muốn xóa");
//		String sql = "DELETE FROM EMPLOYEE WHERE Name=?";
//		try {
//			PreparedStatement prst = conn.prepareStatement(sql);
//			prst.setString(1, name);
//			int rows = prst.executeUpdate();
//			if (rows == 0) {
//				System.out.println("Không tìm thấy tên nhân viên");
//
//			} else
//				System.out.println("Đã xóa " + rows + " dòng tương ứng");
//
//		} catch (
//
//		SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			System.out.println("Xảy ra lỗi khi truy vấn dữ liệu");
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//
//	/**
//	 * Hàm cập nhật lương, tổng giờ làm nhân viên theo tên
//	 */
//	public static void updateEmployeeByName() {
//		conn = ConnectionUtil.getConnection();
//		String name = Input.inputString("Nhập tên nhân viên muốn cập nhật");
//		// Kiểm tra xem nhân viên có tồn tại trong database không
//		String sql = "SELECT * FROM EMPLOYEE WHERE Name=?";
//		try {
//			PreparedStatement prst = conn.prepareStatement(sql);
//			prst.setString(1, name);
//			ResultSet rs = prst.executeQuery();
//			if (!rs.next()) {
//				System.out.println("Không tìm thấy tên nhân viên");
//				return;
//			}
//		} catch (SQLException e) {
//			System.out.println("Xảy ra lỗi khi truy vấn dữ liệu");
//			return;
//		}
//		// Hàm cập nhật
//		double salary = Input.inputDouble("Nhập lương cập nhật");
//		int sumTimeWork = Input.inputInt("Nhập tổng giờ công cập nhật");
//		String sqlUpdate = "UPDATE EMPLOYEE SET SALARY = ?, SUMTIMEWORK = ? WHERE NAME =?";
//
//		try {
//			PreparedStatement prst = conn.prepareStatement(sqlUpdate);
//			prst.setDouble(1, salary);
//			prst.setInt(2, sumTimeWork);
//			prst.setString(3, name);
//			int row = prst.executeUpdate();
//			if (row == 0)
//				System.out.println("Cập nhật không thành công. Vui lòng thử lại");
//			else
//				System.out.println("Cập nhật thành công " + row + " dòng");
//		} catch (SQLException e) {
//			System.out.println("Cập nhật không thành công. Vui lòng thử lại");
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//
	public static String generateQuestionMarks(int count) {
		StringBuilder questionMarks = new StringBuilder();
		for (int i = 0; i < count; i++) {
			if (i > 0) {
				questionMarks.append(", ");
			}
			questionMarks.append("?");
		}
		return questionMarks.toString();
	}

}
