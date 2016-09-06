/**
 * 
 */
package studio.baxia.fire.dbutil.dao;

import java.util.List;

import studio.baxia.fire.dbutil.pojo.TestBean;

/**
 * @author Pan
 *
 */
public interface ITestBeanDao {

	int save(TestBean testBean);

	int update(TestBean testBean);

	int delete(int testBeanId);

	TestBean get(int testBeanId);

	List<TestBean> list(int pageSize, int startRecord);

	int countRow();
}
