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

import cn.edu.jsu.yf.dao.SeatSql;
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
import javax.swing.UIManager;
/**
 * Ԥ����λ������
 * @author YaN
 *
 */
public class BookSeat extends JFrame {
	private JPanel contentPane;// ���崰��������壬���ø����
	private JTable table;// ������
	private JTextField txtKey;//������ҹؼ����ı���
	private DefaultTableModel model;// ����������ģ��
	private TableRowSorter sorter;//����������
	private ArrayList<RowSorter.SortKey> sortKeys;//�����������
	
	private Vector<String> titles;
	private static JTextField txtid;

	public static void main(String[] args) {
		BookSeat frame = new BookSeat();// ʵ��������
		frame.setLocationRelativeTo(null);// �������
		frame.setVisible(true);// ����ɼ�
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * ���幹�췽���������弰���.
	 */
	public BookSeat() {
		setFont(new Font("����-���������ַ���", Font.BOLD, 20));
		setTitle("\u7528\u6237\u754C\u9762-\u9884\u5B9A\u5EA7\u4F4D");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// ���ô���رհ�ť
		setBounds(100, 100, 534, 568);// ���ô���λ�����С
		contentPane = new JPanel();// ʵ�����������
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// �������߿�
		contentPane.setLayout(null);// ������岼��Ϊ���Բ���
		setContentPane(contentPane);// ������Ĭ�����

		// ���ù������
		JScrollPane scrollPane = new JScrollPane();// �����������
		scrollPane.setBounds(24, 96, 461, 363);// ���ô�С��λ��
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
		lblroom.setBounds(14, 13, 139, 23);
		contentPane.add(lblroom);
		
		txtKey = new JTextField();
		txtKey.setBounds(141, 13, 87, 26);
		contentPane.add(txtKey);
		txtKey.setColumns(10);
		
		//������Ұ�ť
		JButton btnFind = new JButton("����");
		btnFind.setBackground(UIManager.getColor("Button.darkShadow"));
		btnFind.setFont(new Font("��������", Font.BOLD, 16));
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key=txtKey.getText().trim();//��ȡ����ؼ����ı����ֵ
				PageController pc=new PageController("select * from seat where roomid='"+key+"'");
				model=new DefaultTableModel(pc.getPaegData(),titles);
				table.setModel(model);	
			}
		});
		btnFind.setBounds(242, 11, 95, 25);
		contentPane.add(btnFind);
		
		JButton btnPre = new JButton("��һҳ");
		btnPre.setFont(new Font("��Բ", Font.PLAIN, 15));
		btnPre.addActionListener(new ActionListener() {//��һҳ�����¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				model=new DefaultTableModel(new PageController().prePage(),titles);//��������ģ���е�����Ϊ��һҳ����
				table.setModel(model);//���ñ�������ģ��
				
			}
		});
		btnPre.setBounds(44, 472, 95, 25);
		contentPane.add(btnPre);
		
		JButton btnNext = new JButton("��һҳ");
		btnNext.setFont(new Font("��Բ", Font.PLAIN, 15));
		btnNext.addActionListener(new ActionListener() {//��һҳ�����¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				model=new DefaultTableModel(new PageController().nextPage(),titles);//��������ģ���е�����Ϊ��һҳ����
				table.setModel(model);//���ñ�������ģ��
			}
		});
		btnNext.setBounds(178, 472, 95, 25);
		contentPane.add(btnNext);
		
		JLabel lblMsg = new JLabel("ÿҳ��ʾ��");
		lblMsg.setFont(new Font("����ϸ��", Font.BOLD | Font.ITALIC, 15));
		lblMsg.setBounds(301, 472, 87, 20);
		contentPane.add(lblMsg);
		
		JComboBox comboBox = new JComboBox(new Integer[] {3,5,10,15,20});
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
		comboBox.setBounds(374, 472, 55, 23);
		contentPane.add(comboBox);
		
		JButton btnbook = new JButton("Ԥ��");//���Ԥ����ť
		btnbook.setBackground(UIManager.getColor("Button.darkShadow"));
		btnbook.setFont(new Font("��������", Font.BOLD, 16));
		btnbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			String id=txtid.getText().trim();//ȡ����������λ���	
			String sql="select * from seat where seatid='"+id+"'";
			PageController pc=new PageController(sql);//��ʾ�鵽����λ��Ϣ
			if(!new SeatSql().getSelectAll(sql+"and sitting='����'").isEmpty()) {
				JOptionPane.showMessageDialog(null, "��λ�Ѿ���Ԥ����");				
			}else {
		    pc.addInStuSeat("update seat set sitting='����' where seatid='"+id+"'");//�޸���λ״̬
			PageController pc1=new PageController(sql);
			pc1.addInStuSeat("insert into tempseat select *from seat where seat.seatid='"+id+"'");
			JOptionPane.showMessageDialog(null, "Ԥ���ɹ���");
		    model=new DefaultTableModel(pc1.getPaegData(),titles);
			table.setModel(model);				
			}}
		});
		btnbook.setBounds(242, 48, 95, 27);
		contentPane.add(btnbook);
		
		txtid = new JTextField();
		txtid.setBounds(141, 49, 86, 24);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		JLabel labelid = new JLabel("��������λ��ţ�");
		labelid.setFont(new Font("����ϸ��", Font.ITALIC, 16));
		labelid.setBounds(14, 47, 128, 23);
		contentPane.add(labelid);

	}
}

