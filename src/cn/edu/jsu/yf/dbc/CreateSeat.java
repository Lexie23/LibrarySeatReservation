package cn.edu.jsu.yf.dbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class CreateSeat {

	
	 //随机返回指定范围的整数
    public static int  getNum(int start,int end) {
    	//Math.random()随机返回0.0至1.0之间的数
        return (int)(Math.random()*(end-start+1)+start);
    }
    
    public static StringBuilder getId() {
//    	String id=String.valueOf(getNum(8005,10005));
//		return id;   
    	StringBuilder id=new StringBuilder(String.valueOf(getNum(8005,10005)));//随机取后3位
    	if(id.length()==1) {
    		id=id.insert(0, "000");//如果是1位数，前面增加2个0
    		//id=id.append(id);
    	}else if(id.length()==2) {
    		id=id.insert(0, "00");//如果是2位数，前面增加1个0
    		//id=id.append(id);
    	}else if(id.length()==3) {
    		//	id=id.append(id);
    		id=id.insert(0, "0");   	
    	}  	
    	return id;
    }
    public static String getRoom() {
    	String room;
    	return String.valueOf(getNum(501,521));
    }
    public static String getSitting() {
    	int i=getNum(0,1);
    	if(i==0)
    		return "无人";
    	else 
    	return "有人";
    }
    public static void addInfo() {//增加学生信息到数据库中
    	DatabaseConnectionSql dbcs=new DatabaseConnectionSql();//使用定义的连接数据库的类，连接数据库
    	String sql="insert into seat(seatid,roomid,sitting) values(?,?,?)";//使用占位符定义插入语句
    	try(Connection conn=dbcs.getConnection();//获取数据库
    		PreparedStatement pstmt=conn.prepareStatement(sql);){//实例化PreparedStatement
        	ArrayList<String> alist=new ArrayList<String>();//定义集合
    		for(int i=1;i<=2000;) {
    			String xh=getId().toString();//随机获取学号
    			if(!alist.contains(xh)) {//判断学号是否唯一
    				alist.add(xh);//将学号加入集合
    				String xm=getRoom();//随机获取姓名
    				String xb=getSitting();
    				
    				pstmt.setString(1, xh);//定义第1个占位符的内容
    	    		pstmt.setString(2, xm);//定义第2个占位符的内容
    	    		pstmt.setString(3, xb);//定义第3个占位符的内容
    	    		
    	    		pstmt.addBatch();//加入批处理等待执行
    				i++;//学号唯一，循环继续往下执行
    			}
    		}
    		pstmt.executeBatch();//批量执行插入操作
    		JOptionPane.showMessageDialog(null, "sucess");
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
    public static void main(String[] args) {
    	addInfo();
      }
}
