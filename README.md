# fire.dbutil

&emsp;&emsp;一个基于jdbc的并且能够操作Java Bean和DatabaseTable的工具

## 文档 ##

<h3>如何使用？ </h3>

&emsp;&emsp;直接下载target目录下的fire.dbutil.jar作为jar包导入到项目中即可使用，注意还需要加入mysql-jdbc的jar。


&emsp;&emsp;下图所示便是该项目的源码结构：  

![项目结构](http://i.imgur.com/CyUekQY.png)

&emsp;&emsp;目前主要关注图中选中的两个Java类文件，其中 <code><b>DBUtility.Java</b></code> 为JDBC获取和关闭数据库连接的工具类；<code><b>BaseMySQLDaoImpl.java</b></code> 则是程序的DAO实现类需要继承的 <i>基础MySQL操作</i>（包括插入、删除、修改、获取单条或多条数据的操作）类。

&emsp;&emsp;在src/main/resources目录下面的db.properties文件则是数据库基本信息配置文件，如果用户没有在src目录（Java项目）或者src/main/resources目录（Maven项目）下配置该文件，该工具则会使用jar包中自带的db.properties，即如下图所示的信息。用户只要将自己配置的db.properties放置在src目录（这里指Java的源代码文件路径）即可覆盖掉jar工具自带的db.properties。

![数据库基本信息配置文件](http://i.imgur.com/qfcDdmh.png)  

&emsp;&emsp;在src/main/java目录下面还存在其他的一些package和class，主要是用来演示该工具的使用方法。

<h3>实例演示？ </h3>

&emsp;&emsp;下面新建一个项目来对该工具jar进行测试。

&emsp;&emsp;测试项目结构如下：  

![测试项目结构](http://i.imgur.com/wwtf9Qd.png)

&emsp;&emsp;实体Bean代码如下：  

	package bean;
	
	public class Ttt {
		private int id;
		private String title;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		@Override
		public String toString() {
			return "Ttt [id=" + id + ", title=" + title + "]";
		}
	}  

&emsp;&emsp;实体对应的操作数据库表的Dao实现类如下：

	package dao;
	
	import java.util.List;
	
	import studio.baxia.fire.dbutil.dao.impl.BaseMySQLDaoImpl;
	import bean.Ttt;
	
	public class TttDaoImpl extends BaseMySQLDaoImpl<Ttt> {
		/**
		 * 获取该实现类操作的对应数据库的表名,此处设置的数据库表名是对应实体的名字的小写
		 * @return tableName
		 */
		private String getTableName() {
			String name = Ttt.class.getSimpleName().toLowerCase();
			return name;
		}
	
		public int save(Ttt testBean) {
			String insertSql = "insert into " + getTableName()
					+ "(title) values(?)";
			return super.executeInsert(insertSql, testBean.getTitle());
		}
	
		public int update(Ttt testBean) {
			String updateSql = "update " + getTableName()
					+ " set title=? where id=?";
			return super.executeUpdateAndDelete(updateSql, testBean.getTitle(),
					testBean.getId());
		}
	
		public int delete(int testBeanId) {
			String deleteSql = "delete from " + getTableName() + " where id=?";
			return super.executeUpdateAndDelete(deleteSql, testBeanId);
		}
	
		public Ttt get(int testBeanId) {
			String getSql = "select * from " + getTableName() + " where id=?";
			return super.executeGet(getSql, testBeanId, Ttt.class);
		}
	
		public List<Ttt> list(int pageSize, int startRecord) {
			String listSql = "select * from " + getTableName() + " limit ?,?";
			return super.executeList(listSql, Ttt.class, startRecord, pageSize);
		}
	
		public int countRow() {
			String getCountSql = "select count(*) from " + getTableName();
			return super.getCountRow(getCountSql);
		}
	
	}

&emsp;&emsp;然后便是测试的类：

	package testdbutil;
	
	import java.sql.Connection;
	import java.util.List;
	
	import org.junit.Test;
	
	import studio.baxia.fire.dbutil.util.DBUtil;
	import bean.Ttt;
	import dao.TttDaoImpl;
	
	public class TestDBUtil {
		private TttDaoImpl tttDao = new TttDaoImpl();
	
		@Test
		public void testSave() {
			Ttt ttt = new Ttt();
			ttt.setTitle("title" + (int) (Math.random() * 100));
			int uid = tttDao.save(ttt);
			ttt.setId(uid);
			System.out.println(ttt.toString());
			System.out.println("|| The result of Test " + this.getClass() + "."
					+ new Throwable().getStackTrace()[0].getMethodName() + " is : "
					+ uid);
		}
	
		@Test
		public void testUpdate() {
			Ttt ttt = new Ttt();
			ttt.setId(3);
			ttt.setTitle("update to title" + (int) (Math.random() * 100));
			System.out.println(ttt.toString());
			int result = tttDao.update(ttt);
			System.out.println("|| Test " + this.getClass() + "."
					+ new Throwable().getStackTrace()[0].getMethodName()
					+ " Result : " + result);
		}
	
		@Test
		public void testDelete() {
			int result = tttDao.delete(12);
			System.out.println("|| Test " + this.getClass() + "."
					+ new Throwable().getStackTrace()[0].getMethodName()
					+ " Result : " + result);
		}
	
		@Test
		public void testGet() {
			Ttt ttt = tttDao.get(3);
			if (ttt != null) {
				System.out.println("|| Test " + this.getClass() + "."
						+ new Throwable().getStackTrace()[0].getMethodName()
						+ " Result : " + ttt.toString());
			} else {
				System.out.println("**Error**:查找失败！");
			}
	
		}
	
		@Test
		public void testList() {
			List<Ttt> list = tttDao.list(2, 1);
			if (list != null) {
				System.out.println("|| Test " + this.getClass() + "."
						+ new Throwable().getStackTrace()[0].getMethodName()
						+ " Result : ");
				for (Ttt ttt : list) {
					System.out.println(ttt.toString());
				}
			} else {
				System.out.println("|| **Error**:查找失败！");
			}
	
		}
	
		@Test
		public void testCount() {
			long count = tttDao.countRow();
			System.out.println("|| The result of Test " + this.getClass() + "."
					+ new Throwable().getStackTrace()[0].getMethodName() + " is : "
					+ count);
		}
	
		public static void main(String[] args) {
			Connection conn = DBUtil.getConnection();
			DBUtil.closeConnection(conn, null, null);
		}
	}



&emsp;&emsp;数据库采用的是mysql默认的test，注意在里面建立与实体Ttt对应的表ttt（id,title），注意需要配置id为主键自增模式。

&emsp;&emsp;测试获取和关闭数据库连接，即运行main方法：

![测试获取和关闭数据库连接](http://i.imgur.com/TJ6pyW0.png)

&emsp;&emsp;接着测试一下插入操作，结果如下，成功：

![](http://i.imgur.com/yZ54Mpt.png)

&emsp;&emsp;修改操作，成功：

![](http://i.imgur.com/AagLNCK.png)

&emsp;&emsp;获取单条操作，成功：

![](http://i.imgur.com/5QxibWN.png)

&emsp;&emsp;分页获取操作，成功：

![](http://i.imgur.com/0zrDUKo.png)

&emsp;&emsp;删除操作，成功：

![](http://i.imgur.com/AyWwPkr.png)

&emsp;&emsp;获取总记录数，成功：

![](http://i.imgur.com/kDnUjp1.png)

&emsp;&emsp;经过以上测试，可以看到上述功能都是健全的，如果有bug欢迎各位提出，谢谢各位宝贵的意见。

<h3>源码解释？ </h3>

&emsp;&emsp;正在来的路上 。。。  ^_^ 

<br/><br/>
<hr>

&emsp;&emsp;A tool based on jdbc and it can operate entity with table .

## Document ##


&emsp;&emsp;The figure below shows the project source structure.

![项目结构](http://i.imgur.com/CyUekQY.png)



# 如果觉得好，就请支持一下，谢谢！  

<div>
<div  style="float:left;"><img src="http://onxe6sbvc.bkt.clouddn.com/alpay.jpg" width="200px"></img></div>

<div  style="float:left;margin-left:50px"><img src="http://onxe6sbvc.bkt.clouddn.com/wxpay.png" width="200px" ></img></div>

</div>
