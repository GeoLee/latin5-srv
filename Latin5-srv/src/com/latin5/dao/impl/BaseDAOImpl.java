

package com.latin5.dao.impl;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.dao.support.DaoSupport;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.util.Assert;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.latin5.dao.BaseDAO;

public class BaseDAOImpl extends DaoSupport implements BaseDAO{
	
	private SqlMapClient mainSqlMapClient;
	private SqlMapClientTemplate mainSqlMapClientTemplate; //读数据。
	
	private SqlMapClient slaveSqlMapClient;
	private SqlMapClientTemplate slaveSqlMapClientTemplate;//写数据。
	
	
	
	public SqlMapClient getMainSqlMapClient() {
		return mainSqlMapClient;
	}
	public void setMainSqlMapClient(SqlMapClient mainSqlMapClient) {
		this.mainSqlMapClient = mainSqlMapClient;
		this.mainSqlMapClientTemplate=new SqlMapClientTemplate(mainSqlMapClient);
	}
	public SqlMapClient getSlaveSqlMapClient() {
		return slaveSqlMapClient;
	}
	public void setSlaveSqlMapClient(SqlMapClient slaveSqlMapClient) {
		this.slaveSqlMapClient = slaveSqlMapClient;
		this.slaveSqlMapClientTemplate=new SqlMapClientTemplate(slaveSqlMapClient);
	}
	/*public SqlMapClientTemplate getMainSqlMapClientTemplate() {
		return mainSqlMapClientTemplate;
	}
	public SqlMapClientTemplate getSlaveSqlMapClientTemplate() {
		return slaveSqlMapClientTemplate;
	}*/
	@Override
	protected void checkDaoConfig() throws IllegalArgumentException {
		Assert.notNull("sqlSessionFactory must be not null");
		
	} 

	public boolean update(String statementName,Map<String, Object> paramMap)throws Exception{
		return slaveSqlMapClientTemplate.update(statementName, paramMap)>0;
	}
	
	public Object insert(String statementName,Object paramObject)throws Exception{
		return slaveSqlMapClientTemplate.insert(statementName, paramObject);
	}
	public List getList(String statementName,Map<String, Object> paramMap)throws Exception{
		return mainSqlMapClientTemplate.queryForList(statementName, paramMap);
	}
	public Object getObject(String statementName,Map<String ,Object>paramMap)throws Exception{
		return mainSqlMapClientTemplate.queryForObject(statementName, paramMap);
	}
	public boolean delete(String statementName,Map<String ,Object> paramMap)throws Exception{
		return slaveSqlMapClientTemplate.delete(statementName, paramMap)>0;
	}
	@SuppressWarnings("unchecked")
	public void batchInsert(final String statementName,final List dataList)throws Exception{
		this.slaveSqlMapClientTemplate.execute(new SqlMapClientCallback() { 
            public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException { 
                executor.startBatch(); 
                for (int i = 0; i < dataList.size(); i++) { 
                	Object object = (Object) dataList.get(i); 
                    executor.insert(statementName, object); 
                } 
                executor.executeBatch(); 
                return null; 
            } 
        }); 
	}
}

