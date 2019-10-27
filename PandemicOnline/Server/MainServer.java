package testServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Hashtable;

public class MainServer {
	private ServerSocket sSocket1 = null;
	private ServerSocket sSocket2 = null;
	private Runnable runThread = null;
	private Thread mainThread = null;
	public static Hashtable<Integer, Room> roomList = new Hashtable<Integer, Room>();
	
	
	public MainServer() {
		
		try {
			System.out.println("����");
			sSocket1 = new ServerSocket(9002);
			sSocket2 = new ServerSocket(9003);
			
			while(true) {
				Socket gameSocket = sSocket1.accept();
				Socket chatSocket = sSocket2.accept();
				System.out.println("���� ����");
				
				//���� ���, ������ ���� �����带 ��
				runThread = new MainThread(gameSocket, chatSocket);
				mainThread = new Thread(runThread);
				mainThread.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("���� ����");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainServer();
	}

}