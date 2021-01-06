package com.lcy.bll.manage.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.lcy.autogenerator.entity.Dept;
import com.lcy.autogenerator.entity.Operator;
import com.lcy.autogenerator.entity.RoleOperatorRelation;
import com.lcy.autogenerator.mapper.DeptMapper;
import com.lcy.autogenerator.mapper.OperatorMapper;
import com.lcy.autogenerator.service.IDeptService;
import com.lcy.autogenerator.service.IOperatorService;
import com.lcy.autogenerator.service.IRoleOperatorRelationService;
import com.lcy.bll.manage.IOrgBLL;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.TreeMaxSeqDTO;
import com.lcy.dto.common.TreeNode;
import com.lcy.dto.manage.DeptDTO;
import com.lcy.dto.manage.OperatorDTO;
import com.lcy.dto.manage.TreeDeptOpreator;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.IdPageParams;
import com.lcy.params.common.IdsParams;
import com.lcy.params.manage.DeptParams;
import com.lcy.params.manage.OperatorParams;
import com.lcy.params.manage.PasswordParams;
import com.lcy.type.common.BooleanType;
import com.lcy.type.manage.ManageCodeType;
import com.lcy.util.common.CloneUtils;
import com.lcy.util.common.SerialNumUtils;
import com.lcy.util.common.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrgBLL implements IOrgBLL {

	@Autowired
    IDeptService deptService;
	
	@Autowired
    IOperatorService operatorService;
	
	@Autowired
    DeptMapper deptMapper;
	
	@Autowired
    OperatorMapper operatorMapper;
	
	@Autowired
    IRoleOperatorRelationService roleOperatorDAO;
	
	@Override
	public List<TreeNode> listSubDeptAndOperator(String operatorId,
                                                 IDParams params) throws ServiceException {
		
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("isAdmin", BooleanType.FALSE.getCode());
		String pid = params.getId();
		if(StringUtils.isEmpty(pid)) {
			pid = params.getSiteId();
		}
		queryMap.put("parentId", pid);
		queryMap.put("siteId", params.getSiteId());
		
		List<TreeDeptOpreator> list = operatorMapper.listSubDeptAndOperator(queryMap);
		
		if(list == null || list.size() == 0) {
			return null;
		}
		
		// 转换成ztree格式
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		TreeNode treeNode = null;
		
		for(TreeDeptOpreator treeDeptOpreator : list){
			
			treeNode = new TreeNode();
			treeNode.setName(treeDeptOpreator.getName());
			treeNode.setId(treeDeptOpreator.getId());
			treeNode.setIsParent(treeDeptOpreator.getSubCount() > 0);
			
			// 类型作为额外参数传递
			Map<String,String> map = new HashMap<String,String>();
			map.put("type", treeDeptOpreator.getType());
			treeNode.setAttributes(map);
			
			treeNodes.add(treeNode);
		}
		
		return treeNodes;
	}
	
	@Transactional
	@Override
	public Boolean saveDept(String operatorId, DeptParams params)
			throws ServiceException {
		
		// 验证部门名称是否存在
		Dept existDept = this.getDeptByName(params.getDeptName(), params.getParentId(), params.getSiteId(), params.getAppId());
		if(existDept != null){
			throw new ServiceException(ManageCodeType.DEPT_NAME_EXIST.getName(),ManageCodeType.DEPT_NAME_EXIST.getNo());
		}
		
		Dept dept = new Dept();

		dept.setId(UUIDGenerator.getUUID());
		dept.setCreateTime(System.currentTimeMillis());
		dept.setCreateOperatorId(operatorId);
		dept.setIsDeleted(BooleanType.FALSE.getCode());
		dept.setName(params.getDeptName());
		dept.setParentId(params.getSiteId());
		dept.setRemark(params.getRemark());
		dept.setAppId(params.getAppId());
		dept.setSiteId(params.getSiteId());
		dept.setSiteAreaCode(params.getSiteAreaCode());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appId", params.getAppId());
		map.put("parentId", params.getParentId());
		map.put("siteId", params.getSiteId());
		TreeMaxSeqDTO maxSeqDTO = deptMapper.getMaxSeq(map);
		
		int maxSort = 0;
		String maxSeqNum = null;
		
		if (maxSeqDTO != null) {
			maxSort = maxSeqDTO.getMaxSort();
			maxSeqNum = maxSeqDTO.getMaxSeqNum();
		}
	
		// 说明新增的是一级部门
		if(StringUtils.isEmpty(params.getParentId())) {
			
			// 说明存在同级部门,取上一个部门的序列号进行累加
			if(StringUtils.isNotEmpty(maxSeqNum)) {
				dept.setSeqNum(SerialNumUtils.getNextSerialNum(maxSeqNum, ""));
			}else {// 否则为第一个部门,设置初始的序列号
				dept.setSeqNum(SerialNumUtils.getNextSerialNum("", ""));
			}
		}else {
			
			// 获取父部门
			Dept parentDept = this.getDeptById(params.getParentId());
			
			String parentSeqNum = null;
			if(parentDept != null) {
				parentSeqNum = parentDept.getSeqNum();
			}
			
			if(StringUtils.isNotEmpty(maxSeqNum)) {
				dept.setSeqNum(SerialNumUtils.getNextSerialNum(maxSeqNum, parentSeqNum));
			}else {
				dept.setSeqNum(SerialNumUtils.getNextSerialNum("", parentSeqNum));
			}
		}

		// 设置排序号
		dept.setSort(maxSort + 1);
		
		boolean flag = deptService.insert(dept);
		
		if(flag) {
			addDeptCache(dept);
		}
		
		return flag;
	}

	@Override
	public List<TreeNode> listSubDept(String operatorId, IDParams params)
			throws ServiceException {
		
		List<TreeNode> treeList = new ArrayList<TreeNode>();
		EntityWrapper<Dept> wrapper = new EntityWrapper<Dept>();
		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
		wrapper.eq("APP_ID", params.getAppId());
		wrapper.eq("SITE_ID", params.getSiteId());
		String pid = params.getId();
		if(StringUtils.isEmpty(pid)) {
			pid = params.getSiteId();
		}
		wrapper.eq("PARENT_ID", pid);
		wrapper.orderBy("SORT", false);
		
		List<Dept> list = deptService.selectList(wrapper);
		
		if(list!=null){
			
			TreeNode dto = null;
			
			for(Dept dept : list){
				
				dto = new TreeNode();
				
				dto.setId(dept.getId()+"");
				dto.setpId(dept.getParentId()+"");
				dto.setIsParent(false);
				dto.setValue(dept.getName());
				// 该部门下的人员数量
				int count = this.countOperatorByDept(dept.getId());
				dto.setName(dept.getName()+"("+count+")");
				
				treeList.add(dto);
			}
			
		}
		
		return treeList;
	}

	/**
	 * 根据id获取部门信息
	 * @param id 部门标识
	 * @return
	 * @throws ServiceException
	 */
	private Dept getDeptById(String id) throws ServiceException {
		
//		String key = ManageCacheKeyUtils.getDeptKey(id);
//		RedisCache cache = RedisCacheUtils.getInnoCache();
//		Dept dept = (Dept) cache.get(key);
//
//		if(dept == null) {
			
			EntityWrapper<Dept> wrapper = new EntityWrapper<Dept>();
			wrapper.eq("ID", id);
			
			Dept dept = deptService.selectOne(wrapper);
			
//			if(dept != null) {
//				addDeptCache(dept);
//			}
//		}
		
		return dept;
	}
	
	/**
	 * 根据名称获取部门信息
	 * @param name 部门名称
	 * @param parentId 父节点标识
	 * @param siteId   站点标识
	 * @param appId    应用标识
	 * @return
	 * @throws ServiceException
	 */
	private Dept getDeptByName(String name, String parentId, String siteId, String appId) throws ServiceException {
		
		EntityWrapper<Dept> wrapper = new EntityWrapper<Dept>();
		
		wrapper.eq("NAME", name);
		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
		wrapper.eq("SITE_ID", siteId);
		
		if(StringUtils.isNotEmpty(parentId)) {
			wrapper.eq("PARENT_ID", parentId);
		}else {
			wrapper.isNull("PARENT_ID");
		}
		
		Dept dept = deptService.selectOne(wrapper);
		
		return dept;
	}

	@Transactional
	@Override
	public Boolean updateDept(String operatorId, DeptParams params)
			throws ServiceException {
		
		// 验证部门名称是否存在
		Dept existDept = this.getDeptByName(params.getDeptName(), params.getParentId(), params.getSiteId(), params.getAppId());
		if(existDept != null && !String.valueOf(existDept.getId()).equals(params.getDeptId())){
			throw new ServiceException(ManageCodeType.DEPT_NAME_EXIST.getName(),ManageCodeType.DEPT_NAME_EXIST.getNo());
		}
		
		Dept oldDept = this.getDeptById(params.getDeptId());
		Dept newDept = CloneUtils.clone(oldDept);
		
		newDept.setUpdateOperatorId(operatorId);
		newDept.setUpdateTime(System.currentTimeMillis());
		newDept.setName(params.getDeptName());
		newDept.setRemark(params.getRemark());
		
		boolean flag = deptService.updateById(newDept);
		
		if(flag) {
			updateDeptCache(oldDept, newDept);
		}
		
		return flag;
	}

	@Transactional
	@Override
	public Boolean removeDept(String operatorId, IDParams params)
			throws ServiceException {
		
		Dept oldDept = this.getDeptById(params.getId());
		
		// 部门下是否有人员
		int count = this.countOperatorByDept(oldDept.getId());
		if(count > 0) {
			throw new ServiceException(ManageCodeType.OPERATOR_OF_DEPT_EXIST.getName(),ManageCodeType.OPERATOR_OF_DEPT_EXIST.getNo());
		}
		
		Dept newDept = CloneUtils.clone(oldDept);
		newDept.setDeletedTime(System.currentTimeMillis());
		newDept.setDeleteOperatorId(operatorId);
		newDept.setIsDeleted(BooleanType.TRUE.getCode());
		
		boolean flag = deptService.updateById(newDept);
		
		if(flag) {
			updateDeptCache(oldDept, newDept);
		}
		
		return flag;
	}

	@Override
	public DeptDTO getDept(String operatorId, IDParams params)
			throws ServiceException {
		
		DeptDTO dto = new DeptDTO();
		
		Dept dept = this.getDeptById(params.getId());
		
		if(dept != null) {
			dto.setDeptId(dept.getId());
			dto.setName(dept.getName());
			dto.setRemark(dept.getRemark());
		}
		
		return dto;
	}

	@Transactional
	@Override
	public Boolean saveOperator(String operatorId, OperatorParams params) throws ServiceException {
		
		Operator existOperator = this.getOperatorByPhone(params.getPhone(), params.getSiteId(), params.getAppId());
		
		if(existOperator != null) {
			throw new ServiceException(ManageCodeType.OPERATOR_PHONE_EXIST.getName(),ManageCodeType.OPERATOR_PHONE_EXIST.getNo());
		}
		
		Operator operator = new Operator();
		operator.setId(UUIDGenerator.getUUID());
		operator.setCreateOperatorId(operatorId);
		operator.setCreateTime(System.currentTimeMillis());
		operator.setDeptId(params.getDeptId());
		operator.setIsDeleted(BooleanType.FALSE.getCode());
		operator.setIsDeveloper(BooleanType.FALSE.getCode());
		operator.setAppId(params.getAppId());
		operator.setSiteId(params.getSiteId());
		operator.setSiteAreaCode(params.getSiteAreaCode());
		operator.setIsAdmin(BooleanType.FALSE.getCode());
		
		Dept dept = this.getDeptById(params.getDeptId());
		
		if(dept != null) {
			
			operator.setDeptSeq(dept.getSeqNum());
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("deptId", dept.getId());
			map.put("isDeleted", BooleanType.FALSE.getCode());
			
			TreeMaxSeqDTO maxSortDTO = operatorMapper.getMaxSort(map);
			int maxSort = 0;
			if(maxSortDTO != null) {
				maxSort = maxSortDTO.getMaxSort();
			}
			operator.setSort(maxSort + 1);
		}
		
		operator.setNickname(params.getNickname());
		operator.setPassword(params.getPassword());
		operator.setPhone(params.getPhone());
		operator.setRemark(params.getRemark());
		
		boolean flag = operatorService.insert(operator);
		
		if(flag) {
			addOperatorCache(operator);
		}
		
		return flag;
	}

	@Transactional
	@Override
	public Boolean updateOperator(String operatorId, OperatorParams params) throws ServiceException {
		
		Operator existOperator = this.getOperatorByPhone(params.getPhone(), params.getSiteId(), params.getAppId());
		
		if(existOperator != null && !String.valueOf(existOperator.getId()).equals(params.getOperatorId())) {
			throw new ServiceException(ManageCodeType.OPERATOR_PHONE_EXIST.getName(),ManageCodeType.OPERATOR_PHONE_EXIST.getNo());
		}
		
		Operator oldOper = this.getOperatorById(params.getOperatorId());
		Operator newOper = CloneUtils.clone(oldOper);
		newOper.setUpdateOperatorId(operatorId);
		newOper.setUpdateTime(System.currentTimeMillis());
		if(StringUtils.isNotEmpty(params.getPassword())) {
			newOper.setPassword(params.getPassword());
		}
		if(StringUtils.isNotEmpty(params.getDeptId())) {
			newOper.setDeptId(params.getDeptId());
		}
		newOper.setNickname(params.getNickname());
		newOper.setPhone(params.getPhone());
		newOper.setRemark(params.getRemark());
		
		boolean flag = operatorService.updateById(newOper);
		
		if(flag) {
			updateOperatorCache(oldOper, newOper);
		}
		
		return flag;
	}

	@Transactional
	@Override
	public Boolean removeOperator(String operatorId, IdsParams params) throws ServiceException {
		
		Operator oldOper = this.getOperatorById(params.getIds());
		
		if(oldOper == null) {
			return true;
		}
		
		long curTime = System.currentTimeMillis();
		
		Operator newOper = CloneUtils.clone(oldOper);
		
		newOper.setIsDeleted(BooleanType.TRUE.getCode());
		newOper.setDeletedTime(curTime);
		newOper.setDeleteOperatorId(operatorId);
		
		boolean flag = operatorService.updateById(newOper);
		
		if(flag) {
			
			// 更新缓存
			updateOperatorCache(oldOper, newOper);
			
			// 同时删除角色人员关系
			List<RoleOperatorRelation> beans = new ArrayList<RoleOperatorRelation>();
			RoleOperatorRelation relation = null;
			
			List<String> relationIds = roleOperatorDAO.listRelationId(null, params.getIds());
			
			if(relationIds != null && relationIds.size() > 0) {
				for (String relationId : relationIds) {
					relation = new RoleOperatorRelation();
					relation.setId(relationId);
					relation.setIsDeleted(BooleanType.TRUE.getCode());
					relation.setDeletedTime(curTime);
					relation.setDeleteOperatorId(operatorId);
					beans.add(relation);
				}
				
				flag = roleOperatorDAO.updateBatchById(beans);
			}
		}
		
		return flag;
	}

	@Override
	public OperatorDTO getOperator(String operatorId, IDParams params) throws ServiceException {
		
		OperatorDTO dto = null;
		
		Operator operator = this.getOperatorById(params.getId());
		
		if(operator != null) {
			
			dto = new OperatorDTO();
			dto.setNickname(operator.getNickname());
			dto.setOperatorId(operator.getId());
			dto.setPassword(operator.getPassword());
			dto.setPhone(operator.getPhone());
			dto.setRemark(operator.getRemark());
		}
				
		return dto;
	}

	@Override
	public PageResult<OperatorDTO> listOperatorByDept(String operatorId, IdPageParams params)
			throws ServiceException {
		
		EntityWrapper<Operator> wrapper = new EntityWrapper<Operator>();
		wrapper.eq("DEPT_ID", params.getpId());
		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
		wrapper.eq("SITE_ID", params.getSiteId());
		wrapper.orderBy("SORT", false);
		
		Page<Operator> page = operatorService.selectPage(new Page<Operator>(params.getPageNum(),
				params.getPageSize()), wrapper);
		
		if(page == null || page.getRecords() == null || page.getRecords().size() <= 0){
			return null;
		}
		
		List<Operator> records = page.getRecords();
		PageResult<OperatorDTO> resultPage = new PageResult<OperatorDTO>();
		resultPage.setCurPage(params.getPageNum());
		resultPage.setPageSize(params.getPageSize());
		resultPage.setTotal(page.getTotal());
		List<OperatorDTO> list = new ArrayList<OperatorDTO>();
		
		for (Operator operator : records) {
			
			if(operator == null){
				continue;
			}
			
			OperatorDTO dto = new OperatorDTO();
			dto.setNickname(operator.getNickname());
			dto.setOperatorId(operator.getId());
			dto.setPassword(operator.getPassword());
			dto.setPhone(operator.getPhone());
			dto.setRemark(operator.getRemark());
			list.add(dto);
		}
		
		resultPage.setList(list);
		return resultPage;
	}

	@Override
	public Boolean updatePassword(String operatorId, OperatorParams params) throws ServiceException {
		
		Operator oldOper = this.getOperatorById(params.getOperatorId());
		Operator newOper = CloneUtils.clone(oldOper);
		
		newOper.setUpdateOperatorId(operatorId);
		newOper.setUpdateTime(System.currentTimeMillis());
		newOper.setPassword(params.getPassword());
		
		boolean flag = operatorService.updateById(newOper);
		
		if(flag) {
			updateOperatorCache(oldOper, newOper);
		}
		
		return flag;
	}

	@Override
	public Boolean updatePwd(String operatorId, PasswordParams params) throws ServiceException {

		Operator oldOper = this.getOperatorById(params.getOperatorId());

		if (!org.apache.commons.lang3.StringUtils.equals(oldOper.getPassword(), params.getOldPwd())){
			throw new ServiceException("旧密码不正确");
		}

		Operator newOper = CloneUtils.clone(oldOper);

		newOper.setUpdateOperatorId(operatorId);
		newOper.setUpdateTime(System.currentTimeMillis());
		newOper.setPassword(params.getNewPwd());

		boolean flag = operatorService.updateById(newOper);

		if(flag) {
			updateOperatorCache(oldOper, newOper);
		}

		return flag;
	}

	@Override
	public Operator getOperatorById(String id) throws ServiceException {
		
//		String key = ManageCacheKeyUtils.getOperatorKey(id);
//		RedisCache cache = RedisCacheUtils.getInnoCache();
//		Operator operator = (Operator) cache.get(key);
//
//		if(operator == null) {
			
			EntityWrapper<Operator> wrapper = new EntityWrapper<Operator>();
			wrapper.eq("ID", id);

			Operator operator = operatorService.selectOne(wrapper);
			
//			if(operator != null) {
//				addOperatorCache(operator);
//			}
//		}
		
		return operator;
	}
	
	@Override
	public Operator getOperatorByPhone(String phone, String siteId, String appId) throws ServiceException {
		
		EntityWrapper<Operator> wrapper = new EntityWrapper<Operator>();
		
		wrapper.eq("PHONE", phone);
		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
		
		Operator operator = operatorService.selectOne(wrapper);
		
		return operator;
	}
	
	/**
	 * 获取部门下的人员数量
	 * @param deptId 部门标识
	 * @return
	 * @throws ServiceException
	 */
	private int countOperatorByDept(String deptId) throws ServiceException {
		
		if(StringUtils.isEmpty(deptId)) {
			return 0;
		}
		
		EntityWrapper<Operator> wrapper = new EntityWrapper<Operator>();
		wrapper.eq("DEPT_ID", deptId);
		wrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
		
		return operatorService.selectCount(wrapper);
	}

	/**
	 * 添加运营人员缓存
	 * @param bean 运营人员对象
	 */
	private void addOperatorCache(Operator bean) {
//		
//		if (bean.getIsDeleted() == BooleanType.TRUE.getCode()) {
//			return;
//		}
//		
//		String key = ManageCacheKeyUtils.getOperatorKey(bean.getId()+"");
//		RedisCache cache = RedisCacheUtils.getInnoCache();
//		cache.add(key, bean);
	}
	
	/**
	 * 删除运营人员缓存
	 * @param bean 运营人员对象
	 */
	private void removeOperatorCache(Operator bean) {
//		
//		String key = ManageCacheKeyUtils.getOperatorKey(bean.getId()+"");
//		RedisCache cache = RedisCacheUtils.getInnoCache();
//		cache.remove(key);
	}
	
	/**
	 * 更新运营人员缓存 
	 * @param oldBean 旧运营人员对象
	 * @param newBean 新运营人员对象
	 */
	private void updateOperatorCache(Operator oldBean, Operator newBean) {
		this.removeOperatorCache(oldBean);
		this.addOperatorCache(newBean);
	}
	
	/**
	 * 添加部门缓存
	 * @param bean 部门对象
	 */
	private void addDeptCache(Dept bean) {
//		
//		if (bean.getIsDeleted() == BooleanType.TRUE.getCode()) {
//			return;
//		}
//		
//		String key = ManageCacheKeyUtils.getDeptKey(bean.getId()+"");
//		RedisCache cache = RedisCacheUtils.getInnoCache();
//		cache.add(key, bean);
	}

	/**
	 * 删除部门缓存
	 * @param bean
	 */
	private void removeDeptCache(Dept bean) {
//		
//		String key = ManageCacheKeyUtils.getDeptKey(bean.getId()+"");
//		RedisCache cache = RedisCacheUtils.getInnoCache();
//		cache.remove(key);
	}

	/**
	 * 更新部门缓存
	 * @param oldBean 旧部门对象
	 * @param newBean 新部门对象
	 */
	private void updateDeptCache(Dept oldBean, Dept newBean) {
		this.removeDeptCache(oldBean);
		this.addDeptCache(newBean);
	}

	@Override
	public Operator getAdminOperatorBySite(String siteId, String appId)
			throws ServiceException {
		
		return operatorService.getAdminOperatorBySite(siteId, appId);
	}
}
