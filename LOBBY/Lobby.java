
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Lobby {

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.add(new Background());
		f.setSize(1920, 1080);
		f.setResizable(false); // �ڹ� ũ�� ���� X
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}

class Background extends JPanel {
	JScrollPane scrollPane;
	ImageIcon icon = new ImageIcon("C:\\Users\\WIN10\\Desktop\\img\\Lobby3.png");

	public void paintComponent(Graphics g) {
		g.drawImage(icon.getImage(), 0, 0, null);
		setOpaque(false); // �׸��� ǥ���ϰ� ����,�����ϰ� ����
		super.paintComponent(g);
	}

	public Background() {
		setSize(1920, 1080);
		setLayout(null);
		add(new Profile()).setBounds(190, 730, 310, 320);
		add(new RoomList()).setBounds(475, 170, 1000, 465);
		add(new Chat()).setBounds(510, 730, 1220, 320);

		// scrollPane = new JScrollPane(this);
		// setContentPane(scrollPane);
		setVisible(true);

	}
}

class Profile extends JPanel {
	public Profile() {
		setLayout(null);
		JLabel label1 = new JLabel("������"); // ������ â ����?
		// this.setBounds(190, 730, 310, 320);
		this.setOpaque(false); // ������ �ǳ� �Ⱥ��̰��ϱ�
		// this.setBackground(Color.green);
		this.add(label1);
		label1.setBounds(0, 0, 100, 70);
		setVisible(true);
	}
}

class RoomList extends JPanel {
	public RoomList() {
		setLayout(null);
		JButton RoomMake = new JButton(new ImageIcon("C:\\Users\\WIN10\\Desktop\\img\\MKRoom.png")); // �游��� ��ư�߰�
																											// // // �߰�
		JButton RoomSearch = new JButton(new ImageIcon("C:\\Users\\WIN10\\Desktop\\img\\SerRoom.png")); // ��ã�� ��ư�߰�
		JButton Refresh = new JButton(new ImageIcon("C:\\Users\\WIN10\\Desktop\\img\\Re.png")); // ���ΰ�ħ��ư �߰�
		RoomMake.setContentAreaFilled(false); // ��ư ���뿵�� ä�����ʱ�,�̹����� �س����ϱ�
		RoomSearch.setContentAreaFilled(false);
		Refresh.setContentAreaFilled(false);
		RoomMake.setBorderPainted(false); // ��ư �׵θ� ���ֱ�
		RoomSearch.setBorderPainted(false);
		Refresh.setBorderPainted(false);
		RoomMake.setFocusPainted(false); // �������� �׵θ� �ȶ߰�
		RoomSearch.setFocusPainted(false);
		Refresh.setFocusPainted(false);
		// �迭�� ������
		// this.setBounds(475, 170, 1000, 465);
		this.setOpaque(false);
		this.add(RoomMake); // �游��� ��ư ����
		this.add(RoomSearch); // ��ã�� ��ư ����
		this.add(Refresh); // ���ΰ�ħ ��ư ����
		RoomMake.setBounds(0, 0, 250, 100);
		RoomSearch.setBounds(270, 0, 410, 100);
		Refresh.setBounds(700, 0, 300, 100);
		// this.setBackground(Color.blue);
		// ������ �ǳ� �Ⱥ��̰��ϱ�
		setVisible(true);
	}
}

class Chat extends JPanel {
	public Chat() {
		setLayout(null);		
		// setBounds(510, 730, 1220, 320);
		this.setOpaque(false); // ������ �ǳ� �Ⱥ��̰��ϱ�		
		JTextField ChatField = new JTextField(); //ä��ġ�� �ʵ�
		JTextArea ChatList = new JTextArea(); //ä���� ǥ�õǴ� ����		
		this.add(new JLabel("Enter")).setBounds(0, 0, 100, 100);
		this.add(ChatField);
		this.add(new JScrollPane(ChatList));
		ChatField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField t = (JTextField)e.getSource();
				ChatList.append(t.getText()+"\n");
				t.setText("");
			}
		});
		this.setOpaque(false); // ������ �ǳ� �Ⱥ��̰��ϱ�
		ChatList.setBounds(0, 300, 200, 200);
		ChatField.setBounds(0, 290, 1200, 30);
		setVisible(true);
	}
}
