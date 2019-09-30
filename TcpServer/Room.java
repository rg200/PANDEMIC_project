package testServer;

import java.io.DataOutputStream;
import java.util.HashMap;

/*
 * �濡 ���� Ŭ�����̴�.
 * �� �渶�� �� roomŬ������ ������ �ϰ�
 * ���⼭�� ���� ��� ����, �����?�� ���´�.
 */

public class Room{
	private UserList user = null;
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
	//�� �濡 �ִ� �������� ������ �����´�.
	public HashMap<String, DataOutputStream> getUserList(){
		return user.getUserList();
	}
}
