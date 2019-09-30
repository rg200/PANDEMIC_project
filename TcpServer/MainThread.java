package testServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MainThread implements Runnable{
	private Socket gameSocket = null;
	private Socket chatSocket = null;
	private String name;
	private DataInputStream input = null;
	private DataOutputStream output = null;
	private Login LoginServer = null;
	private LobbyServer lobbyServer = null;
	
	public MainThread() {
		// TODO Auto-generated constructor stub
		
	}
	
	public MainThread(Socket s1, Socket s2) {
		// TODO Auto-generated constructor stub
		gameSocket = s1;
		chatSocket = s2;
		try {
			input = new DataInputStream(gameSocket.getInputStream());
			output = new DataOutputStream(gameSocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void SetName(String name) {
		this.name = ""+name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			LoginServer = new Login(gameSocket, chatSocket); //�α��� ��ü ����
			try {
				if(LoginServer.loginCheck()) {//�α��� üũ
					//����
					name = LoginServer.getName();
					output.writeUTF("�α��� ����");
					lobbyServer = new LobbyServer(gameSocket, chatSocket, name);//�����ϸ� �κ� ��ü����
				}else {
					System.out.println("����");
					output.writeUTF("�α��� ����");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("����");
				break;
			}finally {
				
			}
			
		}
	}
	
}
