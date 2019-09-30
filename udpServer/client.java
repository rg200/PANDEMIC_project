package udp;

import java.net.*;

import java.io.*;


 

public class client implements Runnable{
	DatagramSocket dsock = null;
	public client() {
		// TODO Auto-generated constructor stub
		 String ip = "39.127.8.83";

         int port = 9000;

         InetAddress inetaddr = null;//ip�� ����.

          

         try{

                inetaddr = InetAddress.getByName(ip);//ip�� �ְ�

         }catch(UnknownHostException e){

                System.out.println("�߸��� �������̳� ip�Դϴ�.");

                System.exit(1);

         }

         
        

         try{
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//�Է°��� ����Ʈ �������� �ٲ㼭 ����

                dsock = new DatagramSocket();


                String line = null;
                Thread t = new Thread(this);
                t.start();

                while((line = br.readLine())!=null){
                		
                       DatagramPacket sendPacket = new DatagramPacket(line.getBytes(), line.getBytes().length, inetaddr, port);
                       dsock.send(sendPacket);

                       if(line.equals("quit")) break;


                       
                }

                       
                      

                

                System.out.println("UDPEchoClient�� �����մϴ�.");

         }catch(Exception ex){

                System.out.println(ex);

         }finally{

                if(dsock != null)

                       dsock.close();

         }
	}

       public static void main(String[] args){
    	   new client();

            

       }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			byte[] buffer = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);

            try {
				dsock.receive(receivePacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


               String msg = new String(receivePacket.getData(), 0, receivePacket.getData().length);

               System.out.println("���۹��� ���ڿ� : "+msg);
		}
	}


 

}