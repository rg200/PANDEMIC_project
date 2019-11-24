package Server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CardList {
	final private String[] cityList = { "애틀란타", "워싱턴", "시카고", "마이애미", "멕시코 시티", "몬트리올", "뉴욕", "보고타", "리마", "로스앤젤레스", "샌프란시스코", "런던",
			"마드리드", "산티아고", "상파울루", "부에노스아이레스", "도쿄", "마닐라", "시드니", "에센", "파리", "알제", "라고스", "오사카", "서울", "상하이", "홍콩",
			"호치민시티", "자카르타", "타이베이", "상트페테르부르크", "밀라노", "이스탄불", "카이로", "카르툼", "킨샤샤", "베이징", "콜카타", "방콕", "첸나이", "모스크바",
			"바그다드", "리야드", "요하네스버그", "델리", "뭄바이", "카라치", "테헤란" };
	final private String[] specialCardList = { "평온한 하룻밤", "긴급공중수송", "항체보유자", "예측", "정부보조금" };
	private List<String> infectionCard;// 감염카드
	private List<String> cityCard; // 도시카드
	private List<String> infAbandonCard; // 버린 감염카드
	private List<String> cityAbandonCard; // 버린 도시카드
	private Deque<String> infectionCardQue = new LinkedList<String>(); // 감염카드 데큐
	private Queue<String> cityCardQue = new LinkedList<String>(); // 도시카드 데큐
	private int difficulty; // 게임 난이도 조절을 위한 전염카드 갯수

	public CardList(int num) {
		// TODO Auto-generated constructor stub
		this.difficulty = num;
		cardSetting();
	}

	public void cardSetting() {
		// 카드 세팅 및 큐에 넣기
		infAbandonCard = new ArrayList<String>();
		cityAbandonCard = new ArrayList<String>();

		infectionCard = Arrays.asList(cityList);

		cityCard = new ArrayList<String>(Arrays.asList(cityList));
		for (int i = 0; i < 5; i++)
			cityCard.add(specialCardList[i]);

		Collections.shuffle(infectionCard);
		Collections.shuffle(cityCard);

		cityCardStting();

		infectionCardQue.addAll(infectionCard);
		cityCardQue.addAll(cityCard);
	}
	
	public void setDifficulty(int d) {this.difficulty = d;}

	//도시카드 세팅(난이도별로)
	private void cityCardStting() {
		for (int i = 0; i < difficulty; i++) {
			int rom = (int) (Math.random() * (53 / difficulty));
			cityCard.add(((53 / difficulty) * i) + rom, "전염");
		}
	}

	//감염카드 뽑기
	public String[] infCardHandling(int count) {
		String[] str = new String[count];
		for (int i = 0; i < str.length; i++) {
			String card = infectionCardQue.pollFirst();
			str[i] = card;
			infAbandonCard.add(card);
		}

		return str;
	}

	//도시카드 뽑기
	public String[] cityCardHamdling() {
		String[] str = new String[2];
		for (int i = 0; i < 2; i++) {
			String card = cityCardQue.poll();
			str[i] = card;
			cityAbandonCard.add(card);
		}

		return str;
	}

	//전염 진행 메소드
	public String Infection() {
		String card = infectionCardQue.pollLast();
		infAbandonCard.add(card);
		Collections.shuffle(infAbandonCard);
		
		for (int i = infAbandonCard.size() - 1; i >= 0; i--)
			infectionCardQue.addFirst(infAbandonCard.get(i));
		
		return card;
	}

}
