package Game;

import java.io.DataInputStream;
import java.net.Socket;
import javax.swing.JTextArea;

import pandemic.Client;


public class ClientGameReceiverThread implements Runnable{
	private Socket socket;
	private MainPanel mainPanel;
	private testLabel labelTh;
	private boolean turnStart = false;
	
	public ClientGameReceiverThread(Socket socket, MainPanel mainPanel) {
		this.socket = socket;
		this.mainPanel = mainPanel;
	}
	
	public void panelRepaint() {
		mainPanel.Controlpanel.invalidate();
		mainPanel.Controlpanel.removeAll();
		mainPanel.Controlpanel.add(new BasicSelect(mainPanel.Controlpanel));
		mainPanel.Controlpanel.revalidate();
		mainPanel.Controlpanel.repaint();
		mainPanel.repaint();
		mainPanel.Controlpanel.setBounds(0, 840, 1920, 240);
	}
	
	public void colorSelect(String color, String cityName) {
		City city = mainPanel.Controlpanel.Mainpanel.citys.returnCity(cityName);
		
		if (color.equals("Red") && !Game.RedCure)
			--city.Red;
		else if (color.equals("Red") && Game.RedCure)
			city.Red = 0;
		else if (color.equals("Black") && !Game.BlackCure)
			--city.Black;
		else if (color.equals("Black") && Game.BlackCure)
			city.Black = 0;
		else if (color.equals("Blue") && !Game.BlueCure)
			--city.Blue;
		else if (color.equals("Blue") && Game.BlueCure)
			city.Blue = 0;
		else if (color.equals("Yellow") && !Game.YellowCure)
			--city.Yellow;
		else if (color.equals("Yellow") && Game.YellowCure)
			city.Yellow = 0;
	}
	
	public void run() {   
		try {
			System.out.println("CRT start");
			
			while(true) {
				DataInputStream reader = new DataInputStream(socket.getInputStream());
				System.out.println(socket);
				String str = reader.readUTF();
				System.out.println(str);
				if(str.equals("[제어]stop") || reader == null) {
					System.out.println("CRT end");
					break;
				}
				else {
					if(str.substring(0,4).equals("[이동]")) {
						System.out.println("ad");
						str = str.substring(4);
						String[] str2 = str.split(":");
						mainPanel.characterList.get(str2[0]).setCurrentposition(str2[1]);// 캐릭터의 현재 위치를 이동시키기 위해 이동도시(String)를 바꾸고
						Point pos = mainPanel.citys.CityPosition(str2[1]);
						String Color = mainPanel.citys.returnCity(str2[1]).color;
						mainPanel.characterList.get(str2[0]).setCC(str2[1], Color);
						mainPanel.characterList.get(str2[0]).setXY(pos.getX(), pos.getY());// 캐릭터의 좌표를 바꾼다.(xy)
						
						if(str2[0].equals(Client.name))
							panelRepaint();
					}else if(str.substring(0, 4).equals("[목룍]")) {
						str = str.substring(4);
						str = str.substring(1, str.length() - 1);

						String[] str2 = str.split(", ");
						System.out.println(str2);
						int c=0;
						for(int i=0;i<str2.length;i++) {
							mainPanel.characterList.put(str2[i], new Character(mainPanel,c));
							c=c+10;
						}
						
					}else if(str.substring(0, 4).equals("[치료]")) {
						str = str.substring(4);
						String[] str2 = str.split(",");//누가,색깔,도시
						
						colorSelect(str2[1], str2[2]);
						
						if(str2[0].equals(Client.name))
							panelRepaint();
						mainPanel.Controlpanel.Mainpanel.repaint();
						
					}else if(str.substring(0, 4).equals("[감염]")) {
						str = str.substring(4);
						String[] str2 = str.split(", ");
						//테스트 아직 안됨
						Client.CardPrint = true;
						labelTh = new testLabel(mainPanel,str2);
						labelTh.start();
						if(turnStart)
							Client.CardPrint = false;
						Thread.sleep(1500*str2.length+1000);
						
					}else if(str.substring(0, 4).equals("[도시]")) {
						str = str.substring(4);
						String[] str2 = str.split(", ");
						for(int i=0;i<str2.length;i++) {
							String Color = mainPanel.citys.returnCity(str2[i]).color;
							mainPanel.Controlpanel.Havecard.insertCard(mainPanel.Controlpanel,str2[i], Color);
						}
						
					}else if(str.substring(0, 4).equals("[건설]")) {
						str = str.substring(4);
						String[] str2 = str.split(":");
						if(str2[0].equals(Client.name)) {
							mainPanel.Controlpanel.Havecard.BuildLabatory(str2[1]);
						}else {
							mainPanel.citys.returnCity(str2[1]).Labatory = true;
							mainPanel.repaint();
						}
						
					}else if(str.substring(0, 4).equals("[개발]")) {
						str = str.substring(4);
						if (str.equals("Red")) {
							Game.RedCure = true;
						} else if (str.equals("Blue")) {
							Game.BlueCure = true;
						} else if (str.equals("Yellow")) {
							Game.YellowCure = true;
						} else {
							Game.BlackCure = true;
						}
					}else if(str.substring(0, 4).equals("[제어]")) {
						str = str.substring(4);
						String[] str2 = str.split(":");
						if(str2[0].equals("turnStart") && str2[1].equals(Client.name)) {
							Client.CardPrint = false;
							turnStart = true;
						}else if(str2[0].equals("turnStop") && str2[1].equals(Client.name)) {
							Client.CardPrint = true;
							turnStart = false;
						}
					}
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}
