
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Lobby  {

   

   public static void main(String[] args) {
      LobbyFrame frame = new LobbyFrame();
   }
}
class LobbyFrame extends JFrame{
   JScrollPane scrollPane;
   ImageIcon icon;

   public LobbyFrame() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(1920, 1080);
      setVisible(true);
      icon = new ImageIcon("C:\\Users\\WIN10\\Desktop\\img\\Lobby.png");
      // ��� Panel ������ �������������� ����
      JPanel background = new JPanel() {
         public void paintComponent(Graphics g) {
            g.drawImage(icon.getImage(), 0, 0, null);
            setOpaque(false); // �׸��� ǥ���ϰ� ����,�����ϰ� ����
            super.paintComponent(g);
         }         
      };
      scrollPane = new JScrollPane(background);
      setContentPane(scrollPane);
      JPanel Profile = new JPanel(); // ������ �г�
      JPanel RoomList = new JPanel(); // ���� �г�
      JPanel Chat = new JPanel(); // ä��â �г�
      JLabel label1 = new JLabel("������"); // ������ â ����?
      JLabel ChatList = new JLabel("ä��â"); // ä��â ����
      Profile.add(label1); // ������ ���� �߰�
      Chat.add(ChatList); // ä��â ���� �߰�      
      Font RLfont,ChatFont,PFfont;  //��Ʈ �߰� ���ʴ�� ����,ä��,������
      RLfont = new Font("Serif",Font.BOLD,50);
      JButton RoomMake = new JButton(new ImageIcon("C:\\\\Users\\\\WIN10\\\\Desktop\\\\img\\\\MkRoom.png")); // �游��� ��ư �߰�
      JButton RoomSearch = new JButton(new ImageIcon("C:\\\\Users\\\\WIN10\\\\Desktop\\\\img\\\\SerRoom.png")); // ��ã�� ��ư �߰�
      JButton Refresh = new JButton(new ImageIcon("C:\\\\Users\\\\WIN10\\\\Desktop\\\\img\\\\Re.png")); //���ΰ�ħ��ư �߰�
      
      RoomMake.setContentAreaFilled(false); //��ư ���뿵�� ä�����ʱ�,�̹����� �س����ϱ�
      RoomSearch.setContentAreaFilled(false);
      Refresh.setContentAreaFilled(false);
      RoomMake.setBorderPainted(false); //��ư �׵θ� ���ֱ�
      RoomSearch.setBorderPainted(false);
      Refresh.setBorderPainted(false);
      RoomMake.setFocusPainted(false); //�������� �׵θ� �ȶ߰�
      RoomSearch.setFocusPainted(false);
      Refresh.setFocusPainted(false);
      JTextArea ChatTest = new JTextArea("chat test"); //ä��â

      /*JLabel RL = new JLabel("����"); // ������ ������ ǥ�õǴ� �κ�
      RL.setFont(RLfont);
      RoomList.add(RL); // ���� �߰�*/
      
      RoomList.add(RoomMake); // �游��� ��ư ����
      RoomList.add(RoomSearch); // ��ã�� ��ư ����
      RoomList.add(Refresh); //���ΰ�ħ ��ư ����

      //ChatTest.setSize(500, 500);
      Chat.add(ChatTest);

      setLayout(null);
      RoomList.setBounds(600, 200, 800, 800);
      Profile.setBounds(200, 750, 100, 100);
      Chat.setBounds(800, 800, 100, 100);
      background.setBounds(0,0,1920,1080);
      Profile.setOpaque(false); //������ �ǳ� �Ⱥ��̰��ϱ�
      RoomList.setOpaque(false); //������ �ǳ� �Ⱥ��̰��ϱ�
      Chat.setOpaque(false); //������ �ǳ� �Ⱥ��̰��ϱ�
      add(Profile);
      add(RoomList);
      add(Chat);
      add(background);

      setVisible(true); // �ذ���� ä��â ũ������, �������ֱ�(����)

      
      
   }
}