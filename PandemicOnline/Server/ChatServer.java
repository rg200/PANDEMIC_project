package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Hashtable;

public class ChatServer implements Runnable{
	//채팅 서버이다
	private int roomNum = 0;
	private Hashtable<String, DataOutputStream> list = null;//유저 리스트 보내기용
	private DataInputStream input = null;
	private Socket s;
	private String name; 
	private DataOutputStream output = null;
	
	public ChatServer() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * 여기서는 룸의 번호와 닉네임을 받고
	 * 전체 룸 리스트에서 룸 번호에 대한 룸 객체를 가지고 오고
	 * 전체 유저 리스트 객체에서 닉네임에 대한 소켓을 가지고 온다.
	 */
	
	public ChatServer(int num, String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
		//나의 소켓과 입력스트림을 받는다.
		s = LobbyServer.userList.get(name)[1];
		try {
			input = new DataInputStream(s.getInputStream());
			output = new DataOutputStream(s.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//해당 방 번호에 대한 룸객체안에 유저들의 리스트를 가지고온다.
		roomNum = num;
		list = MainServer.roomList.get(roomNum).getUserListChat();
	}
	
	//방바꾸기
	public void ChangeRoomNumber(int num) {
		roomNum = num;
		list = MainServer.roomList.get(num).getUserListChat();
	}
	
	//유저 리스트에 받은 입력 정보를 보낸다.
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
		 * 일단 채팅만 구현함! 
		 * 클라에서 보낼때 태그를 이용?
		 * 채팅은 [채팅]
		 * 제어는 [제어]
		 * 이렇게?
		 */
		try {
			while(true) {
				String str = input.readUTF();   // 클라이언트로부터 채팅 받아옴
				System.out.println(str);
				if(str.substring(0,4).equals("[제어]")) {
					System.out.println("chatserver end");
					try {
						output.writeUTF("[제어]stop");
						System.out.println("chatserver2 end");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
				else {
					str = str.substring(4);
					System.out.println(roomNum);
				}
				if(input == null || str == null) break;   // 입력이 아무것도 들어오지 않으면 탈출
				sendAll(name+">"+str);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			MainServer.roomList.get(roomNum).RoomUserListDel(name);//방에 유저 삭제
			System.out.println(MainServer.roomList.get(roomNum).getUserListChat());
			if(MainServer.roomList.get(roomNum).getUserListChat().isEmpty()) {
				System.out.println("샂게함");
				MainServer.roomList.remove(roomNum);
			}
			
		}
	}

}
