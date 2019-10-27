package testServer;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Hashtable;

public class LobbyServer{
	public static Hashtable<String, Socket[]> userList = new Hashtable<String, Socket[]>();
	private static int RoomNumber = 1;
	private Socket gameSocket = null;
	private Socket chatSocket = null;
	private DataInputStream input = null;
	private String name;
	private Runnable ChatRun;
	private Thread ChatTh;
	
	
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
		
		//�κ� ä���� �ϱ� ���ؼ� ���ȣ 0���� �κ� ä������ �Ѵ�.
		if(MainServer.roomList.containsKey(0)) {
			MainServer.roomList.get(0).RoomUserListAdd(name);
		}
		else {
			MainServer.roomList.put(0,new Room(name));
		}
		
		//�� ��ȣ 0������ ä�� �����带 �����.
		ChatRun = new ChatServer(0, name);
		ChatTh = new Thread(ChatRun);
		ChatTh.start();
		
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
					int num = 0;
					synchronized (this) {//�� ��ȣ�� ����ȭ�ؾߵȴ�.
						num = RoomNumber++;
						MainServer.roomList.put(num, new Room(name));
					}
					new GameRoom(num, name);
				}else if(str.equals("exit")){
					return;
				}else if(str.equals("refresh")) {
					//���ΰ�ħ(���ȣ�� ���̸��� ��������Ѵ�.)
				}
				else {
					System.out.println("����");
					int rNumber = Integer.parseInt(input.readUTF());
					System.out.println(rNumber);
					MainServer.roomList.get(rNumber).RoomUserListAdd(name);
					new GameRoom(rNumber, name);
				}
				
			}catch (Exception e) {
				// TODO: handle exception
				break;
			}
		}
	}
}