package com.mytriple.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mytriple.dao.MytripleDAO;
import com.mytriple.dto.PointVO;

@Service
public class MytripleServiceImpl implements MytripleService{
	
	@Inject
	private MytripleDAO dao;

	@Override
	public Boolean insertPoint(Map parma) throws Exception {
		return dao.insertPoint(parma);
	}

	@Override
	public Boolean deletePoint(String reviewId) throws Exception {
		return dao.deletePoint(reviewId);
	}

	@Override
	public List<PointVO> findPlace(Map param) throws Exception {
		return dao.findPlace(param);
	}

	@Override
	public Boolean updatePoint(Map param) throws Exception {
		return dao.updatePoint(param);
	}

	@Override
	public List<PointVO> historyData(Map param) throws Exception {
		return dao.historyData(param);
	}

	@Override
	public Boolean insertPointHistory(Map param) throws Exception {
		return dao.insertPointHistory(param);
	}

	@Override
	public List<PointVO> selectPoint(String userId) throws Exception {
		return dao.selectPoint(userId);
	}

	
	
}
