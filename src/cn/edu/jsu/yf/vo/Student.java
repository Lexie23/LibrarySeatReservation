package cn.edu.jsu.yf.vo;
/**
 * 学生信息类
 * @author YaN
 *
 */
public class Student {
	   private String xh;
	   private String xm;
	   private String xb;
	   private String zy;
	   private String bj;
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getZy() {
		return zy;
	}
	public void setZy(String zy) {
		this.zy = zy;
	}
	public String getBj() {
		return bj;
	}
	public void setBj(String bj) {
		this.bj = bj;
	}
	public Student(String xh, String xm, String xb, String zy, String bj) {
		super();
		this.xh = xh;
		this.xm = xm;
		this.xb = xb;
		this.zy = zy;
		this.bj = bj;
	}
	public Student() {}
	@Override
	public String toString() {
		return "Student [xh=" + xh + ", xm=" + xm + ", xb=" + xb + ", zy=" + zy + ", bj=" + bj + "]";
	}
    
}
