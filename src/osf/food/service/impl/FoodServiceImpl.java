package osf.food.service.impl;

import java.util.List;

import osf.food.dao.FoodDAO;
import osf.food.dao.impl.FoodDAOImpl;
import osf.food.service.FoodService;
import osf.food.vo.FoodVO;

public class FoodServiceImpl implements FoodService {
	private FoodDAO fdao = new FoodDAOImpl();
	@Override
	public List<FoodVO> selectFoodList(FoodVO Food) {
		return fdao.selectFoodList(Food);
	}

	@Override
	public FoodVO selectFood(Integer foodNum) {
		return fdao.selectFood(foodNum);
	}

	@Override
	public int insertFood(FoodVO food) throws Exception {
		List<FoodVO> tmpFoodList = fdao.selectFoodList(food);
		if(tmpFoodList.size()!=0) {
			throw new Exception("같은 이름의 음식이 존재합니다.");
		}
		return fdao.insertFood(food);
	}

	@Override
	public int updateFood(FoodVO food) throws Exception {
		List<FoodVO> tmpFoodList = fdao.selectFoodList(food);
		if(tmpFoodList.size()!=0) {
			throw new Exception("같은 이름의 음식이 존재합니다.");
		}
		return fdao.updateFood(food);
	}

	@Override
	public int deleteFood(FoodVO food) throws Exception{
		FoodVO tmpFood = fdao.selectFood(food.getFoodNum());
		if(tmpFood==null) {
			throw new Exception("이미 삭제 된 음식입니다. ");
		}
		return fdao.deleteFood(food);
	}
	public static void main(String[ ]args) {
		FoodService fs = new FoodServiceImpl();
//		List<FoodVO> foodList = fs.selectFoodList(null);
//		System.out.println(foodList);
//		//조회 테스트
		
		FoodDAO fdao = new FoodDAOImpl();	
		List<FoodVO> foodList = fdao.selectFoodList(null);
		System.out.println(foodList);
		FoodVO food = new FoodVO();
		food.setFoodName("참치");
		foodList = fdao.selectFoodList(food);
		System.out.println(foodList);
		
//		food.setFoodName("치토스치킨");
//		food.setFoodPrice(17000);
//		int cnt = 0;
//		try {
//			cnt = fs.insertFood(food);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("저장건수 : " + cnt);
//		
//		
//		food.setFoodNum(5);
//		food.setFoodName("뿌링클");
//		food.setFoodPrice(17000);
//		try {
//			cnt = fs.updateFood(food);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("수정건수 : " + cnt);
		
		
		food.setFoodNum(100);	
		int cnt = 0;
		try {
			cnt = fs.deleteFood(food);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("삭제건수 : " + cnt);
		
		
		
	}
}
