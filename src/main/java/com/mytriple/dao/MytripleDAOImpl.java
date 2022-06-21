package com.mytriple.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mytriple.dto.MytripleVO;
import com.mytriple.dto.PointVO;

@Repository
public class MytripleDAOImpl implements MytripleDAO {

	@Inject
	private SqlSession sqlSession;

	private static final String Namesapce = "com.example.mapper.tripleMapper";

	@Override
	public Boolean insertPoint(Map param) throws Exception {
		int result = sqlSession.insert(Namesapce + ".insertPoint", param);
		if (result == 0) {
			return false; 
		}
		return true; 
	}

	@Override
	public Boolean deletePoint(String reviewId) throws Exception {
		int result = sqlSession.delete(Namesapce + ".deletePoint", reviewId);
		if (result == 0) {
			return false; 
		}
		return true; 
	}

	@Override
	public List<PointVO> findPlace(Map param) throws Exception {
		return sqlSession.selectList(Namesapce + ".findPlace", param);
	}

	@Override
	public Boolean updatePoint(Map param) throws Exception {
		int result = sqlSession.update(Namesapce + ".updatePoint", param);
		if (result == 0) {
			return false; 
		}
		return true; 
	}

	@Override
	public List<PointVO> historyData(Map param) throws Exception {
		return sqlSession.selectList(Namesapce + ".historyData", param);
	}

	@Override
	public Boolean insertPointHistory(Map param) throws Exception {
		int result = sqlSession.insert(Namesapce + ".insertPointHistory", param);
		if (result == 0) {
			return false; 
		}
		return true; 
	}

	@Override
	public List<PointVO> selectPoint(String userId) throws Exception {
		return sqlSession.selectList(Namesapce + ".selectPoint", userId);
	}

	
}
