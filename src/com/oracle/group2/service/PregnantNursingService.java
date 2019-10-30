package com.oracle.group3.service;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.oracle.group3.domain.PregnantNursing;
import com.oracle.group3.dao.PregnantNursingMapper;

@Service
@Scope("singleton")
public class PregnantNursingService {
@Resource
private PregnantNursingMapper PregnantNursingMapper;
   public List<PregnantNursing> getPregnantNursingById(int disease_id){
	
		   List<PregnantNursing> list = null;
		   list =  PregnantNursingMapper.getPregnantNursingById(disease_id);
	       return list;
	  
   }
}
