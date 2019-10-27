package pandemic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Lobby extends JPanel {

	Image background = new ImageIcon(Client.class.getResource("../Lobby_Image/background.png")).getImage();
	Socket gsocket, csocket;
	DataInputStream input;
	DataOutputStream output;
	
	

	public Lobby(Socket gsocket, Socket csocket) {
		this.gsocket = gsocket;
		this.csocket = csocket;
		
		try {
			input = new DataInputStream(gsocket.getInputStream());
			output = new DataOutputStream(gsocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		setSize(1920, 1080);
		setLayout(null);
		add(new Profile()).setBounds(190, 730, 310, 320);
		add(new RoomList()).setBounds(475, 170, 1000, 465);
		add(new Chat(csocket)).setBounds(510, 730, 1230, 320);
		setVisible(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}
}

class Profile extends JPanel {
	public Profile() {
		setLayout(null);
		JLabel label1 = new JLabel("������"); // ������ â ����?
		// this.setBounds(190, 730, 310, 320);//�ĺ���
		this.setOpaque(false); // �ǳ� �Ⱥ��̰��ϱ�
		// this.setBackground(Color.green);//�ĺ���
		this.add(label1);
		label1.setBounds(0, 0, 100, 70);
		setVisible(true);
	}
}

class RoomList extends JPanel {
	ImageIcon Make = new ImageIcon(Client.class.getResource("../Lobby_Image/Make.png"));
	ImageIcon Search = new ImageIcon(Client.class.getResource("../Lobby_Image/Search.png"));
	ImageIcon Re = new ImageIcon(Client.class.getResource("../Lobby_Image/Refresh.png"));

	public RoomList() {
		setLayout(null);
		JButton RoomMake = new JButton(Make); // �游��� ��ư�߰�
		JButton RoomSearch = new JButton(Search); // ��ã�� ��ư�߰�
		JButton Refresh = new JButton(Re); // ���ΰ�ħ��ư �߰�

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
		RoomMake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new makeRoom();
			}
		});
		// this.setBounds(475, 170, 1000, 465);//�ĺ���
		
		this.setOpaque(false);
		this.add(RoomMake); // �游��� ��ư ����
		this.add(RoomSearch); // ��ã�� ��ư ����
		this.add(Refresh); // ���ΰ�ħ ��ư ����
		RoomMake.setBounds(0, 0, 250, 100);
		RoomSearch.setBounds(270, 0, 410, 100);
		Refresh.setBounds(700, 0, 300, 100);
		// this.setBackground(Color.blue);//�ĺ���
		setVisible(true);
	}
}

class makeRoom extends JFrame { // �游��� ������ �ߴ� â

	public makeRoom() {
		setTitle("�游���");
		setSize(500, 300);
		this.setBackground(Color.green);
		setResizable(false);
		setLocationRelativeTo(null);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}

class MkRoomBg extends JPanel { // �游��� â�� ���� ��� �г�
	public MkRoomBg() {
		JLabel label = new JLabel("123");
		this.add(label);
		this.setBackground(Color.green);
		this.add(new MKRP());
		// this.setBounds(0, 0, 500, 300);
		setVisible(true);
	}
}

class MKRP extends JPanel { // �游��� â�� ����� �г�
	public MKRP() {
		JLabel RoomID = new JLabel("�� �̸� "); // ���̸� ������� ����
		JLabel RoomPW = new JLabel("��й�ȣ"); // ��й�ȣ ������� ����
		RoomID.setBounds(100, 100, 50, 50);
		RoomPW.setBounds(150, 150, 50, 50);
		this.add(RoomID);
		this.add(RoomPW);

	}
}

class Chat extends JPanel {
	DataOutputStream output;
	
	Runnable ChatRun;
	Thread ChatTh;
	
	
	public Chat(Socket csocket) {
		
		setLayout(null);
		// setBounds(510, 730, 1230, 320);//�ĺ���
		this.setOpaque(false); // �ǳ� �Ⱥ��̰��ϱ�
		JTextField ChatField = new JTextField(); // ä��ġ�� �ʵ�
		JTextArea ChatList = new JTextArea(20, 20); // ä���� ǥ�õǴ� ����
		ChatList.setEditable(false);
		JScrollPane scroll;
		scroll = new JScrollPane(ChatList);
		this.add(scroll);
		this.add(ChatField);
		
		try {
			output = new DataOutputStream(csocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ChatRun = new ClientReceiverThread(csocket, ChatList);
		ChatTh = new Thread(ChatRun);
		ChatTh.start();
		
		
		ChatField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField t = (JTextField) e.getSource();
				//ChatList.append(t.getText() + "\n");
				try {
					output.writeUTF("[ä��]"+t.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				t.setText("");
			}
		});
		this.setOpaque(false); // �ǳ� �Ⱥ��̰��ϱ�
		scroll.setBounds(0, 0, 1230, 285);
		// ChatList.setBounds(0, 0, 1200, 200);
		ChatField.setBounds(0, 290, 1230, 30);
		setVisible(true);
	}
}
