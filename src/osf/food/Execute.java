package osf.food;

import java.util.HashMap;
import java.util.Map;

import osf.food.controller.FoodController;

public class Execute {
	private FoodController fc = new FoodController();
	public static void main (String[] args) {
		
		
		Map<String,String> req =  new HashMap<>();
		Map<String,Object> res =  new HashMap<>();
		
		Execute e = new Execute(); //foodController fc 사용하기 위해서. 
		try {
//			req.put("cmd","list");
//			e.fc.doGet(req,res);
//			System.out.println(res); //selectFoodList
//			
//			
//			req =  new HashMap<>();
//			res =  new HashMap<>();
//			req.put("cmd", "food");
//			req.put("foodNum", "1");
//			e.fc.doGet(req,res);		
//			System.out.println(res); //selectFood
//			
//			
			req =  new HashMap<>();
			res =  new HashMap<>();
			req.put("cmd", "insert");
			req.put("foodName", "순대");
			req.put("foodPrice","3000");
			e.fc.doPost(req, res);
			System.out.println(res); //insertFood
			
			
			req =  new HashMap<>();
			res =  new HashMap<>();
			req.put("cmd", "update");
			req.put("foodNum", "2");
//			req.put("foodName", "닭꼬치");
			req.put("foodPrice","4000");
			e.fc.doPost(req, res);
			System.out.println(res); //updateFood
			
			req =  new HashMap<>();
			res =  new HashMap<>();
			req.put("cmd", "delete");
			req.put("foodNum", "3");
			req.put("foodName", "닭꼬치");
			req.put("foodPrice","4000");
			e.fc.doPost(req, res);
			System.out.println(res); //deleteFood
			
			
			
			
		} catch (Exception e1) {		
			e1.printStackTrace();
		}
	}
}
