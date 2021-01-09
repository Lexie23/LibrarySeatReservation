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
 * 生成随机密码
 * @param pwd_len 密码长度
 * @return 密码的字符串
 */
public static int  getNum(int start,int end) {
	//Math.random()随机返回0.0至1.0之间的数
    return (int)(Math.random()*(end-start+1)+start);
}

//随机返回学号
public static StringBuilder getStuno() {//不使用String，因为需要大量拼接字符串
	StringBuilder xh=new StringBuilder("2017273");//学号前7位相同
	StringBuilder xh1=new StringBuilder(String.valueOf(getNum(1,999)));//随机取后3位
	if(xh1.length()==1) {
		xh1=xh1.insert(0, "00");//如果是1位数，前面增加2个0
		xh=xh.append(xh1);//前6位与后3位拼接成学号
	}else if(xh1.length()==2) {
		xh1=xh1.insert(0, "0");//如果是2位数，前面增加1个0
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

public static void addInfo() {//增加学生信息到数据库中
	DatabaseConnectionSql dbcs=new DatabaseConnectionSql();//使用定义的连接数据库的类，连接数据库
	String sql="insert into stuid(xh,code) values(?,?)";//使用占位符定义插入语句
	try(Connection conn=dbcs.getConnection();//获取数据库
		PreparedStatement pstmt=conn.prepareStatement(sql);){//实例化PreparedStatement
    	ArrayList<String> alist=new ArrayList<String>();//定义集合
		for(int i=1;i<=800;) {
			String xh=getStuno().toString();//随机获取学号
			if(!alist.contains(xh)) {//判断学号是否唯一
				alist.add(xh);//将学号加入集合
				String xm=genRandomPwd(getNum(6,10));//随机获取姓名
				
				pstmt.setString(1, xh);//定义第1个占位符的内容
	    		pstmt.setString(2, xm);//定义第2个占位符的内容
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