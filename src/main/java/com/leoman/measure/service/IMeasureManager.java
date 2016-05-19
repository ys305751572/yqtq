package com.leoman.measure.service;

import com.leoman.common.entity.PageVO;
import com.leoman.common.service.GenericManager;
import com.leoman.measure.entity.Measure;
import com.leoman.measure.entity.vo.MeasureListVO;
import com.leoman.measure.entity.vo.MeasureSearchVO;

import java.util.List;

public interface IMeasureManager extends GenericManager<Measure> {

	public PageVO queryXtDataList(MeasureSearchVO measureSearchVO,int pagenum,int pagesize);
	
	public PageVO queryXyDataList(MeasureSearchVO measureSearchVO, int pagenum,int pagesize);
	
	public List<MeasureListVO> queryReportDateId(String userid,
												 String diviceID, String measureType, String sendTimeQ, String sendTimeZ);
	//测量血压，传入用户以及设备ID 查出数据结果
	public List<MeasureListVO> queryReportDate(String username, String diviceID, String measureType);
	
	/**
	 * 根据机器码查找用户数据
	 */
	public PageVO queryMeasureDataList(MeasureSearchVO measureSearchVO,int pagenum,int pagesize);
	
	/**
	 * 查找出代理商所属高血压偏高数据
	 */
	public String findHighBloodToUp(String orgId, String newTime);
	/**
	 * 查找出代理商所属高血压偏低数据
	 */
	public String findHighBloodToDown(String orgId, String newTime);
	/**
	 * 查找出代理商所属底血压偏高数据
	 */
	public String findLowBloodToUp(String orgId, String newTime);
	/**
	 * 查找出代理商所属底血压偏底数据
	 */
	public String findLowBloodToDown(String orgId, String newTime);
	/**
	 * 查找出血糖偏高
	 */
	public String findBloodSugarToUp(String orgId, String newTime);
	/**
	 * 查找出血糖偏低
	 */
	public String findBloodSugarToDown(String orgId, String newTime);
}
