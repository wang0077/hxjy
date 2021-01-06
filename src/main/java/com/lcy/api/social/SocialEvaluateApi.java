package com.lcy.api.social;

import com.lcy.bll.social.ISocialEvaluateBLL;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.social.SocialEvaluateDTO;
import com.lcy.dto.social.SocialEvaluateQuotaDTO;
import com.lcy.params.common.IDAppPageParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.social.EvaluateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialEvaluateApi {

	@Autowired
	ISocialEvaluateBLL bll;
	
	/**
	 * 评价
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public SocialEvaluateDTO evaluate(EvaluateParams params) throws ServiceException {
		return bll.evaluate(params);
	}
	
	/**
	 * 删除评价
	 * @param idParams
	 * @return
	 * @throws ServiceException
	 */
	public SocialEvaluateDTO delete(IDParams idParams)throws ServiceException{
		return bll.delete(idParams);
	}
	
	/**
	 * 获取用户被评价的所有评价列表
	 * @param idParams
	 * @return
	 * @throws ServiceException
	 */
	public List<SocialEvaluateDTO> getUserAllEvaluatedList(IDAppPageParams pageParams)throws ServiceException{
		return bll.getUserAllEvaluatedList(pageParams);
	}
	
	/**
	 * 获取用户被评价的所有评价列表
	 * @param idParams
	 * @return
	 * @throws ServiceException
	 */
	public Long getUserAllEvaluatedCount(IDParams idParams)throws ServiceException{
		return bll.getUserAllEvaluatedCount(idParams);
	}
	
	/**
	 * 获取用户某资源被评价的所有评价列表
	 * @param idParams
	 * @return
	 * @throws ServiceException
	 */
	public List<SocialEvaluateDTO> getUserResourceIdAllEvaluatedList(EvaluateParams evaluateParams)throws ServiceException{
		return bll.getUserResourceIdAllEvaluatedList(evaluateParams);
	}
	
	/**
	 * 获取用户某资源被评价的所有评价列表
	 * @param idParams
	 * @return
	 * @throws ServiceException
	 */
	public Long getUserResourceIdAllEvaluatedCount(EvaluateParams evaluateParams)throws ServiceException{
		return bll.getUserResourceIdAllEvaluatedCount(evaluateParams);
	}
	
	/**
	 * 获取用户某资源类型被评价的所有评价列表
	 * @param idParams
	 * @return
	 * @throws ServiceException
	 */
	public List<SocialEvaluateDTO> getUserResourceTypeAllEvaluatedList(EvaluateParams evaluateParams)throws ServiceException{
		return bll.getUserResourceTypeAllEvaluatedList(evaluateParams);
	}
	
	/**
	 * 获取用户某资源类型被评价的所有评价列表
	 * @param idParams
	 * @return
	 * @throws ServiceException
	 */
	public Long getUserResourceTypeAllEvaluatedCount(EvaluateParams evaluateParams)throws ServiceException{
		return bll.getUserResourceTypeAllEvaluatedCount(evaluateParams);
	}
	
	/**
	 * 获取用户评价别人的所有评价列表
	 * @param idParams
	 * @return
	 * @throws ServiceException
	 */
	public List<SocialEvaluateDTO> getUserAllEvaluateOtherUserList(IDAppPageParams pageParams)throws ServiceException{
		return bll.getUserAllEvaluateOtherUserList(pageParams);
	}
	
	/**
	 * 获取用户某个资源被评价的所有评价列表
	 * @param idParams
	 * @return
	 * @throws ServiceException
	 */
	public Long getUserResourceIdAllEvaluateOtherUserList(EvaluateParams evaluateParams)throws ServiceException{
		return bll.getUserResourceIdAllEvaluateOtherUserList(evaluateParams);
	}
	
	
	/**
	 * 获取用户某个资源类型被评价的所有评价列表
	 * @param idParams
	 * @return
	 * @throws ServiceException
	 */
	public List<SocialEvaluateDTO> getUserResourceTypeAllEvaluateOtherUserList(EvaluateParams evaluateParams)throws ServiceException{
		return bll.getUserResourceTypeAllEvaluateOtherUserList(evaluateParams);
	}
	
	
	/**
	 * 获取用户除某资源外的资源被评价的所有评价列表
	 * @param idParams
	 * @return
	 * @throws ServiceException
	 */
	public List<SocialEvaluateDTO> getUserOtherResourceIdsAllEvaluatedList(EvaluateParams evaluateParams)throws ServiceException{
		return bll.getUserOtherResourceIdsAllEvaluatedList(evaluateParams);
	}
	
	/**
	 * 获取用户除某资源外的资源被评价的所有评价列表
	 * @param idParams
	 * @return
	 * @throws ServiceException
	 */
	public Long getUserOtherResourceIdsAllEvaluatedCount(EvaluateParams evaluateParams)throws ServiceException{
		return bll.getUserOtherResourceIdsAllEvaluatedCount(evaluateParams);
	}
	
	
	/**
	 * 获取某个资源的统计
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public SocialEvaluateQuotaDTO getResourceIdEvaluateQuotaDTO(EvaluateParams params) throws ServiceException{
		return bll.getResourceIdEvaluateQuotaDTO(params);
	}
	
	
	/**
	 * 获取某种资源所属的用户的统计
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public SocialEvaluateQuotaDTO getResourceTypeUserEvaluateQuotaDTO(EvaluateParams params) throws ServiceException{
		return bll.getResourceTypeUserEvaluateQuotaDTO(params);
	}
	
	/**
	 * 获取普通用户的统计（资源用户对她的评价）
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public SocialEvaluateQuotaDTO getCommonUserEvaluateQuotaDTO(EvaluateParams params) throws ServiceException{
		return bll.getCommonUserEvaluateQuotaDTO(params);
	}

	/**
	 * 获取用户某交易号被评价的所有评价列表
	 * @param idParams
	 * @return
	 * @throws ServiceException
	 */
	public List<SocialEvaluateDTO> getUserTradeIdAllEvaluatedList(EvaluateParams evaluateParams)throws ServiceException{
		return bll.getUserTradeIdAllEvaluatedList(evaluateParams);
	}
}
