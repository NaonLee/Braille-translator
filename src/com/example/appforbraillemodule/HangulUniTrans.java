package com.example.appforbraillemodule;

public class HangulUniTrans {


	static final int BASE_CODE = 44032;
	static final int CHOSUNG = 588;
	static final int JUNGSUNG = 28;
	
	static int chojungjong_buf[]={0,0,0}; //유니코드 인덱스가 들어갈 버퍼 초성중성종성

	public static int[] chojungjong(char charTemp) {
		// 한글자를 받아서  한글을 초성, 중성, 종성으로 분리하기.
		// 유니코드 한글 시작 : 44032, 끝 : 55199
		// 한글 BASE_CODE + (0 * CHOSUNG + 2 * JUNGSUNG);
		// int cBase = charTemp - BASE_CODE; // BASE_CODE(440324) 제거
		int i = 0;
		// 초성
		int c1 = (charTemp - BASE_CODE) / CHOSUNG;
		// 중성
		int c2 = ((charTemp - BASE_CODE) - CHOSUNG * c1) / JUNGSUNG;
		// 종성
		int c3 = (charTemp - BASE_CODE) - CHOSUNG * c1 - JUNGSUNG * c2;

		chojungjong_buf[0] = (int)c1;
		chojungjong_buf[1] = (int)c2;
		chojungjong_buf[2] = (int)c3;
		
		return chojungjong_buf;
	}
	
	public static void mapping (int[] buff){ //유니코드인덱스 - 점자인덱스 => 한글자리스트
		//LinkedList<Integer> hanguljalist = new LinkedList<Integer>();//한글자 초성,초성,중성,중성,종성,종성 이게 진짜
		
		UpAndDown.buflist.addAll(Uni2Braille.unicho2cho(buff[0]));//초성유니코드인댁덱스 (1개)-> 점자인덱스(0개 또는 1개 또는 2개)
		UpAndDown.buflist.addAll(Uni2Braille.unijung2mo(buff[1]));/////중성
		UpAndDown.buflist.addAll(Uni2Braille.unijong2jong(buff[2]));///종성
		


		
	}
	
	
}
