package testServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Hashtable;

public class ChatServer implements Runnable{
	//ä�� �����̴�
	private int roomNum = 0;
	private Hashtable<String, DataOutputStream> list = null;//���� ����Ʈ �������
	private DataInputStream input = null;
	private Socket s;
	private String name; 
	
	public ChatServer() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * ���⼭�� ���� ��ȣ�� �г����� �ް�
	 * ��ü �� ����Ʈ���� �� ��ȣ�� ���� �� ��ü�� ������ ����
	 * ��ü ���� ����Ʈ ��ü���� �г��ӿ� ���� ������ ������ �´�.
	 */
	
	public ChatServer(int num, String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
		//���� ���ϰ� �Է½�Ʈ���� �޴´�.
		s = LobbyServer.userList.get(name)[1];
		try {
			input = new DataInputStream(s.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//�ش� �� ��ȣ�� ���� �밴ü�ȿ� �������� ����Ʈ�� ������´�.
		roomNum = num;
		list = MainServer.roomList.get(roomNum).getUserListChat();
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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		/*
		 * �ϴ� ä�ø� ������! 
		 * Ŭ�󿡼� ������ �±׸� �̿�?
		 * ä���� [ä��]
		 * ����� [����]
		 * �̷���?
		 */
		try {
			while(true) {
				
				String str = input.readUTF();   // Ŭ���̾�Ʈ�κ��� ä�� �޾ƿ�
				if(str.substring(0,4).equals("[����]") && str.substring(4).equals("stop")) {
					break;
				}
				else {
					str = str.substring(4);
				}
				if(input == null) break;   // �Է��� �ƹ��͵� ������ ������ Ż��
				sendAll(name+">"+str);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			MainServer.roomList.get(roomNum).RoomUserListDel(name);//�濡 ���� ����
			System.out.println(MainServer.roomList.get(roomNum).getUserListChat());
			if(!MainServer.roomList.get(roomNum).getUserList().isEmpty())
				MainServer.roomList.remove(roomNum);
		}
	}

}
