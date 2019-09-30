package testServer;

import java.util.HashMap;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Login{
	private HashMap<String, String> IdPassword = new HashMap<String, String>();
	private DataInputStream input = null;
	private Socket gameSocket = null;
	private Socket chatSocket = null;
	private String id;
	private String pw;
	
	public Login(Socket s1, Socket s2) {
		// TODO Auto-generated constructor stub
		gameSocket = s1;
		chatSocket = s2;
		
		//������ ����
		IdPassword.put("admin","1234");
		IdPassword.put("user","1234");
		IdPassword.put("user2","1234");
		IdPassword.put("user3","1234");
		try {
			input = new DataInputStream(gameSocket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public String getName() {
		return id;
	}
	
	public boolean loginCheck() {
		
		try {
			//�Էµ� ���̵�� ����� �ް� ����� �����Ϳ� ���Ѵ�.
			id = input.readUTF();
			pw = input.readUTF();
			if(pw.equals(IdPassword.get(id))) {
				return true;
			}
			else
				return false;
		}catch (Exception e) {
			// TODO: handle exception
			return false;//���Ͽ����� �̻��ϰų� �ȵǸ� �α��� ����
		}
	}
}
