package osf.food.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import osf.food.dao.FoodDAO;
import osf.food.db.DBCon;
import osf.food.vo.FoodVO;

public class FoodDAOImpl implements FoodDAO {

	@Override
	public List<FoodVO> selectFoodList(FoodVO sFood) {
		String sql = "select * from food where 1=1";
		if(sFood!=null) {
			if(sFood.getFoodName()!=null) {
				sql += "and food_name=?";
			}
		}
			
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(sql);
			if(sFood!=null) {
				if(sFood.getFoodName()!=null) {
					ps.setString(1, sFood.getFoodName());
				}
			}
			ResultSet rs = ps.executeQuery();
			List<FoodVO> foodList = new ArrayList<>();
			while(rs.next()) {
				FoodVO food = new FoodVO();
				food.setFoodNum(rs.getInt("food_num"));
				food.setFoodName(rs.getString("food_name"));
				food.setFoodPrice(rs.getInt("food_price"));
				foodList.add(food);			
			}
			return foodList;
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			DBCon.close();
		}
		return null;
	}
	public FoodVO selectFood(Integer foodNum) { //단일조회
		String sql = "select food_num,food_name,food_price from food where food_num=?";
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(sql);
			ps.setInt(1, foodNum);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) { //단건인데 왜 while문에 넣쥬
				FoodVO food = new FoodVO();
				food.setFoodNum(rs.getInt("food_num"));
				food.setFoodName(rs.getString("food_name"));
				food.setFoodPrice(rs.getInt("food_price"));
				return food; //while문이 한번만 돌아야 정상이니 return 자리 노상관.
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBCon.close();
		}
			
		return null;
	}
	public static void main(String[] args) {
		FoodDAO fdao = new FoodDAOImpl();	
		List<FoodVO> foodList = fdao.selectFoodList(null);
		System.out.println(foodList);
		FoodVO food = new FoodVO();
		food.setFoodName("참치");
		foodList = fdao.selectFoodList(food);
		System.out.println(foodList);
		
		
		

		
	}
	@Override
	public int insertFood(FoodVO food) {
		String sql = "insert into food(food_num,food_name,food_price)"
				+" values((select nvl(max(food_num),0)+1 from food),?,?)";
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(sql);
			ps.setString(1, food.getFoodName());
			ps.setInt(2, food.getFoodPrice());
			return ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCon.close();
		}
		return 0;
	}
	@Override
	public int updateFood(FoodVO food) {
		String sql = "update food set food_name=?, food_price=? where food_num=?";
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(sql);
			ps.setString(1, food.getFoodName());	
			ps.setInt(2, food.getFoodPrice());
			ps.setInt(3, food.getFoodNum());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCon.close();
		}
	
	
		return 0;
	}
	@Override
	public int deleteFood(FoodVO food) {
		String sql = "delete from food where food_num = ?";
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(sql);
			ps.setInt(1, food.getFoodNum());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCon.close();
		}
		
		return 0;
	}
}
