package cn.edu.jsu.yf.dbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class StuCode{

public final static String[] LOWER_CASES = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
public final static String[] UPPER_CASES = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
public final static String[] NUMS_LIST = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
public final static String[] SYMBOLS_ARRAY = {"!","~","^","_","*"};
 
/**
 * �����������
 * @param pwd_len ���볤��
 * @return ������ַ���
 */
public static int  getNum(int start,int end) {
	//Math.random()�������0.0��1.0֮�����
    return (int)(Math.random()*(end-start+1)+start);
}

//�������ѧ��
public static StringBuilder getStuno() {//��ʹ��String����Ϊ��Ҫ����ƴ���ַ���
	StringBuilder xh=new StringBuilder("2017273");//ѧ��ǰ7λ��ͬ
	StringBuilder xh1=new StringBuilder(String.valueOf(getNum(1,999)));//���ȡ��3λ
	if(xh1.length()==1) {
		xh1=xh1.insert(0, "00");//�����1λ����ǰ������2��0
		xh=xh.append(xh1);//ǰ6λ���3λƴ�ӳ�ѧ��
	}else if(xh1.length()==2) {
		xh1=xh1.insert(0, "0");//�����2λ����ǰ������1��0
		xh=xh.append(xh1);
	}else
		xh=xh.append(xh1);
	return xh;
}
public static String genRandomPwd(int pwd_len) {
    if ( pwd_len < 6 || pwd_len >20 ) {
        return "";
    }
    int lower = pwd_len / 2 ;
 
    int upper = (pwd_len - lower) /2;
 
    int num =  (pwd_len - lower) / 2;
 
    int symbol = pwd_len - lower - upper - num ;
    
    StringBuffer pwd = new StringBuffer();
    Random random = new Random();
    int position = 0;
    while((lower + upper + num + symbol) > 0 ){
        if(lower > 0){
            position = random.nextInt(pwd.length() + 1);
 
            pwd.insert(position,LOWER_CASES[random.nextInt(LOWER_CASES.length)]);
            lower--;
        }
        if(upper > 0){
            position = random.nextInt(pwd.length()  + 1);
 
            pwd.insert(position,UPPER_CASES[random.nextInt(UPPER_CASES.length)]);
            upper--;
        }
        if(num > 0){
            position = random.nextInt(pwd.length()  + 1);
 
            pwd.insert(position,NUMS_LIST[random.nextInt(NUMS_LIST.length)]);
            num--;
        }
        if(symbol > 0){
            position = random.nextInt(pwd.length()  + 1);
 
            pwd.insert(position,SYMBOLS_ARRAY[random.nextInt(SYMBOLS_ARRAY.length)]);
            symbol--;
        }
 
    }
 
 
    return pwd.toString();
}

public static void addInfo() {//����ѧ����Ϣ�����ݿ���
	DatabaseConnectionSql dbcs=new DatabaseConnectionSql();//ʹ�ö�����������ݿ���࣬�������ݿ�
	String sql="insert into stuid(xh,code) values(?,?)";//ʹ��ռλ������������
	try(Connection conn=dbcs.getConnection();//��ȡ���ݿ�
		PreparedStatement pstmt=conn.prepareStatement(sql);){//ʵ����PreparedStatement
    	ArrayList<String> alist=new ArrayList<String>();//���弯��
		for(int i=1;i<=800;) {
			String xh=getStuno().toString();//�����ȡѧ��
			if(!alist.contains(xh)) {//�ж�ѧ���Ƿ�Ψһ
				alist.add(xh);//��ѧ�ż��뼯��
				String xm=genRandomPwd(getNum(6,10));//�����ȡ����
				
				pstmt.setString(1, xh);//�����1��ռλ��������
	    		pstmt.setString(2, xm);//�����2��ռλ��������
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