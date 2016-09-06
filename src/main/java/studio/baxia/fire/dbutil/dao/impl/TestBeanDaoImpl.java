package studio.baxia.fire.dbutil.dao.impl;

import java.util.List;

import studio.baxia.fire.dbutil.dao.ITestBeanDao;
import studio.baxia.fire.dbutil.pojo.TestBean;

public class TestBeanDaoImpl extends BaseMySQLDaoImpl<TestBean> implements
		ITestBeanDao {
	// 该实现类操作的对应数据库的表,此处设置的数据库表名是对应实体的名字的小写
	// private String tableName = TestBean.class.getSimpleName().toLowerCase();
	// private String tableName ="testbean";
	/**
	 * 获取该实现类操作的对应数据库的表名,此处设置的数据库表名是对应实体的名字的小写
	 * 
	 * @return tableName
	 */
	private String getTableName() {
		String name = TestBean.class.getSimpleName().toLowerCase();
		return name;
	}

	@Override
	public int save(TestBean testBean) {
		String insertSql = "insert into " + getTableName()
				+ "(name,saveDate) values(?,?)";
		return super.executeInsert(insertSql, testBean.getName(),
				testBean.getSaveDate());
	}

	@Override
	public int update(TestBean testBean) {
		String updateSql = "update " + getTableName()
				+ " set name=? where id=?";
		return super.executeUpdateAndDelete(updateSql, testBean.getName(),
				testBean.getId());
	}

	@Override
	public int delete(int testBeanId) {
		String deleteSql = "delete from " + getTableName() + " where id=?";
		return super.executeUpdateAndDelete(deleteSql, testBeanId);
	}

	@Override
	public TestBean get(int testBeanId) {
		String getSql = "select * from " + getTableName() + " where id=?";
		return super.executeGet(getSql, testBeanId, TestBean.class);
	}

	@Override
	public List<TestBean> list(int pageSize, int startRecord) {
		String listSql = "select * from " + getTableName() + " limit ?,?";
		return super
				.executeList(listSql, TestBean.class, startRecord, pageSize);
	}

	@Override
	public int countRow() {
		String getCountSql = "select count(*) from " + getTableName();
		return super.getCountRow(getCountSql);
	}

}
