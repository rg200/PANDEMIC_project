package testServer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;

/*
 * �뿡 �ִ� ��� ������ ����ִ�. �׸��� �� ������ ����, ������� �������ִ�
 */

public class UserList{
	//dataOutputStream�� �ٸ� ��Ʈ������ �ٸ��� �ڷ��� �״�� ������´�.
	//ex) int������ �����͸� ������ int������ ������ �ִ�.
	private HashMap<String, DataOutputStream> user = new HashMap<String, DataOutputStream>();
	
	public UserList() {
		// TODO Auto-generated constructor stub
	}
	
	//���� ó�� ���� ���鶧 �ش� �г��ӿ� ���� ������ ��� ��Ʈ���� hashmap�� ��Ƶд�.(Ű�� �г��� ������ ��½�Ʈ��)
	public UserList(String name) {
		DataOutputStream output;
		try {
			output = new DataOutputStream(LobbyServer.userList.get(name)[0].getOutputStream());
			user.put(name, output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//������ �濡 ������ �߰��Ҷ�
	public void userAdd(String name) {
		DataOutputStream output;
		try {
			output = new DataOutputStream(LobbyServer.userList.get(name)[0].getOutputStream());
			user.put(name, output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//�̰� ������ �𸣰����� �ش� ������ ��½�Ʈ���� ��ȯ�Ѵ�.
	/*public DataOutputStream getUserList(String name){
		return user.get(name);
	}*/
	
	//���� ������ ����Ʈ�� ��ȯ�Ѵ�.
	public HashMap<String, DataOutputStream> getUserList(){
		return user;
	}
}
