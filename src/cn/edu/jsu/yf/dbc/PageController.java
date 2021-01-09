package cn.edu.jsu.yf.dbc;
import java.util.Vector;

import cn.edu.jsu.yf.dao.SeatSql;
/**
 * 
 * @author YaN
 *
 */
public class PageController {
	private static Vector<Vector> bigList ; // �󼯺ϣ������(���ݿ�)��ȡ����
	private Vector<Vector> smallList = new Vector<Vector>(); // С���ϣ����ظ�����������
	private static int curentPageIndex = 1; // ��ǰҳ��
	private static int  countPerpage = 5; // ÿҳ��ʾ����
	private int pageCount; // ��ҳ��
	private int recordCount; // �ܼ�¼����
	{// ��ʼ�������
		if(PageController.bigList==null) {
			PageController.bigList=SeatSql.getSelectAll("select * from seat");// ���ò�ѯ���ݿ�ķ������������е���
		}
		//�������ݿ��е���������ȡ��ҳ��
		if(bigList.size()%countPerpage==0) {
			pageCount=bigList.size()/countPerpage;
		}else {
			pageCount=bigList.size()/countPerpage+1;
		}
	}
	public PageController() {}//�޲ι��췽��

	public PageController(int curentPageIndex) {//���췽�����õ�ǰҳ
		this.curentPageIndex = curentPageIndex;
	}
	public void setCountPerpage(int countPerpage) {//���췽������ÿҳ��ʾ�ļ�¼��
		this.countPerpage=countPerpage;
	}
	public PageController(String sql) {//���췽��ִ�в�ѯ�����в���
		PageController.bigList=SeatSql.getSelectAll(sql);
	}
	/**
	 * 
	 * @param sql����sql���ִ�����ݸ��²���
	 */
	public void addInStuSeat(String sql) {		
		SeatSql.update(sql);
	}
	public boolean search() {
		if(this.bigList.isEmpty())
		return false;
		else
		return true;
	}
	public Vector<Vector> getPaegData() {// ���ݵ�ǰҳ����ɸѡ��¼
		recordCount = bigList.size();//�����¼��Ϊ���ݿ��б����������
		for(int i = (curentPageIndex-1)*countPerpage;i<curentPageIndex*countPerpage&&i<recordCount; i++) {//ȡ�õ�ǰҳ���ļ�¼��curentPageIndex��ǰҳ����countPerpageÿҳ��ʾ�ļ�¼��
			smallList.add(bigList.get(i));//����¼���뵽С������
		}
		return smallList;//����С���ϣ���ǰҳ�����ݣ�
	}
	
	public Vector<Vector> nextPage(){//��һҳ
		if(curentPageIndex<pageCount) {//��ǰҳС����ҳ��ʱ��ǰҳ����һ����'��һҳ'
			curentPageIndex++;
		}else {
			curentPageIndex=1;
		}
		return getPaegData();//������һҳ������
	}
	
	public Vector<Vector> prePage(){//��һҳ
		if(curentPageIndex>1) {
			curentPageIndex--;
		}else {
			curentPageIndex=pageCount;
		}
		return getPaegData();//������һҳ������
	}
}
