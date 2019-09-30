package testServer;

import java.io.DataInputStream;

/*
 * ������ �����ϰ� ä���� �Ҽ��ְ� �Ѵ�.
 */

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

public class GameRoom extends MainThread{
	private int roomNum = 0;
	private HashMap<String, DataOutputStream> list = null;//���� ����Ʈ �������
	private DataInputStream input = null;
	private Socket s;
	private String name; 
	
	public GameRoom() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * ���⼭�� ���� ��ȣ�� �г����� �ް�
	 * ��ü �� ����Ʈ���� �� ��ȣ�� ���� �� ��ü�� ������ ����
	 * ��ü ���� ����Ʈ ��ü���� �г��ӿ� ���� ������ ������ �´�.
	 */
	
	public GameRoom(int num, String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
		//���� ���ϰ� �Է½�Ʈ���� �޴´�.
		s = LobbyServer.userList.get(name)[0];
		try {
			input = new DataInputStream(s.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//�ش� �� ��ȣ�� ���� �밴ü�ȿ� �������� ����Ʈ�� ������´�.
		roomNum = num;
		list = MainServer.roomList.get(roomNum).getUserList();
		
		/*
		 * �� �κк��� ���� �����̴�.
		 * �ϴ� ä�ø� ������! 
		 */
		try {
			while(true) {
				String str = input.readUTF();   // Ŭ���̾�Ʈ�κ��� ä�� �޾ƿ�
				if(input == null) break;   // �Է��� �ƹ��͵� ������ ������ Ż��
				sendAll(name+">"+str);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//���� ����Ʈ�� ���� �Է� ������ ������.
	public void sendAll(String str) {
		for(DataOutputStream out : list.values()) {
			try {
				out.writeUTF(str);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}