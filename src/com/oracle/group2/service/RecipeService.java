package com.oracle.group3.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.oracle.group3.dao.RecipeMapper;
import com.oracle.group3.domain.PreganancyDiet;



@Service
@Scope("singleton")
public class RecipeService {
	@Resource
	private RecipeMapper recipeMapper;
	
	public PreganancyDiet getRecipe(){
		PreganancyDiet pd=null;
		pd=recipeMapper.getRecipe();
		return pd;
		
	}
	
	
	
}

/*@Service
@Scope("singleton")
public class HabitRecordService {
@Resource
private HabitRecordMapper HabitRecordMapper;
    public void insertHabit(int uid,String habit){
        try{
        	 System.out.println("走到这里");
        	 System.out.println(habit);
              HabitRecordMapper.insertHabit(uid, habit);
            }catch(Exception e){
	             e.getMessage();
	             System.out.println(e.getMessage());
                }
     }
   public List<HabitRecord> getHabitById(int uid){
	
		   List<HabitRecord> list = null;
		   list =  HabitRecordMapper.getHabitRecordById(uid);
	       return list;
	  
   }
}*/