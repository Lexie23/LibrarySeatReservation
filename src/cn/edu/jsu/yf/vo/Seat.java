package cn.edu.jsu.yf.vo;
import java.io.Serializable;

/**
 * ��λ��Ϣ��
 * @author YaN
 *
 */
public class Seat implements Serializable{
	private String seatid;//��λ���
	private String roomid;//���������
	private Boolean sitting;//��λ��״̬
	public Boolean getSitting() {
		return sitting;
	}
	public void setSitting(Boolean sitting) {
		this.sitting = sitting;
	}
	public String getSeatid() {
		return seatid;
	}
	public void setSeatid(String seatid) {
		this.seatid = seatid;
	}
	public String getRoomid() {
		return roomid;
	}
	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}
	
	public Seat(String seatid, String roomid,boolean sitting) {
		super();
		this.seatid = seatid;
		this.roomid = roomid;
		this.sitting = sitting;
	}
	public Seat() {}
	@Override
	public String toString() {
		return "��λ�ࣺ=" + seatid + ", ����ţ�" + roomid+"״̬��"+sitting;
	}
	public boolean isBooked() {
		return true;
	}
	public boolean unBooked() {
		return false;
	}
}