package cn.edu.jsu.yf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import cn.edu.jsu.yf.dbc.DatabaseConnectionSql;
/**
 * �����ݿ�����ݲ���
 * @author YaN
 *
 */
public class SeatSql {
	/**
	 * 
	 * @param sql��ѯ���
	 * @return ����ѯ�������ݼ�¼��ӵ�vector������
	 */
	 public static Vector<Vector> getSelectAll(String sql){//����sql���������ݿ�������Ҫ��������Ϣ
	    	Vector<Vector> rows=new Vector<Vector>();//����Ҫ���ص����м�¼����
	    	DatabaseConnectionSql dbcs=new DatabaseConnectionSql();//ʹ�ö�����������ݿ����
	    	try(Connection conn=dbcs.getConnection();//��ȡ���ݿ��
	    		PreparedStatement pstmt=conn.prepareStatement(sql);){//ʵ����PreparedStatement
	    		ResultSet rs=pstmt.executeQuery();//ִ�в�ѯ��䣬����ŵ����ݼ���
	    		while(rs.next()) {//�������ݼ�
	    			Vector row=new Vector();//����������
	    			row.add(rs.getString(1));//��ȡ��һ���ֶ�
	    			row.add(rs.getString(2));//��ȡ�ڶ����ֶ�
	    			row.add(rs.getString(3));//��ȡ�������ֶ�
	    			rows.add(row);//����������ӵ���¼������
	    		}
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    	return rows;//��������������
	    }
	 /**
	  * ����sql�����и��²�������ɾ�����޸ģ����ӵ�
	  * @param sql sql���
	  */
	public static void update(String sql) {
		DatabaseConnectionSql dbcs=new DatabaseConnectionSql();//ʹ�ö�����������ݿ����
    	try(Connection conn=dbcs.getConnection();
    		PreparedStatement pstmt=conn.prepareStatement(sql);){//ʵ����PreparedStatement   		
    		pstmt.executeUpdate();//ִ�в�ѯ���
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
	}
	
	 
}
