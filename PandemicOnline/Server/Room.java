package testServer;

import java.io.DataOutputStream;
import java.util.Hashtable;

/*
 * �濡 ���� Ŭ�����̴�.
 * �� �渶�� �� roomŬ������ ������ �ϰ�
 * ���⼭�� ���� ��� ����, �����?�� ���´�.
 */

public class Room{
	private UserList user = null;
	private String roomName = "";
	
	public Room() {
		// TODO Auto-generated constructor stub
	}
	//�ش� �г��� �Ǵ� ���̵��� ������ ���� Ŭ������ �߰��Ѵ�.(���� ������ �Ұ��)
	public Room(String name) {
		// TODO Auto-generated constructor stub
		System.out.println(name);
		user = new UserList(name);//���� ��ü�� ����
	}
	//�濡 �����Ұ�� �� �޼ҵ带 �̿��ؼ� ���� Ŭ������ �߰��Ѵ�.
	public void RoomUserListAdd(String name) {
		System.out.println(name);
		user.userAdd(name);
	}
	
	public void RoomUserListDel(String name) {
		user.userDel(name);
	}
	
	public void setRoomName(String str) {
		roomName = str;
	}
	
	public String getRoomName() {
		return roomName;
	}
	
	//�̰� ����(���� �ý���)
	public String GetKing() {
		return user.getUserList().keySet().iterator().next();
	}
	
	//�� �濡 �ִ� �������� ������ �����´�.
	public Hashtable<String, DataOutputStream> getUserList(){
		return user.getUserList();
	}
	
	public Hashtable<String, DataOutputStream> getUserListChat(){
		return user.getUserListChat();
	}
}