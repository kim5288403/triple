package com.mytriple.controller;

import org.slf4j.Logger;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mytriple.dto.PointVO;
import com.mytriple.service.MytripleService;

@Controller
@RequestMapping(value = "triple")
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


	@Inject
	private MytripleService service;
	
	@RequestMapping(value = "/event", method = RequestMethod.POST)
	@ResponseBody
	public HashMap event(@RequestBody HashMap<String, Object> request) throws Exception {
		logger.info("event");

		String action = (request.containsKey("action")) ? (String) request.get("action") : "";
		HashMap result = new HashMap();
		
		if (Objects.equals(action, "DELETE")) {
			return delete(request);
		}
		
		if (Objects.equals(action, "ADD")) {
			return insert(request);
		}
		
		if (Objects.equals(action, "MOD")) {
			return modify(request);
		}
		
		result.put("code", 400);
		result.put("message", "잘못된 접근 입니다.");
		result.put("data",request);
		return result;		
	}
	
	@RequestMapping(value = "/point", method = RequestMethod.GET)
	@ResponseBody
	public HashMap point(@RequestBody HashMap<String, Object> request) throws Exception {
		logger.info("point");
		HashMap result = new HashMap();
		HashMap data = new HashMap();
		String userId = (request.containsKey("userId")) ? (String) request.get("userId") : "";

		if (Objects.equals(userId, "")) {
			result.put("code", 400);
			result.put("data",request);
			result.put("message", "잘못된 접근 입니다.");
			return result;	
		}
		
		if(service.selectPoint(userId).size() > 0) {
			PointVO list = service.selectPoint(userId).get(0);
			
			data.put("userId", list.getUserId());
			data.put("point", list.getTotal());
				
			result.put("code", 200);
			result.put("data",data);
			result.put("message", "포인트 조회 성공하였습니다.");
		}else {
			result.put("code", 200);
			result.put("data",request);
			result.put("message", "조회할 포인트가 없습니다.");
		}
		
		return result;		
	}
	
	
	public HashMap insert(Map request) throws Exception {
		Map param = new HashMap();
		String userId = (request.containsKey("userId")) ? request.get("userId").toString() : "";
		String reviewId = (request.containsKey("reviewId")) ? request.get("reviewId").toString() : "";
		String placeId = (request.containsKey("placeId")) ? request.get("placeId").toString() : "";
		Timestamp date = new Timestamp(new java.util.Date().getTime());
		
		int point = point(request);
		HashMap result = new HashMap();
		
		param.put("userId",userId);
		param.put("placeId",placeId);
		param.put("reviewId", reviewId);
		param.put("point",point);
		param.put("created_at",date);
		
		if (service.insertPoint(param)) {
			result.put("massage", "등록 성공 했습니다.");
			pointHistory(request);
		} else {
			result.put("massage", "등록 실패 했습니다.");
		}
			
		result.put("code", 200);
		result.put("data",request);
		return result;
	}
	
	public HashMap modify(Map request) throws Exception {
		Map param = new HashMap();
		String reviewId = (request.containsKey("reviewId")) ? request.get("reviewId").toString() : "";
		int point = point(request);
		HashMap result = new HashMap();
		
		param.put("reviewId", reviewId);
		param.put("point", point);
		pointHistory(request);
		if (service.updatePoint(param)) {
			result.put("massage", "수정 성공 했습니다.");
		} else {
			result.put("massage", "수정 실패 했습니다.");
		}
		
		result.put("code", 200);
		result.put("data",request);
		return result;
	}
	
	public HashMap delete(Map request) throws Exception {
		String reviewId = (request.containsKey("reviewId")) ? (String) request.get("reviewId") : "";
		HashMap result = new HashMap();
		
		if (service.deletePoint(reviewId)) {
			result.put("massage", "삭제 성공 했습니다.");
		} else {
			result.put("massage", "삭제 실패 했습니다.");
		}
		result.put("code", 200);
		result.put("data",request);
		
		return result;
	}
	
	public int point(Map request) throws Exception {
		String reviewId = (request.containsKey("reviewId")) ? request.get("reviewId").toString() : "";
		String placeId = (request.containsKey("placeId")) ? request.get("placeId").toString() : "";
		String content = (request.containsKey("content")) ? request.get("content").toString() : "";
		String attachedPhotoIdsStr = (request.containsKey("attachedPhotoIds")) ? request.get("attachedPhotoIds").toString(): "";
		String attachedPhotoIds = attachedPhotoIdsStr.replace("[", "").replace("]", "");

		Map param = new HashMap();
		param.put("placeId", placeId);
		param.put("reviewId", reviewId);
		
		List<PointVO> check = service.findPlace(param);
		
		int point = 0;
		
		if (content.length() > 0) {
			point++;
		}
		if (attachedPhotoIds.length() > 0) {
			point++;
		}
		if (check.size() > 0) {
			if (Objects.equals(check.get(0).getReviewId(), reviewId)) {
				point++;
			}
		}else {
			point++;
		}
		
		return point;
	}
	
	public void pointHistory(Map request) throws Exception {
		Map param = new HashMap();
		int point = point(request);
		List<PointVO> historyData = service.historyData(request);
		String content = "";
		Timestamp date = new Timestamp(new java.util.Date().getTime());
		
		if (Objects.equals(request.get("action"), "ADD")) {
			content = point + " 포인트 증가";
			param.put("pointId", historyData.get(0).getId());
			param.put("content", content);
			param.put("created_at", date);
			service.insertPointHistory(param);
		}
		
		if (Objects.equals(request.get("action"), "MOD")) {
			if (historyData.size() != 0) {
				
				if(historyData.get(0).getPoint() > point) {
					content = (historyData.get(0).getPoint() - point) + " 포인트 감소";
				}				
				
				if(historyData.get(0).getPoint() < point) {
					content = (point - historyData.get(0).getPoint()) + " 포인트 증가";
				}
				
				if (historyData.get(0).getPoint() != point) {
					param.put("pointId", historyData.get(0).getId());
					param.put("content", content);
					param.put("created_at", date);
					service.insertPointHistory(param);
				}
			}
		}
	}
	
}
