package studio.baxia.fire.dbutil;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import studio.baxia.fire.dbutil.service.ITestBeanService;
import studio.baxia.fire.dbutil.service.impl.TestBeanServiceImpl;

/**
 * @author phn
 * @date 2015-4-12
 * @TODO 
 */
public class TestITestBeanService {
	private ITestBeanService userService = new TestBeanServiceImpl();

	@Test
	public void testPagingList() {
		Map<String , Object> map = new HashMap<String,Object>();
		int  pageSize = 2;
		int currentPage = 1;
		map = userService.pagingLists(pageSize, currentPage);
		System.out.println("|| Test " + this.getClass() + "."
				+ new Throwable().getStackTrace()[0].getMethodName()
				+ " Result : "+map.toString());
	}

}
