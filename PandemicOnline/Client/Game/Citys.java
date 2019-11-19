package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Game.MainPanel.Map;

public class Citys {
	public String[][] ad = new String[49][49];// 인접시 o 소문자 o가 넣어진다 아닐시에는"", 도시의 인접상만 판단
	String[] name = { "", "애틀란타", "워싱턴", "시카고", "마이애미", "멕시코 시티", "몬트리올", "뉴욕", "보고타", "리마", "로스앤젤레스", "샌프란시스코", "런던",
			"마드리드", "산티아고", "상파울루", "부에노스아이레스", "도쿄", "마닐라", "시드니", "에센", "파리", "알제", "라고스", "오사카", "서울", "상하이", "홍콩",
			"호치민 시티", "자카르타", "타이베이", "상트페테르부르크", "밀라노", "이스탄불", "카이로", "카르툼", "킨샤샤", "베이징", "콜카타", "방콕", "첸나이", "모스크바",
			"바그다드", "리야드", "요하네스버그", "델리", "뭄바이", "카라치", "테헤란" };

	City[] city = new City[48];// 도시 그 자체의 자원을 나타낸다.
	// ad배열같은 경우에는 한눈에 알아보기 쉽고, 또 인접성판단을 위해 공백을 넣어 index가 +1 되있지만, city 배열은 그럴
	// 필요가없어서 공백이 없다.

	public Citys() {
		for (int i = 0; i < 49; i++)
			for (int j = 0; j < 49; j++)
				ad[i][j] = "";// 다 공백으로 채워넣은 뒤에

		for (int i = 0; i < 49; i++) {
			ad[i][0] = name[i];
			ad[0][i] = name[i];
			// 0행 or 0열 기준으로 모든 도시를 구분
		}
		ad[1][2] = ad[2][1] = "o";
		ad[1][3] = ad[3][1] = "o";
		ad[1][4] = ad[4][1] = "o";
		ad[2][6] = ad[6][2] = "o";
		ad[2][7] = ad[7][2] = "o";
		ad[2][4] = ad[4][2] = "o";
		ad[3][5] = ad[5][3] = "o";
		ad[3][6] = ad[6][3] = "o";
		ad[3][11] = ad[11][3] = "o";
		ad[3][10] = ad[10][3] = "o";
		ad[4][5] = ad[5][4] = "o";
		ad[4][8] = ad[8][4] = "o";
		ad[5][8] = ad[8][5] = "o";
		ad[5][9] = ad[9][5] = "o";
		ad[5][10] = ad[10][5] = "o";
		ad[6][7] = ad[7][6] = "o";
		ad[7][12] = ad[12][7] = "o";
		ad[7][13] = ad[13][7] = "o";
		ad[8][9] = ad[9][8] = "o";
		ad[8][16] = ad[16][8] = "o";
		ad[8][15] = ad[15][8] = "o";
		ad[9][14] = ad[14][9] = "o";
		ad[10][11] = ad[11][10] = "o";
		ad[10][19] = ad[19][19] = "o";
		ad[11][17] = ad[17][11] = "o";
		ad[11][18] = ad[18][11] = "o";
		ad[12][13] = ad[13][12] = "o";
		ad[12][20] = ad[20][12] = "o";
		ad[12][21] = ad[21][12] = "o";
		ad[13][15] = ad[15][13] = "o";
		ad[13][22] = ad[22][13] = "o";
		ad[13][21] = ad[21][13] = "o";
		ad[15][16] = ad[16][15] = "o";
		ad[15][23] = ad[23][15] = "o";
		ad[17][24] = ad[24][17] = "o";
		ad[17][25] = ad[25][17] = "o";
		ad[17][26] = ad[26][17] = "o";
		ad[18][19] = ad[19][18] = "o";
		ad[18][27] = ad[27][18] = "o";
		ad[18][28] = ad[28][18] = "o";
		ad[18][30] = ad[30][18] = "o";
		ad[19][29] = ad[29][19] = "o";
		ad[20][21] = ad[21][20] = "o";
		ad[20][31] = ad[31][20] = "o";
		ad[20][32] = ad[32][20] = "o";
		ad[21][22] = ad[22][21] = "o";
		ad[21][32] = ad[32][21] = "o";
		ad[22][33] = ad[33][22] = "o";
		ad[22][34] = ad[34][22] = "o";
		ad[23][35] = ad[35][23] = "o";
		ad[23][36] = ad[36][23] = "o";
		ad[24][30] = ad[30][24] = "o";
		ad[25][26] = ad[26][25] = "o";
		ad[25][37] = ad[37][25] = "o";
		ad[26][27] = ad[27][26] = "o";
		ad[26][30] = ad[30][26] = "o";
		ad[26][37] = ad[37][26] = "o";
		ad[27][28] = ad[28][27] = "o";
		ad[27][30] = ad[30][27] = "o";
		ad[27][38] = ad[38][27] = "o";
		ad[27][39] = ad[39][27] = "o";
		ad[28][29] = ad[29][28] = "o";
		ad[28][39] = ad[39][28] = "o";
		ad[29][39] = ad[39][29] = "o";
		ad[29][40] = ad[40][29] = "o";
		ad[31][41] = ad[41][31] = "o";
		ad[31][33] = ad[33][31] = "o";
		ad[32][33] = ad[33][32] = "o";
		ad[33][34] = ad[34][33] = "o";
		ad[33][41] = ad[41][33] = "o";
		ad[33][42] = ad[42][33] = "o";
		ad[34][35] = ad[35][34] = "o";
		ad[34][42] = ad[42][34] = "o";
		ad[34][43] = ad[43][34] = "o";
		ad[35][36] = ad[36][35] = "o";
		ad[35][44] = ad[44][35] = "o";
		ad[36][44] = ad[44][36] = "o";
		ad[38][39] = ad[39][38] = "o";
		ad[38][45] = ad[45][38] = "o";
		ad[38][40] = ad[30][38] = "o";
		ad[39][40] = ad[40][39] = "o";
		ad[40][46] = ad[46][40] = "o";
		ad[40][45] = ad[45][40] = "o";
		ad[41][48] = ad[48][41] = "o";
		ad[42][43] = ad[43][42] = "o";
		ad[42][47] = ad[47][42] = "o";
		ad[42][48] = ad[48][42] = "o";
		ad[43][47] = ad[47][43] = "o";
		ad[45][46] = ad[46][45] = "o";
		ad[45][47] = ad[47][45] = "o";
		ad[48][45] = ad[45][48] = "o";
		ad[46][47] = ad[47][46] = "o";
		ad[47][48] = ad[48][47] = "o";
		// 인접성 설정

		/*
		 * for (int i = 0; i < 49; i++) { for (int j = 0; j < 49; j++)
		 * System.out.printf("%s\t", ad[i][j]); System.out.println(); }
		 */
		// 위는 인접성이 잘 설정됬는지 확인하기 위한 반복문이다.

		// 도시정보 설정
		city[0] = new City("애틀란타", "BlueCircle.png", 1, 760, 900);
		city[1] = new City("워싱턴", "BlueCircle.png", 2, 960, 900);
		city[2] = new City("시카고", "BlueCircle.png", 3, 680, 780);
		city[3] = new City("마이애미", "YellowCircle.png", 4, 870, 1020);
		city[4] = new City("멕시코 시티", "YellowCircle.png", 5, 670, 1035);
		city[5] = new City("몬트리올", "BlueCircle.png", 6, 860, 770);
		city[6] = new City("뉴욕", "BlueCircle.png", 7, 1000, 760);
		city[7] = new City("보고타", "YellowCircle.png", 8, 910, 1180);
		city[8] = new City("리마", "YellowCircle.png", 9, 740, 1230);
		city[9] = new City("로스앤젤레스", "YellowCircle.png", 10, 460, 1030);
		city[10] = new City("샌프란시스코", "BlueCircle.png", 11, 440, 850);
		city[11] = new City("런던", "BlueCircle.png", 12, 1300, 680);
		city[12] = new City("마드리드", "BlueCircle.png", 13, 1313, 870);
		city[13] = new City("산티아고", "YellowCircle.png", 14, 790, 1400);
		city[14] = new City("상파울루", "YellowCircle.png", 15, 1200, 1200);
		city[15] = new City("부에노스아이레스", "YellowCircle.png", 16, 1070, 1350);
		city[16] = new City("도쿄", "RedCircle.png", 17, 2700, 800);
		city[17] = new City("마닐라", "RedCircle.png", 18, 2590, 1125);
		city[18] = new City("시드니", "RedCircle.png", 19, 2690, 1395);
		city[19] = new City("에센", "BlueCircle.png", 20, 1545, 655);
		city[20] = new City("파리", "BlueCircle.png", 21, 1490, 775);
		city[21] = new City("알제", "BlackCircle.png", 22, 1500, 930);
		city[22] = new City("라고스", "YellowCircle.png", 23, 1430, 1100);
		city[23] = new City("오사카", "RedCircle.png", 24, 2730, 970);
		city[24] = new City("서울", "RedCircle.png", 25, 2550, 780);
		city[25] = new City("상하이", "RedCircle.png", 26, 2380, 820);
		city[26] = new City("홍콩", "RedCircle.png", 27, 2400, 1005);
		city[27] = new City("호치민 시티", "RedCircle.png", 28, 2395, 1200);
		city[28] = new City("자카르타", "RedCircle.png", 29, 2240, 1300);
		city[29] = new City("타이베이", "RedCircle.png", 30, 2545, 985);
		city[30] = new City("상트페테르부르크", "BlueCircle.png", 31, 1680, 460);
		city[31] = new City("밀라노", "BlueCircle.png", 32, 1670, 700);
		city[32] = new City("이스탄불", "BlackCircle.png", 33, 1690, 850);
		city[33] = new City("카이로", "BlackCircle.png", 34, 1680, 1000);
		city[34] = new City("카르툼", "YellowCircle.png", 35, 1720, 1155);
		city[35] = new City("킨샤샤", "YellowCircle.png", 36, 1550, 1215);
		city[36] = new City("베이징", "RedCircle.png", 37, 2360, 650);
		city[37] = new City("콜카타", "BlackCircle.png", 38, 2235, 855);
		city[38] = new City("방콕", "RedCircle.png", 39, 2290, 1100);
		city[39] = new City("첸나이", "BlackCircle.png", 40, 2140, 1190);
		city[40] = new City("모스크바", "BlackCircle.png", 41, 1815, 590);
		city[41] = new City("바그다드", "BlackCircle.png", 42, 1870, 880);
		city[42] = new City("리야드", "BlackCircle.png", 43, 1840, 1100);
		city[43] = new City("요하네스버그", "YellowCircle.png", 44, 1700, 1400);
		city[44] = new City("델리", "BlackCircle.png", 45, 2130, 700);
		city[45] = new City("뭄바이", "BlackCircle.png", 46, 2000, 1080);
		city[46] = new City("카라치", "BlackCircle.png", 47, 2020, 900);
		city[47] = new City("테헤란", "BlackCircle.png", 48, 1920, 700);
	}

	public void draw(Graphics g) {
		for (int i = 0; i < city.length; i++) {
			city[i].draw(g);
		}
	}// 도시들을 다 그리기

	class City {// 도시 클래스
		String name;
		String color;
		private int x, y;
		int myNum;
		int[] yellow = new int[4];
		int[] blue = new int[4];
		int[] black = new int[4];
		int[] red = new int[4];
		Image CircleBack;

		public City() {
		}

		public City(String name, String color, int myNum, int x, int y) {
			this.name = name;
			this.color = color;
			this.myNum = myNum;
			this.x = x;
			this.y = y;
			CircleBack = new ImageIcon(Map.class.getResource("../Image/" + color)).getImage();
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public String getName() {
			return name;
		}

		public void draw(Graphics g) {
			g.drawImage(CircleBack, x, y, null);
			g.setFont(new Font("굴림", Font.BOLD, 30));
			g.setColor(Color.white);
			g.drawString(name, x - 30, y + 90);
		}

	}

	public ArrayList<String> AdjacencyCitys(String e) {
		// 인접성을 식별해서 인접한 도시들을 배열로 보내준다.
		// 인접성을 판별해서 인접한 도시들의 ArrayList<String> 값으로 반환
		// 매개변수 e로 현재 캐릭터의 도시 위치(String)가 들어온다.
		ArrayList<String> Adjacency = new ArrayList<String>();
		int position = 0;
		for (position = 0; position < 49; position++) {
			if (ad[0][position].equals(e))
				break;
			// 행이 0일 때 즉 e 값이 어딨는지 찾는다.
		}

		for (int i = 0; i < 49; i++) {
			// 위에서 현재도시가 판별됬으니 그 도시를 기준으로 o가 되있는 도시들을 다 Adjacency에 넣는다
			if (ad[i][position].equals("o")) {
				Adjacency.add(ad[0][i]);
			}
		}
		return Adjacency;
	}

	public Point CityPosition(String CurrentCity) {
		//현재의 도시명(String)을 입력 받아서 그 도시의 좌표(x,y)를 반환하는 메소드
		Point current = new Point(0, 0);
		for (int i = 0; i < 48; i++) {
			if (CurrentCity.equals(city[i].getName())) {
				current.setX(city[i].getX());
				current.setY(city[i].getY());
			}
		}
		return current;
	}

}
