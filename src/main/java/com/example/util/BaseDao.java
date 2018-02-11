/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.example.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 所有Component Service实现类的基类，用于提供所有Component Service实现类的基本特征功能
 * 
 * @author JackDou
 * @since 2016-5-27
 */
public abstract class BaseDao<M, M1, E> extends SqlSessionDaoSupport {

	protected Class<M> mapperClass;

	protected Class<M1> extMapperClass;

	protected Class<E> entityClass;

	protected SqlSessionFactory sqlSessionFactory;

	private static final Logger logger = LoggerFactory.getLogger(BaseDao.class);

	/**
	 * 构造方法
	 */
	protected BaseDao() {
		setMapperClass();
		setEntityClass();
	}

	/**
	 * 子类必须实现的方法，用于指定Mapper的Class类型
	 */
	protected abstract void setMapperClass();

	/**
	 * 子类必须实现的方法，用于指定实体类的Class类型
	 */
	protected abstract void setEntityClass();

	/**
	 * 指定Mybatis代码生成器生成的Mapper类的Class类型
	 */
	protected void setMapperClass(Class<M> mapperClass) {
		this.mapperClass = mapperClass;
	}

	/**
	 * 指定自定义的Mapper类的Class类型
	 */
	protected void setExtMapperClass(Class<M1> extMapperClass) {
		this.extMapperClass = extMapperClass;
	}

	/**
	 * 指定实体类的Class类型
	 * 
	 * @param entityClass
	 *            实体类Class类型
	 */
	protected void setEntityClass(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * 获取DAO关联的XxxMapper对象
	 * 
	 * @return DAO关联的XxxMapper对象
	 */
	public M getMapper() {
		return this.getSqlSession().getMapper(mapperClass);
	}

	/**
	 * 获取DAO关联的Xxx1Mapper对象
	 * 
	 * @return DAO关联的Xxx1Mapper对象
	 */
	public M1 getExtMapper() {
		return this.getSqlSession().getMapper(extMapperClass);
	}

	/**
	 * 获取JDBC Connection对象
	 * 
	 * @return JDBC Connection对象
	 */
	public Connection getJDBCConnection() {
		return this.getSqlSession().getConnection();
	}

	protected long getRecordTotal(String whereclause) {
		long recordTotal = 0;
		Connection conn = this.getSqlSession().getConnection();

		String tableName = PageUtils
				.underscoreName(entityClass.getSimpleName());
		StringBuilder sb = new StringBuilder("select count(*) from ")
				.append(tableName);
		if (StringUtils.isNotBlank(whereclause)) {
			sb.append(" where 1=1 ").append(whereclause);
		}
		try (Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sb.toString())) {
			if (rs.next()) {
				recordTotal = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return recordTotal;
	}

	protected long getSID() {
		int recordTotal = 0;
		Connection conn = this.getSqlSession().getConnection();

		String tableName = PageUtils
				.underscoreName(entityClass.getSimpleName());
		String sequenceName = tableName + "_S";
		StringBuilder sb = new StringBuilder("select ").append(sequenceName)
				.append(".nextVal from dual");
		try (Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sb.toString())) {
			if (rs.next()) {
				recordTotal = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return recordTotal;
	}

	/**
	 * 将结果集resultSet映射成为javabean
	 * 
	 * @param rs
	 *            数据库查询数据的结果集
	 * @param clazz
	 *            需要映射成为的javaBean的class
	 * @return 一个携带javaBean的List
	 * @throws Exception
	 */
	private List<E> convert2BeanList(ResultSet rs, Class<E> clazz,
			String dateFormatStr) {
		Field[] fields = clazz.getDeclaredFields();
		List<E> list = new ArrayList<E>();
		try {
			while (rs.next()) {
				E obj = null;
				try {
					obj = clazz.newInstance();
				} catch (InstantiationException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				}
				for (Field f : fields) {
					String name = f.getName();
					Class<?> type = f.getType();
					Method method = null;
					try {
						method = clazz
								.getMethod(
										"set"
												+ name.replaceFirst(name
														.substring(0, 1), name
														.substring(0, 1)
														.toUpperCase()), type);
						// TODO: DateUtils.parseSqlDate2UtilDate is not found
						// if ("Date".equals(type.getSimpleName())) {
						// java.util.Date udate =
						// DateUtils.parseSqlDate2UtilDate(rs.getDate(name));
						// method.invoke(obj, (Object)udate);
						// } else {
						// method.invoke(obj,
						// ConvertUtils.convert(rs.getString(name), type));
						// }
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Resource
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
}
