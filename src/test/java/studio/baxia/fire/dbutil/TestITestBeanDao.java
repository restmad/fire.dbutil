package studio.baxia.fire.dbutil;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import studio.baxia.fire.dbutil.dao.ITestBeanDao;
import studio.baxia.fire.dbutil.dao.impl.TestBeanDaoImpl;
import studio.baxia.fire.dbutil.pojo.TestBean;

/**
 * @author phn
 * @date 2015-4-8
 * @TODO 测试TestBeanDao的方法
 */
public class TestITestBeanDao {

	ITestBeanDao testBeanDao = new TestBeanDaoImpl();

	 @Test
	public void testSave() {
		TestBean testBean = new TestBean();
		testBean.setName("name" + (int) (Math.random() * 100));
		testBean.setSaveDate(new Date());
		int uid = testBeanDao.save(testBean);
		testBean.setId(uid);
		System.out.println(testBean.toString());
		System.out.println("|| The result of Test " + this.getClass() + "."
				+ new Throwable().getStackTrace()[0].getMethodName()
				+ " is : " + uid);
	}

	 @Test
	public void testUpdate() {
		TestBean testBean = new TestBean();
		testBean.setId(3);
		testBean.setName("name update to name" + (int) (Math.random() * 100));
		System.out.println(testBean.toString());
		int result = testBeanDao.update(testBean);
		System.out.println("|| Test " + this.getClass() + "."
				+ new Throwable().getStackTrace()[0].getMethodName()
				+ " Result : " + result);
	}

	 @Test
	public void testDelete() {
		int result = testBeanDao.delete(12);
		System.out.println("|| Test " + this.getClass() + "."
				+ new Throwable().getStackTrace()[0].getMethodName()
				+ " Result : " + result);
	}

	@Test
	public void testGet() {
		TestBean testBean = testBeanDao.get(3);
		if (testBean != null) {
			System.out.println("|| Test " + this.getClass() + "."
					+ new Throwable().getStackTrace()[0].getMethodName()
					+ " Result : " + testBean.toString());
		} else {
			System.out.println("**Error**:查找失败！");
		}

	}

	@Test
	public void testList() {
		List<TestBean> list = testBeanDao.list(2,1);
		if (list != null) {
			System.out.println("|| Test " + this.getClass() + "."
					+ new Throwable().getStackTrace()[0].getMethodName()
					+ " Result : ");
			for(TestBean testBean:list){
				System.out.println(testBean.toString());
			}
		} else {
			System.out.println("|| **Error**:查找失败！");
		}

	}
	@Test
	public void testCount(){
		long count = testBeanDao.countRow();
		System.out.println("|| The result of Test " + this.getClass() + "."
				+ new Throwable().getStackTrace()[0].getMethodName()
				+ " is : "+count);
	}
}
