package osf.food.controller;


import java.util.Map;

import osf.food.service.FoodService;
import osf.food.service.impl.FoodServiceImpl;
import osf.food.vo.FoodVO;

public class FoodController {
	private FoodService fs = new FoodServiceImpl();
	public void doGet(Map<String,String> req, Map<String,Object> res) throws Exception {
		String cmd = req.get("cmd");
		//String foodName = req.get("name");

		FoodVO food = new FoodVO();
		//food.setFoodName(foodName);
		if("list".equals(cmd)) {
			res.put("list",fs.selectFoodList(food));
		}else if ("food".equals(cmd)) {
			Integer foodNum = Integer.parseInt(req.get("foodNum"));			
			res.put("food",fs.selectFood(foodNum));
		}else {
			throw new Exception("요청하신 서비스는 없는 서비스 입니다.");
		}
	}
	public void doPost(Map<String,String> req, Map<String,Object> res) throws Exception{
		
		String cmd = req.get("cmd");
		
		String foodName = req.get("foodName");
		Integer foodPrice = Integer.parseInt(req.get("foodPrice"));
		
		FoodVO food = new FoodVO();
	;
		
		
		if("insert".equals(cmd)) {			
			food.setFoodName(foodName);
			food.setFoodPrice(foodPrice);
			res.put("insert",fs.insertFood(food));
			
		}else if("update".equals(cmd)) {
			Integer foodNum = Integer.parseInt(req.get("foodNum"));
			food.setFoodNum(foodNum);
			food.setFoodName(foodName);
			food.setFoodPrice(foodPrice);
			res.put("update",fs.updateFood(food));
			
		}else if("delete".equals(cmd)) {
			Integer foodNum = Integer.parseInt(req.get("foodNum"));
			food.setFoodNum(foodNum);
			res.put("delete",fs.deleteFood(food));
			
		}else {
			throw new Exception ("요청하신 서비스는 없는 서비스 입니다. ");
		}
		
	}
}
