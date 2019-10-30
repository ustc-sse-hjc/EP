package
com.oracle.group3.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.oracle.group3.dao.HospitalMapper;
import com.oracle.group3.domain.ZrHospital;

@Service
@Scope("singleton")
public class HospitalService {
	@Resource
	HospitalMapper hospitalMapper;

	public ZrHospital getHospitalById(Integer hid){
		return hospitalMapper.getHospitalById(hid);
	}

	public List<ZrHospital> getHospitalByArea(String city){
		System.out.println("HospitalService²ã     "+city);
		List<ZrHospital> list = new ArrayList<>();
		list =	hospitalMapper.getHospitalByArea(city);
		for(ZrHospital l:list){
			if(l!=null){
				l.toString();
			}else{
				System.out.println("\tl is null");
			}
		}
		return list;
	}

	public List<ZrHospital> getHospitalDefault(){
		System.out.println("HospitalService²ã");
		List<ZrHospital> list = new ArrayList<>();
		list = hospitalMapper.getHospitalDefault();
		for(ZrHospital l:list){
			if(l!=null){
				l.toString();
			}else{
				System.out.println("\tl is null");
			}
		}
		return list;
	}

	public List<ZrHospital> getHospitalByPart(String content){
		System.out.println("HospitalService²ã     "+content);
		List<ZrHospital> list = new ArrayList<>();
		list =	hospitalMapper.getHospitalByPart(content);
		for(ZrHospital l:list){
			if(l!=null){
				l.toString();
			}else{
				System.out.println("\tl is null");
			}
		}
		return list;
	}
}
