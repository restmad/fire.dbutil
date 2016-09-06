package studio.baxia.fire.dbutil.service;

import java.util.Map;

public interface ITestBeanService {

	/**
	 * 分页获取TestBean列表信息
	 * @param pageSize-每页数量
	 * @param currentPage-当前页数
	 * @return Map<String, Object>其中可能结果为：1.null;2.需要包含(pageBean,listBean)两个键值对
	 */
	Map<String, Object> pagingLists(int pageSize,int currentPage);
}
