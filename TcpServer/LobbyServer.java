package testServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

public class LobbyServer{
	public static HashMap<String, Socket[]> userList = new HashMap<String, Socket[]>();
	private static int RoomNumber = 1;
	private Socket gameSocket = null;
	private Socket chatSocket = null;
	private DataInputStream input = null;
	private String name;
	
	public LobbyServer() {
		
	}
	
	public LobbyServer(Socket s1, Socket s2, String name) {
		// TODO Auto-generated constructor stub
		gameSocket = s1;
		chatSocket = s2;
		
		try {
			input = new DataInputStream(gameSocket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//�κ� ������ ��� �������� ���������� ��´�.
		//�׸��� �г��� �Ǵ� ���̵� �����Ѵ�.
		this.name = name;
		Socket[] s = {gameSocket, chatSocket};
		userList.put(name,s);
		
		/*
		 * �κ� ������ ������ ���� �����ϰų� ���忡 ���� �ݺ����̴�.
		 * �̰� �ٲ�� ������ �ϴ� �����ϸ� create �κ񿡼� ������ exit �׿� �ٸ����� ������ �� �������� ����.
		 * �� ��ȣ�� 1���� ���������� �����ϰ�
		 * �����ϸ� roomNumber�� ���ڷ� ���μ����� roomList�� Ű�� �� ��ȣ ������ �� ��ü�� �����Ѵ�.
		 * �����ϰ�� ���� ����(�� ��ȣ, ���̸�?, ���)�� �޾Ƽ� �׿� �´� room ��ü�� ȣ���Ѵ�. �׸��� ������ �߰��Ѵ�.
		 * 
		 * �� ������ ������ ���ӷ����� �����ϰ� �ȴ�.
		 * ���� �뿡���� �밴ü�ȿ� �ִ� �����鸸 ���, ä���� �����ϰԵȴ�.
		 * 
		 * +���� �����Ǿ����� Ŭ������ �޼ҵ嵵 �����ߵ�!!
		 */
		while(true) {
			try {
				String str = input.readUTF();
				if(str.equals("Create")) {
					System.out.println("����");
					int num = RoomNumber;
					RoomNumber++;
					MainServer.roomList.put(num, new Room(name));
					new GameRoom(num, name);
				}else if(str.equals("exit")){
					return;
				}else {
					System.out.println("����");
					int rNumber = Integer.parseInt(input.readUTF());
					System.out.println(rNumber);
					MainServer.roomList.get(rNumber).RoomUserListAdd(name);
					new GameRoom(rNumber, name);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
