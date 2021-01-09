package cn.edu.jsu.yf.vo;
import java.io.Serializable;

/**
 * 座位信息类
 * @author YaN
 *
 */
public class Seat implements Serializable{
	private String seatid;//座位编号
	private String roomid;//所属房间号
	private Boolean sitting;//座位的状态
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
		return "座位编：=" + seatid + ", 房间号：" + roomid+"状态："+sitting;
	}
	public boolean isBooked() {
		return true;
	}
	public boolean unBooked() {
		return false;
	}
}