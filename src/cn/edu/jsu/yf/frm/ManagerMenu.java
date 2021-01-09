package cn.edu.jsu.yf.frm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import cn.edu.jsu.yf.dbc.Demo;
import cn.edu.jsu.yf.dbc.PageController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
/**
 * ����Ա����
 * @author YaN
 *
 */
public class ManagerMenu extends JFrame {
	private JPanel contentPane;// ���崰��������壬���ø����
	private JTable table;// ������
	private JTextField txtKey;//������ҹؼ����ı���
	private DefaultTableModel model;// ����������ģ��
	private TableRowSorter sorter;//����������
	private ArrayList<RowSorter.SortKey> sortKeys;//�����������
	
	private Vector<String> titles;
	private JTextField txtid;
	private JTextField txtseat;
	private JTextField txtroomid;

	public static void main(String[] args) {
		ManagerMenu frame = new ManagerMenu();// ʵ��������
		frame.setLocationRelativeTo(null);// �������
		frame.setVisible(true);// ����ɼ�
	}

	/**
	 * ���幹�췽���������弰���.
	 */
	public ManagerMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���ô���رհ�ť
		setBounds(100, 100, 534, 646);// ���ô���λ�����С
		contentPane = new JPanel();// ʵ�����������
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// �������߿�
		contentPane.setLayout(null);// ������岼��Ϊ���Բ���
		setContentPane(contentPane);// ������Ĭ�����

		// ���ù������
		JScrollPane scrollPane = new JScrollPane();// �����������
		scrollPane.setBounds(31, 171, 452, 377);// ���ô�С��λ��
		contentPane.add(scrollPane);// �����������뵽���������

		// ʹ�ö�̬�������ݣ��б����������ݣ�
		titles = new Vector<String>();// ���嶯̬�����ʾ������
		Collections.addAll(titles, "��λ���", "�����","״̬");
		Vector<Vector> seatInfo = new PageController().getPaegData();//��ȡ��һҳ������

//		ʹ�þ�̬���ݴ���DefaultTableModel����ģ��
		model = new DefaultTableModel(seatInfo, titles) {// ʹ��Vectorװ�ر������ģ�ͣ���дgetColumnClass������ʵ�ְ����е�������������
			public Class getColumnClass(int column) {//��ȡ�е�����
				Class returnValue;
				if ((column >= 0) && (column < getColumnCount())) {
					returnValue = getValueAt(0, column).getClass();
				} else {
					returnValue = Object.class;
				}
				return returnValue;
			}
		};
		table = new JTable(model);// ʹ��DefaultTableModel����ģ��ʵ�������
		sorter = new TableRowSorter<DefaultTableModel>(model);//����������
		table.setAutoCreateRowSorter(true);;//���ñ���Զ�����

		scrollPane.setViewportView(table);// ����ʹ�ù��������ʾ��������ʹ�ù��������ʾ��������б����޷���ʾ
		
		JLabel lblroom = new JLabel("���뷿��Ų��ң�");
		lblroom.setFont(new Font("����ϸ��", Font.ITALIC, 16));
		lblroom.setBounds(14, 13, 136, 23);
		contentPane.add(lblroom);
		
		txtKey = new JTextField();
		txtKey.setBounds(141, 13, 87, 26);
		contentPane.add(txtKey);
		txtKey.setColumns(10);
		
		//������Ұ�ť
		JButton btnFind = new JButton("����");
		btnFind.setFont(new Font("��������", Font.BOLD, 16));
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key=txtKey.getText().trim();//��ȡ����ؼ����ı����ֵ
				PageController pc=new PageController("select * from seat where roomid='"+key+"'");
				model=new DefaultTableModel(pc.getPaegData(),titles);
				table.setModel(model);	
			}
		});
		btnFind.setBounds(242, 14, 95, 25);
		contentPane.add(btnFind);
		
		JButton btnPre = new JButton("��һҳ");
		btnPre.setFont(new Font("��������", Font.PLAIN, 20));
		btnPre.addActionListener(new ActionListener() {//��һҳ�����¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				model=new DefaultTableModel(new PageController().prePage(),titles);//��������ģ���е�����Ϊ��һҳ����
				table.setModel(model);//���ñ�������ģ��
				
			}
		});
		btnPre.setBounds(41, 554, 95, 38);
		contentPane.add(btnPre);
		
		JButton btnNext = new JButton("��һҳ");
		btnNext.setFont(new Font("��������", Font.PLAIN, 20));
		btnNext.addActionListener(new ActionListener() {//��һҳ�����¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				model=new DefaultTableModel(new PageController().nextPage(),titles);//��������ģ���е�����Ϊ��һҳ����
				table.setModel(model);//���ñ�������ģ��
			}
		});
		btnNext.setBounds(173, 554, 95, 38);
		contentPane.add(btnNext);
		
		JLabel lblMsg = new JLabel("ÿҳ��ʾ��");
		lblMsg.setFont(new Font("����ϸ��", Font.ITALIC, 16));
		lblMsg.setBounds(294, 561, 87, 20);
		contentPane.add(lblMsg);
		
		JComboBox comboBox = new JComboBox(new Integer[] {3,5,10,15,20});
		comboBox.setFont(new Font("����", Font.PLAIN, 17));
		comboBox.addItemListener(new ItemListener() {//ҳ��������ѡ��ı��¼�
			public void itemStateChanged(ItemEvent e) {
				int pageSize=Integer.valueOf(comboBox.getSelectedItem().toString());//��ȡ��������ѡ��ֵ
				PageController pcl=new PageController();
				pcl.setCountPerpage(pageSize);//����ÿҳ��ʾ��¼����
				model=new DefaultTableModel(pcl.getPaegData(),titles);//��������ģ��
				table.setModel(model);//���ñ������ģ��
			}
		});
		comboBox.setSelectedIndex(1);//����������Ĭ��ֵ
		comboBox.setBounds(366, 562, 55, 23);
		contentPane.add(comboBox);
		
		JButton btndelete = new JButton("ɾ��");
		btndelete.setFont(new Font("��������", Font.BOLD, 16));
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			String key=txtid.getText();
			PageController pc=new PageController("select * from seat where seatid='"+key+"'");//��ʾ�鵽����λ��Ϣ
			pc.addInStuSeat("delete from seat where seatid='"+key+"'");
			model=new DefaultTableModel(pc.getPaegData(),titles);//��������ģ��
			table.setModel(model);//���ñ������ģ��
			JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
            txtid.setText("");
			}
		});
		btndelete.setBounds(242, 48, 95, 27);
		contentPane.add(btndelete);
		
		txtid = new JTextField();
		txtid.setBounds(141, 49, 86, 24);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		JLabel labelid = new JLabel("��������λ��ţ�");
		labelid.setFont(new Font("����ϸ��", Font.ITALIC, 16));
		labelid.setBounds(14, 47, 136, 23);
		contentPane.add(labelid);
		
		JButton btnadd = new JButton("�������λ");
		btnadd.setFont(new Font("��������", Font.PLAIN, 16));
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String seat=txtseat.getText();
			String room=txtroomid.getText();
			PageController pc=new PageController("select * from seat where seatid='"+seat+"'");
			if(pc.search()) {
				JOptionPane.showMessageDialog(null, "��λ���Ѿ����ڣ�");
			}
			else {
			pc.addInStuSeat("insert into seat values('"+seat+"','"+room+"','����')");
			model=new DefaultTableModel(pc.getPaegData(),titles);//��������ģ��
			table.setModel(model);
			JOptionPane.showMessageDialog(null, "��ӳɹ���");
            txtseat.setText("");
            txtroomid.setText("");
			}
			
			}
		});
		btnadd.setBounds(230, 105, 130, 53);
		contentPane.add(btnadd);
		
		JButton btnupdate = new JButton("����");
		btnupdate.setFont(new Font("��������", Font.PLAIN, 16));
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PageController pc=new PageController("select * from seat");
				model=new DefaultTableModel(pc.getPaegData(),titles);//��������ģ��
				table.setModel(model);
			}
		});
		btnupdate.setBounds(370, 105, 132, 53);
		contentPane.add(btnupdate);
		
		txtseat = new JTextField();
		txtseat.setBounds(103, 105, 95, 24);
		contentPane.add(txtseat);
		txtseat.setColumns(10);
		
		JLabel label = new JLabel("\u5EA7\u4F4D\u7F16\u53F7\uFF1A");
		label.setFont(new Font("����ϸ��", Font.ITALIC, 16));
		label.setBounds(14, 105, 87, 21);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u6240\u5C5E\u623F\u95F4\u53F7\uFF1A");
		label_1.setFont(new Font("����ϸ��", Font.PLAIN, 16));
		label_1.setBounds(14, 134, 113, 23);
		contentPane.add(label_1);
		
		txtroomid = new JTextField();
		txtroomid.setBounds(103, 134, 95, 24);
		contentPane.add(txtroomid);
		txtroomid.setColumns(10);
	}
}

