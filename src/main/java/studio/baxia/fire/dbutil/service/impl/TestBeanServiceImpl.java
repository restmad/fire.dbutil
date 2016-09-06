package studio.baxia.fire.dbutil.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import studio.baxia.fire.dbutil.dao.ITestBeanDao;
import studio.baxia.fire.dbutil.dao.impl.TestBeanDaoImpl;
import studio.baxia.fire.dbutil.pojo.TestBean;
import studio.baxia.fire.dbutil.service.ITestBeanService;
import studio.baxia.fire.dbutil.vo.PageVO;


public class TestBeanServiceImpl implements ITestBeanService {
	ITestBeanDao testBeanDao = new TestBeanDaoImpl();

	@Override
	public Map<String, Object> pagingLists(int pageSize, int current) {
		PageVO pageBean = new PageVO();
		int allRecords = testBeanDao.countRow();
		int totalPages = pageBean.calculateTotalPage(pageSize, allRecords);
		int currentPage = pageBean.judgeCurrentPageIsLegal(current, totalPages);
		if (currentPage == 0) {
			return null;
		}
		int startRecord = pageBean.calculateCurrentPageStartRecord(pageSize,
				currentPage);
		pageBean.init(allRecords, currentPage, pageSize, totalPages);
		List<TestBean> list = testBeanDao.list(pageSize, startRecord);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listBean", list);
		map.put("pageBean", pageBean);
		return map;
	}

}
