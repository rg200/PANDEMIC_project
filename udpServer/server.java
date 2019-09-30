package udp;

import java.io.IOException;
import java.net.*;
import java.util.HashMap;

public class server {
	private static DatagramSocket dsock = null;

       public static void main(String[] args){
    	   HashMap<InetAddress, Integer> user = new HashMap<InetAddress, Integer>();
            

             int port = 9000;

            

       // Ŭ���̾�Ʈ���� DatagramPacket�� �����ϰų� �����ϱ� ���� DatagramSocket ��ü ����

             //DatagramSocket dsock = null;

             try{

                    System.out.println("���� �������Դϴ�.");

                    dsock = new DatagramSocket(port);

                    String line = null;


                    while(true){

                           byte[] buffer = new byte[1024];

                           DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                           
                           
                           dsock.receive(receivePacket);
                           System.out.println(receivePacket.getPort());

                           String msg = new String(receivePacket.getData(), 0, receivePacket.getLength());

                           System.out.println("���� ���� ���ڿ� : " + msg);

                           if(msg.equals("quit")) break;
								user.put(receivePacket.getAddress(), receivePacket.getPort());
			                           
                           for(InetAddress ip : user.keySet()) {
                        	   DatagramPacket sendPacket = null;
                        	   if(ip != receivePacket.getAddress()) {
                        		   sendPacket = new DatagramPacket(receivePacket.getData(),receivePacket.getData().length, ip, user.get(ip));
                        		   try {
									dsock.send(sendPacket);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
                        	   }

                               
                           }
                           

                           

                    }

                    System.out.println("UDPEchoServer�� �����մϴ�.");

                   

             }catch(Exception e){

                    System.out.println(e);

             }finally{

                    if(dsock != null)

                           dsock.close();

             }

       }

}