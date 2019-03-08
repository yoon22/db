package osf.food.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// 연결되는지 잘 끊기는지 테스트 해야함! 단일테스트 .....
public class DBCon {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "osfu";
	private static final String PASSWORD = "12345678";
	private static final String CLASS_NAME = "oracle.jdbc.OracleDriver"; // 오류를 처리하기 위해 만들어진 클래스..
	private static Connection con;

	private static boolean open() {
		try {
			Class.forName(CLASS_NAME);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// 15라인에서 오류날경우
		} catch (SQLException e) {
			e.printStackTrace();
			// 16라인에서 오류날경우 (url,user,password같은거..?)
		}
		return false;
	}

	public static Connection getCon() {// 캡슐화! 얘만 왜 public 일까?
		if (con == null) {
			if (open()) {
				return con;
			}
		}
		return null;
	}

	public static void close() {
		if (con != null) {
			try {
				if (!con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
		con = null;
	}

}
