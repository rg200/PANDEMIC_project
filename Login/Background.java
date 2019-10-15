package login;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Background extends JFrame {

	JScrollPane scrollPane;
	ImageIcon icon;

	JButton login = new JButton("Login"); // �α��� ��ư
	JButton exit = new JButton("EXIT"); // ������ ��ư
	JButton join = new JButton("ȸ������"); // ȸ������ ��ư
	JButton find = new JButton("ID/PWD ã��"); // ID/PWD ã�� ��ư

	public Background() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH); // ��üȭ��
		setUndecorated(true); // ������ �� ���ֱ�
		setVisible(true);

		icon = new ImageIcon("C:\\image\\background.png");

		// ��� Panel ������ �������������� ����
		JPanel background = new JPanel() {

			public void paintComponent(Graphics g) {

				Dimension d = getSize();

				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null); // ������Ʈ ����� �°�

				setOpaque(false); // �׸��� ǥ���ϰ� ����,�����ϰ� ����
				super.paintComponent(g);
			}

		};

		scrollPane = new JScrollPane(background);
		setContentPane(scrollPane);

		JPanel loginmenu = new JPanel();

		JLabel id = new JLabel("ID ");
		JLabel pwd = new JLabel("PWD ");

		JTextField idtext = new JTextField();
		JTextField pwdtext = new JTextField();

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new joinE();
			}
		});

		loginmenu.add(login); // ������ ���� �߰�
		loginmenu.add(exit);
		loginmenu.add(join);
		loginmenu.add(find);
		loginmenu.add(id);
		loginmenu.add(pwd);

		Font RLfont, ChatFont, PFfont; // ��Ʈ �߰� ���ʴ�� ����,ä��,������

		RLfont = new Font("Serif", Font.BOLD, 50);

		// ChatTest.setSize(500, 500);

		setLayout(null);

		login.setBounds(1200, 525, 100, 100);
		exit.setBounds(1190, 700, 120, 60);
		join.setBounds(800, 700, 85, 50);
		find.setBounds(930, 700, 140, 50);

		id.setBounds(730, 500, 100, 100);
		pwd.setBounds(730, 560, 100, 100);

		idtext.setBounds(790, 530, 300, 30);
		pwdtext.setBounds(790, 590, 300, 30);

		background.setBounds(0, 0, 1960, 1080);

		login.setOpaque(false); // �����ϰ�

		exit.setOpaque(false);
		// exit.setBorderPainted(false);// �ܰ������ֱ�
		// exit.setContentAreaFilled(false);// ���뿵�� ä��� ���ֱ�
		// exit.setFocusPainted(false);// ���É����� �׵θ� ��� ����

		join.setOpaque(false);
		join.setBorderPainted(false);
		// join.setContentAreaFilled(false);
		join.setFocusPainted(false);

		find.setOpaque(false);
		find.setBorderPainted(false);
		// find.setContentAreaFilled(false);
		find.setFocusPainted(false);

		add(login);
		add(exit);
		add(join);
		add(find);
		add(id);
		add(pwd);
		add(idtext);
		add(pwdtext);

		add(background);

		setVisible(true); // �ذ���� ä��â ũ������, �������ֱ�(����)

	}
}

class joinE extends JFrame {

	public joinE() {

		JButton Jexit = new JButton("exit");
		setTitle("ȸ������");
		setSize(500, 600);
		setLocation(0, 120);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Jexit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		Jexit.setBounds(100,10,100,50);
		getContentPane().add(Jexit);
		setVisible(true);

	}
}

/*
 * @Override 
 * public void actionPerformed(ActionEvent e) { // TODO Auto-generated
 * method stub
 * 
 * if(e.getSource() == join) je = new joinE(e.getActionCommand() + "��ư");
 * 
 * 
 * }
 * 
 * }
 * 
 * class joinE extends JDialog{
 * 
 * JLabel jlb = new JLabel("");
 * 
 * public joinE(String str){
 * 
 * getContentPane().add(jlb);
 * 
 * 
 * 
 * jlb.setText(str.toString());
 * 
 * 
 * 
 * this.setSize(200,100);
 * 
 * this.setModal(true);
 * 
 * this.setVisible(true);
 * 
 * 
 * 
 * }
 * 
 * }
 */
