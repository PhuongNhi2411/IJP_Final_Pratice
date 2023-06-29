package ijp_final;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Function {
	public static Scanner sc = new Scanner(System.in);
	public static Connection conn;
	public static ArrayList<Employee> employees = new ArrayList<>();

	/**
	 * Hàm thêm mới dữ liệu vào bảng
	 */
	public static void insertEmployee(Scanner sc) {
		Employee emp = inputEmployee();
		insert(emp);
		employees.add(emp);
	}

	public static Employee inputEmployee() {
		Employee emp = new Employee();
		System.out.println("-------Thêm nhân viên----------");
		emp.id = Input.inputLong("Nhập ID nhân viên");
		emp.name = Input.inputString("Nhập tên nhân viên");
		emp.age = Input.inputInt("Nhập tuổi nhân viên");
		emp.address = Input.inputString("Nhập địa chỉ nhân viên");
		emp.salary = Input.inputDouble("Nhập lương nhân viên");
		emp.sumTimeWork = Input.inputInt("Nhập tổng số giờ làm của nhân viên");
		return emp;
	}

	public static String insert(Employee emp) {
		conn = ConnectionUtil.getConnection();

		String sql = "INSERT INTO EMPLOYEE VALUES (" + generateQuestionMarks(6) + ")";

		// Insert dữ liệu vào sql
		try {
			PreparedStatement prstmt = conn.prepareStatement(sql);
			prstmt.setLong(1, emp.id);
			prstmt.setString(2, emp.name);
			prstmt.setInt(3, emp.age);
			prstmt.setString(4, emp.address);
			prstmt.setDouble(5, emp.salary);
			prstmt.setInt(6, emp.sumTimeWork);
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

	/**
	 * Hàm lấy toàn bộ dữ liệu
	 */
	public static void getAllEmployee() {
		List<Employee> emps = new ArrayList<>();
		conn = ConnectionUtil.getConnection();

		String sql = "SELECT * FROM EMPLOYEE";
		try {
			PreparedStatement stt = conn.prepareStatement(sql);
			ResultSet rs = stt.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.id = rs.getLong("ID");
				emp.name = rs.getString("Name");
				emp.age = rs.getInt("Age");
				emp.address = rs.getString("Address");
				emp.salary = rs.getDouble("salary");
				emp.sumTimeWork = rs.getInt("sumTimeWork");
				emps.add(emp);

			}
			// Hàm hiển thị
			for (int i = 0; i < emps.size(); i++) {
				System.out.println(emps.get(i).toString());

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

	/**
	 * Hàm tìm kiếm thông tin theo tên
	 */
	public static void getEmployeeByName() {
		conn = ConnectionUtil.getConnection();
		List<Employee> emps = new ArrayList<>();
		String name = Input.inputString("Nhập tên nhân viên muốn tìm kiếm");
		String sql = "SELECT * FROM EMPLOYEE WHERE Name=?";
		try {
			PreparedStatement prst = conn.prepareStatement(sql);

			prst.setString(1, name);
			ResultSet rs = prst.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.id = rs.getLong("ID");
				emp.name = rs.getString("Name");
				emp.age = rs.getInt("Age");
				emp.address = rs.getString("Address");
				emp.salary = rs.getDouble("salary");
				emp.sumTimeWork = rs.getInt("sumTimeWork");
				emps.add(emp);

			}
			// Hàm hiển thị
			for (int i = 0; i < emps.size(); i++) {
				System.out.println(emps.get(i).toString());
			}
			if (emps.size() == 0) {
				System.out.println("Không tìm thấy tên nhân viên");
				System.out.println("---------------------------");

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

	/**
	 * Hàm xóa thông tin theo tên
	 */
	public static void deleteEmployeeByName() {
		conn = ConnectionUtil.getConnection();
		String name = Input.inputString("Nhập tên nhân viên muốn xóa");
		String sql = "DELETE FROM EMPLOYEE WHERE Name=?";
		try {
			PreparedStatement prst = conn.prepareStatement(sql);
			prst.setString(1, name);
			int rows = prst.executeUpdate();
			if (rows == 0) {
				System.out.println("Không tìm thấy tên nhân viên");

			} else
				System.out.println("Đã xóa " + rows + " dòng tương ứng");

		} catch (

		SQLException e) {
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

	/**
	 * Hàm cập nhật lương, tổng giờ làm nhân viên theo tên
	 */
	public static void updateEmployeeByName() {
		conn = ConnectionUtil.getConnection();
		String name = Input.inputString("Nhập tên nhân viên muốn cập nhật");
		// Kiểm tra xem nhân viên có tồn tại trong database không
		String sql = "SELECT * FROM EMPLOYEE WHERE Name=?";
		try {
			PreparedStatement prst = conn.prepareStatement(sql);
			prst.setString(1, name);
			ResultSet rs = prst.executeQuery();
			if (!rs.next()) {
				System.out.println("Không tìm thấy tên nhân viên");
				return;
			}
		} catch (SQLException e) {
			System.out.println("Xảy ra lỗi khi truy vấn dữ liệu");
			return;
		}
		// Hàm cập nhật
		double salary = Input.inputDouble("Nhập lương cập nhật");
		int sumTimeWork = Input.inputInt("Nhập tổng giờ công cập nhật");
		String sqlUpdate = "UPDATE EMPLOYEE SET SALARY = ?, SUMTIMEWORK = ? WHERE NAME =?";

		try {
			PreparedStatement prst = conn.prepareStatement(sqlUpdate);
			prst.setDouble(1, salary);
			prst.setInt(2, sumTimeWork);
			prst.setString(3, name);
			int row = prst.executeUpdate();
			if (row == 0)
				System.out.println("Cập nhật không thành công. Vui lòng thử lại");
			else
				System.out.println("Cập nhật thành công " + row + " dòng");
		} catch (SQLException e) {
			System.out.println("Cập nhật không thành công. Vui lòng thử lại");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

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
