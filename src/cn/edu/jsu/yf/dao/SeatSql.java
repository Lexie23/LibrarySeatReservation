package cn.edu.jsu.yf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import cn.edu.jsu.yf.dbc.DatabaseConnectionSql;
/**
 * 对数据库的数据操作
 * @author YaN
 *
 */
public class SeatSql {
	/**
	 * 
	 * @param sql查询语句
	 * @return 将查询到的数据记录添加到vector集合中
	 */
	 public static Vector<Vector> getSelectAll(String sql){//根据sql语句查找数据库中所需要的数据信息
	    	Vector<Vector> rows=new Vector<Vector>();//定义要返回的所有记录集合
	    	DatabaseConnectionSql dbcs=new DatabaseConnectionSql();//使用定义的连接数据库的类
	    	try(Connection conn=dbcs.getConnection();//获取数据库接
	    		PreparedStatement pstmt=conn.prepareStatement(sql);){//实例化PreparedStatement
	    		ResultSet rs=pstmt.executeQuery();//执行查询语句，结果放到数据集中
	    		while(rs.next()) {//遍历数据集
	    			Vector row=new Vector();//定义行数据
	    			row.add(rs.getString(1));//获取第一个字段
	    			row.add(rs.getString(2));//获取第二个字段
	    			row.add(rs.getString(3));//获取第三个字段
	    			rows.add(row);//将行数据添加到记录集合中
	    		}
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    	return rows;//返回所有行数据
	    }
	 /**
	  * 根据sql语句进行更新操作，如删除，修改，增加等
	  * @param sql sql语句
	  */
	public static void update(String sql) {
		DatabaseConnectionSql dbcs=new DatabaseConnectionSql();//使用定义的连接数据库的类
    	try(Connection conn=dbcs.getConnection();
    		PreparedStatement pstmt=conn.prepareStatement(sql);){//实例化PreparedStatement   		
    		pstmt.executeUpdate();//执行查询语句
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
	}
	
	 
}
