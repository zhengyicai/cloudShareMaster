/* 
 * 文件名：ManagerMachineService.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月31日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.app;

import java.util.List;

import com.qzi.cms.common.vo.CallVo;
import com.qzi.cms.common.vo.UseAlarmRecordVo;
import com.qzi.cms.common.vo.UseEquipmentVo;
import com.qzi.cms.common.vo.UseRoomVo;

/**
 * 管理机业务层接口
 * @author qsy
 * @version v1.0
 * @date 2017年8月31日
 */
public interface ManagerMachineService {

	/**
	 * 查询设备列表
	 * @param communityNo 设备编号
	 * @param equipmentType 设备类型
	 * @return 集合
	 */
	public List<UseEquipmentVo> findEquipments(String communityNo, String equipmentType);

	/**
	 * 获取设备信息
	 * @param equipmentId 设备编号
	 * @return 设备信息
	 */
	public UseEquipmentVo findEquipmentInfo(String equipmentId);

	/**
	 * 新增报警记录
	 * @param alarmRecordVo 报警记录对象
	 * @throws Exception 
	 */
	public void addAlarmRecord(UseAlarmRecordVo alarmRecordVo) throws Exception;

	/**
	 * 呼叫
	 * @param equipmentId 设备编号
	 * @param houseId 房间号
	 * @return
	 */
	public List<CallVo> call(String equipmentId, String houseId);

	/**
	 * 验证开门密码
	 * @param equipmentId 设备编号
	 * @param houseId 房间号
	 * @param openPwd 开门密码
	 * @return 是否正确
	 */
	public boolean validOpenPwd(String equipmentId, String houseId, String openPwd);

	/**
	 * 输入手机号获取对应的房间信息
	 * @param mobile 手机号
	 * @return 房间信息
	 */
	public List<UseRoomVo> findRooms(String mobile);

	/**
	 * 输入房间编号获取对应信息
	 * @param roomId 房间编号
	 * @return 房间信息
	 */
	public UseRoomVo findRoomById(String roomId);

}
