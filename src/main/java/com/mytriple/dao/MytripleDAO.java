package com.mytriple.dao;

import java.util.List;
import java.util.Map;

import com.mytriple.dto.PointVO;


public interface MytripleDAO {
	public Boolean insertPoint(Map param) throws Exception;
	public Boolean deletePoint(String reviewId) throws Exception;
	public Boolean updatePoint(Map param) throws Exception;
	public Boolean insertPointHistory(Map param) throws Exception;
	public List<PointVO> selectPoint(String userId) throws Exception;
	public List<PointVO> findPlace(Map param) throws Exception;
	public List<PointVO> historyData(Map para) throws Exception;
	
	
}
