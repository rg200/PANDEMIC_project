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

	JButton login = new JButton("Login"); // 로그인 버튼
	JButton exit = new JButton("EXIT"); // 나가기 버튼
	JButton join = new JButton("회원가입"); // 회원가입 버튼
	JButton find = new JButton("ID/PWD 찾기"); // ID/PWD 찾기 버튼

	public Background() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH); // 전체화면
		setUndecorated(true); // 맨위에 줄 없애기
		setVisible(true);

		icon = new ImageIcon("C:\\image\\background.png");

		// 배경 Panel 생성후 컨텐츠페인으로 지정
		JPanel background = new JPanel() {

			public void paintComponent(Graphics g) {

				Dimension d = getSize();

				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null); // 컴포넌트 사이즈에 맞게

				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
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
		}); // 나가기 버튼 누르면 창 꺼짐

		join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new joinE();
			}
		}); // 회원가입 누르면 회원가입 창 불러옴

		loginmenu.add(login); // 내정보 제목 추가
		loginmenu.add(exit);
		loginmenu.add(join);
		loginmenu.add(find);
		loginmenu.add(id);
		loginmenu.add(pwd);

		Font RLfont, ChatFont, PFfont; // 폰트 추가 차례대로 방목록,채팅,내정보

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

		login.setOpaque(false); // 투명하게

		exit.setOpaque(false);
		// exit.setBorderPainted(false);// 외곽선없애기
		// exit.setContentAreaFilled(false);// 내용영역 채우기 없애기
		// exit.setFocusPainted(false);// 선택瑛뻑 테두리 사용 안함

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

		setVisible(true); // 해결과제 채팅창 크기조절, 배경사진넣기(전부)

	}
}

class joinE extends JFrame // 회원가입 창 만드는 클래스 {

	public joinE() {

		JButton Jexit = new JButton("exit");
		setTitle("회원가입");
		setSize(500, 600);
		setLocation(0, 120);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Jexit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});//회원가입 창에 있는 버튼 누르면 창 종료
		
		Jexit.setBounds(100,10,100,50);
		getContentPane().add(Jexit);
		setVisible(true);

	}
}

