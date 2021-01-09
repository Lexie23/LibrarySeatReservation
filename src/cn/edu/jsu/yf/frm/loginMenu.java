package cn.edu.jsu.yf.frm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Choice;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

import java.awt.ScrollPane;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Font;
import javax.swing.ImageIcon;

public class loginMenu extends JFrame {

	private JPanel contentPane;
	private JTextField txtid;
	private JPasswordField txtcode;
    private String id;
	/**
	 * Launch the application.
	 */
   
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginMenu frame = new loginMenu();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Create the frame.
	 */
	public loginMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelchoice = new JLabel("��ѡ����ݣ�");
		labelchoice.setFont(new Font("΢���ź�", Font.ITALIC, 16));
		labelchoice.setBounds(156, 27, 100, 18);
		contentPane.add(labelchoice);
		
		JLabel labelid = new JLabel("\u8D26\u53F7\uFF1A");
		labelid.setFont(new Font("΢���ź�", Font.ITALIC, 16));
		labelid.setBounds(184, 69, 72, 18);
		contentPane.add(labelid);
		
		JLabel labelcode = new JLabel("\u5BC6\u7801\uFF1A");
		labelcode.setFont(new Font("΢���ź�", Font.ITALIC, 16));
		labelcode.setBounds(184, 121, 72, 18);
		contentPane.add(labelcode);
		
		txtid = new JTextField();
		txtid.setBounds(231, 67, 131, 24);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		JRadioButton rbmanager = new JRadioButton("����Ա");//ѡ�����Ա��¼	
		rbmanager.setFont(new Font("���Ĳ���", Font.PLAIN, 16));
		rbmanager.setBounds(252, 24, 94, 27);
		contentPane.add(rbmanager);
		
		JRadioButton rbuser = new JRadioButton("�û�");//ѡ���û���¼
		rbuser.setFont(new Font("���Ĳ���", Font.PLAIN, 16));
		rbuser.setBounds(342, 24, 80, 27);
		contentPane.add(rbuser);
		
		ButtonGroup group=new ButtonGroup();
		group.add(rbmanager);
		group.add(rbuser);
		
		JLabel lbltip = new JLabel("");
		lbltip.setBounds(184, 150, 238, 18);
		contentPane.add(lbltip);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\YaN\\Pictures\\Saved Pictures\\user.PNG"));
		lblNewLabel.setBounds(26, 27, 113, 112);
		contentPane.add(lblNewLabel);
		
		JButton btnlogin = new JButton("��¼");//�����¼��ť
		btnlogin.setFont(new Font("����", Font.PLAIN, 20));
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbmanager.isSelected()) {    //��ѡ�й���Ա��ѡ���򵯳�����Ա����
					if(txtid.getText().length()==0) {
						lbltip.setText("�˺Ų���Ϊ�գ�");
						lbltip.requestFocus();				
					}
					else {
					File file=new File("d:/Account/manager.txt");
					try(
						FileWriter fw=new FileWriter(file,true);
						FileReader fr=new FileReader(file);
						BufferedReader br=new BufferedReader(fr);){
						String row=null;//����
						String[] arrs=null;//ÿ�е�����
						while((row=br.readLine())!=null) {
							arrs=row.split(",");
							if(arrs[0].contains(txtid.getText())&&arrs[1].contains(txtcode.getText())) {
								JOptionPane.showMessageDialog(null, "��¼�ɹ���");
								ManagerMenu mng=new ManagerMenu();
				                mng.setVisible(true);
								mng.setLocationRelativeTo(null);
				                dispose();
				             //   return;
							}
							else 							
						    lbltip.setText("�˺Ż������������������");
														
					}
						}catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}				   
				}	}
				else if(rbuser.isSelected()) {//��ѡ���û���ѡ���򵯳��û�����
					if(txtid.getText().length()==0) {
						lbltip.setText("�˺Ų���Ϊ�գ�");
						lbltip.requestFocus();				
					}
					else {
					File file=new File("d:/Account/user.txt");
					try(
						FileWriter fw=new FileWriter(file,true);
						FileReader fr=new FileReader(file);
						BufferedReader br=new BufferedReader(fr);){
						String row=null;
						String[] arrs=null;
						while((row=br.readLine())!=null) {
							arrs=row.split(",");
							if(arrs[0].contains(txtid.getText())&&arrs[1].contains(txtcode.getText())) {
								JOptionPane.showMessageDialog(null, "��¼�ɹ���");
								UserMenu user=new UserMenu();
								user.setVisible(true);							
								user.setLocationRelativeTo(null);
								dispose();
							}
							else {
								lbltip.setText("�˺Ż������������������");
							}							
						}				
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					}
				}

			}
		});
		btnlogin.setBounds(184, 167, 113, 41);
		contentPane.add(btnlogin);
	
		JButton btncancel = new JButton("ȡ��");//���ȡ����ť�˳�����
		btncancel.setIcon(new ImageIcon(loginMenu.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		btncancel.setFont(new Font("����", Font.PLAIN, 20));
		btncancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});		
		btncancel.setBounds(311, 167, 113, 41);
		contentPane.add(btncancel);
		
		txtcode = new JPasswordField();
		txtcode.setBounds(233, 119, 129, 24);
		contentPane.add(txtcode);	
	}
}
