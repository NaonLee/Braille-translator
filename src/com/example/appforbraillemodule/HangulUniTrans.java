package com.example.appforbraillemodule;

public class HangulUniTrans {


	static final int BASE_CODE = 44032;
	static final int CHOSUNG = 588;
	static final int JUNGSUNG = 28;
	
	static int chojungjong_buf[]={0,0,0}; //�����ڵ� �ε����� �� ���� �ʼ��߼�����

	public static int[] chojungjong(char charTemp) {
		// �ѱ��ڸ� �޾Ƽ�  �ѱ��� �ʼ�, �߼�, �������� �и��ϱ�.
		// �����ڵ� �ѱ� ���� : 44032, �� : 55199
		// �ѱ� BASE_CODE + (0 * CHOSUNG + 2 * JUNGSUNG);
		// int cBase = charTemp - BASE_CODE; // BASE_CODE(440324) ����
		int i = 0;
		// �ʼ�
		int c1 = (charTemp - BASE_CODE) / CHOSUNG;
		// �߼�
		int c2 = ((charTemp - BASE_CODE) - CHOSUNG * c1) / JUNGSUNG;
		// ����
		int c3 = (charTemp - BASE_CODE) - CHOSUNG * c1 - JUNGSUNG * c2;

		chojungjong_buf[0] = (int)c1;
		chojungjong_buf[1] = (int)c2;
		chojungjong_buf[2] = (int)c3;
		
		return chojungjong_buf;
	}
	
	public static void mapping (int[] buff){ //�����ڵ��ε��� - �����ε��� => �ѱ��ڸ���Ʈ
		//LinkedList<Integer> hanguljalist = new LinkedList<Integer>();//�ѱ��� �ʼ�,�ʼ�,�߼�,�߼�,����,���� �̰� ��¥
		
		UpAndDown.buflist.addAll(Uni2Braille.unicho2cho(buff[0]));//�ʼ������ڵ��δ쵦�� (1��)-> �����ε���(0�� �Ǵ� 1�� �Ǵ� 2��)
		UpAndDown.buflist.addAll(Uni2Braille.unijung2mo(buff[1]));/////�߼�
		UpAndDown.buflist.addAll(Uni2Braille.unijong2jong(buff[2]));///����
		


		
	}
	
	
}
