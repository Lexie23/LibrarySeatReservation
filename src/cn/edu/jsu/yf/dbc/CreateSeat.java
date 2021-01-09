package cn.edu.jsu.yf.dbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class CreateSeat {

	
	 //�������ָ����Χ������
    public static int  getNum(int start,int end) {
    	//Math.random()�������0.0��1.0֮�����
        return (int)(Math.random()*(end-start+1)+start);
    }
    
    public static StringBuilder getId() {
//    	String id=String.valueOf(getNum(8005,10005));
//		return id;   
    	StringBuilder id=new StringBuilder(String.valueOf(getNum(8005,10005)));//���ȡ��3λ
    	if(id.length()==1) {
    		id=id.insert(0, "000");//�����1λ����ǰ������2��0
    		//id=id.append(id);
    	}else if(id.length()==2) {
    		id=id.insert(0, "00");//�����2λ����ǰ������1��0
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
    		return "����";
    	else 
    	return "����";
    }
    public static void addInfo() {//����ѧ����Ϣ�����ݿ���
    	DatabaseConnectionSql dbcs=new DatabaseConnectionSql();//ʹ�ö�����������ݿ���࣬�������ݿ�
    	String sql="insert into seat(seatid,roomid,sitting) values(?,?,?)";//ʹ��ռλ������������
    	try(Connection conn=dbcs.getConnection();//��ȡ���ݿ�
    		PreparedStatement pstmt=conn.prepareStatement(sql);){//ʵ����PreparedStatement
        	ArrayList<String> alist=new ArrayList<String>();//���弯��
    		for(int i=1;i<=2000;) {
    			String xh=getId().toString();//�����ȡѧ��
    			if(!alist.contains(xh)) {//�ж�ѧ���Ƿ�Ψһ
    				alist.add(xh);//��ѧ�ż��뼯��
    				String xm=getRoom();//�����ȡ����
    				String xb=getSitting();
    				
    				pstmt.setString(1, xh);//�����1��ռλ��������
    	    		pstmt.setString(2, xm);//�����2��ռλ��������
    	    		pstmt.setString(3, xb);//�����3��ռλ��������
    	    		
    	    		pstmt.addBatch();//����������ȴ�ִ��
    				i++;//ѧ��Ψһ��ѭ����������ִ��
    			}
    		}
    		pstmt.executeBatch();//����ִ�в������
    		JOptionPane.showMessageDialog(null, "sucess");
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
    public static void main(String[] args) {
    	addInfo();
      }
}
