package com.oracle.group3.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.oracle.group3.dao.PregnantInformationMapper;
import com.oracle.group3.domain.PregnantInformation;

@Service
@Scope("singleton")
public class PregnantInformationService {
	@Resource
	private PregnantInformationMapper PregnantInformationMapper;

	public List<PregnantInformation> getPregnantInformationById(int uid) {

		List<PregnantInformation> list = null;
		list = PregnantInformationMapper.getPregnantInformationById(uid);
		return list;

	}
}
