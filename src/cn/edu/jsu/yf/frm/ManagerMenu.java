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
 * 管理员界面
 * @author YaN
 *
 */
public class ManagerMenu extends JFrame {
	private JPanel contentPane;// 定义窗体内容面板，放置各组件
	private JTable table;// 定义表格
	private JTextField txtKey;//定义查找关键字文本框
	private DefaultTableModel model;// 定义表格数据模型
	private TableRowSorter sorter;//定义排序器
	private ArrayList<RowSorter.SortKey> sortKeys;//设置排序规则
	
	private Vector<String> titles;
	private JTextField txtid;
	private JTextField txtseat;
	private JTextField txtroomid;

	public static void main(String[] args) {
		ManagerMenu frame = new ManagerMenu();// 实例化窗体
		frame.setLocationRelativeTo(null);// 窗体居中
		frame.setVisible(true);// 窗体可见
	}

	/**
	 * 定义构造方法创建窗体及组件.
	 */
	public ManagerMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置窗体关闭按钮
		setBounds(100, 100, 534, 646);// 设置窗体位置与大小
		contentPane = new JPanel();// 实例化内容面板
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// 设置面板边框
		contentPane.setLayout(null);// 设置面板布局为绝对布局
		setContentPane(contentPane);// 将窗体默认面板

		// 设置滚动面板
		JScrollPane scrollPane = new JScrollPane();// 创建滚动面板
		scrollPane.setBounds(31, 171, 452, 377);// 设置大小与位置
		contentPane.add(scrollPane);// 将滚动面板加入到内容面板中

		// 使用动态数组数据（列标题与行数据）
		titles = new Vector<String>();// 定义动态数组表示表格标题
		Collections.addAll(titles, "座位编号", "房间号","状态");
		Vector<Vector> seatInfo = new PageController().getPaegData();//获取第一页的数据

//		使用静态数据创建DefaultTableModel数据模型
		model = new DefaultTableModel(seatInfo, titles) {// 使用Vector装载表格数据模型，覆写getColumnClass方法，实现按各列的数据类型排序
			public Class getColumnClass(int column) {//获取列的类型
				Class returnValue;
				if ((column >= 0) && (column < getColumnCount())) {
					returnValue = getValueAt(0, column).getClass();
				} else {
					returnValue = Object.class;
				}
				return returnValue;
			}
		};
		table = new JTable(model);// 使用DefaultTableModel数据模型实例化表格
		sorter = new TableRowSorter<DefaultTableModel>(model);//设置排序器
		table.setAutoCreateRowSorter(true);;//设置表格自动排序

		scrollPane.setViewportView(table);// 设置使用滚动面板显示表格，如果不使用滚动面板显示，则表格的列标题无法显示
		
		JLabel lblroom = new JLabel("输入房间号查找：");
		lblroom.setFont(new Font("华文细黑", Font.ITALIC, 16));
		lblroom.setBounds(14, 13, 136, 23);
		contentPane.add(lblroom);
		
		txtKey = new JTextField();
		txtKey.setBounds(141, 13, 87, 26);
		contentPane.add(txtKey);
		txtKey.setColumns(10);
		
		//定义查找按钮
		JButton btnFind = new JButton("查找");
		btnFind.setFont(new Font("华文琥珀", Font.BOLD, 16));
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key=txtKey.getText().trim();//获取输入关键字文本框的值
				PageController pc=new PageController("select * from seat where roomid='"+key+"'");
				model=new DefaultTableModel(pc.getPaegData(),titles);
				table.setModel(model);	
			}
		});
		btnFind.setBounds(242, 14, 95, 25);
		contentPane.add(btnFind);
		
		JButton btnPre = new JButton("上一页");
		btnPre.setFont(new Font("方正舒体", Font.PLAIN, 20));
		btnPre.addActionListener(new ActionListener() {//上一页单击事件
			@Override
			public void actionPerformed(ActionEvent e) {
				model=new DefaultTableModel(new PageController().prePage(),titles);//设置数据模型中的数据为上一页内容
				table.setModel(model);//设置表格的数据模型
				
			}
		});
		btnPre.setBounds(41, 554, 95, 38);
		contentPane.add(btnPre);
		
		JButton btnNext = new JButton("下一页");
		btnNext.setFont(new Font("方正舒体", Font.PLAIN, 20));
		btnNext.addActionListener(new ActionListener() {//下一页单击事件
			@Override
			public void actionPerformed(ActionEvent e) {
				model=new DefaultTableModel(new PageController().nextPage(),titles);//设置数据模型中的数据为下一页内容
				table.setModel(model);//设置表格的数据模型
			}
		});
		btnNext.setBounds(173, 554, 95, 38);
		contentPane.add(btnNext);
		
		JLabel lblMsg = new JLabel("每页显示：");
		lblMsg.setFont(new Font("华文细黑", Font.ITALIC, 16));
		lblMsg.setBounds(294, 561, 87, 20);
		contentPane.add(lblMsg);
		
		JComboBox comboBox = new JComboBox(new Integer[] {3,5,10,15,20});
		comboBox.setFont(new Font("宋体", Font.PLAIN, 17));
		comboBox.addItemListener(new ItemListener() {//页数下拉框选择改变事件
			public void itemStateChanged(ItemEvent e) {
				int pageSize=Integer.valueOf(comboBox.getSelectedItem().toString());//获取下拉框所选的值
				PageController pcl=new PageController();
				pcl.setCountPerpage(pageSize);//设置每页显示记录条数
				model=new DefaultTableModel(pcl.getPaegData(),titles);//设置数据模型
				table.setModel(model);//设置表格数据模型
			}
		});
		comboBox.setSelectedIndex(1);//设置下拉框默认值
		comboBox.setBounds(366, 562, 55, 23);
		contentPane.add(comboBox);
		
		JButton btndelete = new JButton("删除");
		btndelete.setFont(new Font("华文琥珀", Font.BOLD, 16));
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			String key=txtid.getText();
			PageController pc=new PageController("select * from seat where seatid='"+key+"'");//显示查到的座位信息
			pc.addInStuSeat("delete from seat where seatid='"+key+"'");
			model=new DefaultTableModel(pc.getPaegData(),titles);//设置数据模型
			table.setModel(model);//设置表格数据模型
			JOptionPane.showMessageDialog(null, "删除成功！");
            txtid.setText("");
			}
		});
		btndelete.setBounds(242, 48, 95, 27);
		contentPane.add(btndelete);
		
		txtid = new JTextField();
		txtid.setBounds(141, 49, 86, 24);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		JLabel labelid = new JLabel("请输入座位编号：");
		labelid.setFont(new Font("华文细黑", Font.ITALIC, 16));
		labelid.setBounds(14, 47, 136, 23);
		contentPane.add(labelid);
		
		JButton btnadd = new JButton("添加新座位");
		btnadd.setFont(new Font("华文琥珀", Font.PLAIN, 16));
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String seat=txtseat.getText();
			String room=txtroomid.getText();
			PageController pc=new PageController("select * from seat where seatid='"+seat+"'");
			if(pc.search()) {
				JOptionPane.showMessageDialog(null, "座位号已经存在！");
			}
			else {
			pc.addInStuSeat("insert into seat values('"+seat+"','"+room+"','无人')");
			model=new DefaultTableModel(pc.getPaegData(),titles);//设置数据模型
			table.setModel(model);
			JOptionPane.showMessageDialog(null, "添加成功！");
            txtseat.setText("");
            txtroomid.setText("");
			}
			
			}
		});
		btnadd.setBounds(230, 105, 130, 53);
		contentPane.add(btnadd);
		
		JButton btnupdate = new JButton("更新");
		btnupdate.setFont(new Font("华文琥珀", Font.PLAIN, 16));
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PageController pc=new PageController("select * from seat");
				model=new DefaultTableModel(pc.getPaegData(),titles);//设置数据模型
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
		label.setFont(new Font("华文细黑", Font.ITALIC, 16));
		label.setBounds(14, 105, 87, 21);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u6240\u5C5E\u623F\u95F4\u53F7\uFF1A");
		label_1.setFont(new Font("华文细黑", Font.PLAIN, 16));
		label_1.setBounds(14, 134, 113, 23);
		contentPane.add(label_1);
		
		txtroomid = new JTextField();
		txtroomid.setBounds(103, 134, 95, 24);
		contentPane.add(txtroomid);
		txtroomid.setColumns(10);
	}
}

