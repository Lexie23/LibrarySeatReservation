package cn.edu.jsu.yf.frm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.jsu.yf.dao.SeatSql;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;

public class CancelSeat extends JFrame {

	private JPanel contentPane;
	private JTextField txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CancelSeat frame = new CancelSeat();
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
	public CancelSeat() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 232);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txt = new JTextField();
		txt.setBounds(14, 60, 408, 50);
		contentPane.add(txt);
		txt.setColumns(10);
		txt.setText(new SeatSql().getSelectAll("select *from tempseat").toString());
		
		JLabel label = new JLabel("\u60A8\u9884\u8BA2\u7684\u5EA7\u4F4D\u4E3A\uFF1A");
		label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.ITALIC, 18));
		label.setBounds(14, 32, 159, 27);
		contentPane.add(label);
		
		JButton btncancel = new JButton("È·¶¨ÍË¶©");
		btncancel.setIcon(null);
		btncancel.setFont(new Font("»ªÎÄçúçê", Font.PLAIN, 15));
		btncancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new SeatSql().update("delete from tempseat");
				JOptionPane.showMessageDialog(null, "ÍË¶©³É¹¦£¡");
                txt.setText("");
			}
		});
		btncancel.setBounds(136, 135, 136, 36);
		contentPane.add(btncancel);
	}
}
