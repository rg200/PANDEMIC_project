package testServer;

import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class ClientReceiverThread implements Runnable{   // ä�� ���� ȭ�鿡 ���
	Socket socket;
	public ClientReceiverThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {   
		try {
			DataInputStream reader = new DataInputStream(socket.getInputStream());
			while(true) {
				String str = reader.readUTF();   // ä�� ���� �Է�
				if(str == null) break;
				System.out.println(str);
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}
