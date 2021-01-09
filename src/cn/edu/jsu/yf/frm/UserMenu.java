package cn.edu.jsu.yf.frm;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.jsu.yf.dbc.PageController;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
/**
 * 用户主界面
 * @author YaN
 *
 */
public class UserMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserMenu frame = new UserMenu();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserMenu() {
		setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 17));
		setBackground(SystemColor.inactiveCaptionText);
		setTitle("\u7528\u6237\u4E3B\u754C\u9762");
		setForeground(Color.DARK_GRAY);
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserMenu.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 467, 308);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u9884\u8BA2\u5EA7\u4F4D");
		menu.setIcon(new ImageIcon(UserMenu.class.getResource("/com/sun/javafx/scene/control/skin/modena/dialog-more-details.png")));
		menu.setFont(new Font("等线", Font.BOLD, 20));
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("\u9884\u8BA2");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BookSeat book=new BookSeat();
				book.setVisible(true);
				book.setLocationRelativeTo(null);
			}
		});
		menuItem.setIcon(new ImageIcon(UserMenu.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlayDisabled.png")));
		menuItem.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		menu.add(menuItem);
		
		JSeparator separator = new JSeparator();
		menu.add(separator);
		
		JMenuItem menuItem_1 = new JMenuItem("\u9000\u8BA2");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelSeat cancel=new CancelSeat();
				cancel.setVisible(true);
				cancel.setLocationRelativeTo(null);
			}
		});
		menuItem_1.setIcon(new ImageIcon(UserMenu.class.getResource("/com/sun/javafx/scene/web/skin/DrawHorizontalLine_16x16_JFX.png")));
		menuItem_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		menu.add(menuItem_1);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\YaN\\Pictures\\Saved Pictures\\welcom.PNG"));
		lblNewLabel.setBounds(0, 0, 449, 235);
		contentPane.add(lblNewLabel);
	}
}
