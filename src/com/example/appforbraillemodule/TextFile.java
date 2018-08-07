package com.example.appforbraillemodule;

public class TextFile {

	// 글자를 받아와서 객체생성할꺼
	// 속성들과 메소드들이 있음

	private int strpt = -1;
	String text = null;
	int len = 0;

	public TextFile(String txt) {
		this.text = txt;
		this.len = text.length();
	}

	public char getOnetext() {
		char one = 'a';
		this.strpt++;
		if (text.charAt(strpt) != '\0') {
			one = text.charAt(strpt);

			return one;
		} else {
			return 0;
		}
	}

	// 한글자 받아왔을때 그 글자 타입 알려주기
	public int getType(char s) {
		int type = 9;
		// 한글, 영어, 숫자, 부호, 공백
		char what = text.charAt(strpt);
		int unicode = (int) what;
		// 유니코드 한글 시작 : 44032, 끝 : 55199
		if (unicode >= 44032 && unicode <= 55199) {
			type = 1; // 한글
		} else if (what == ' ') {
			type = 0;// 공백
		} else if ((unicode >= 65 && unicode < 90)
				|| (unicode >= 97 && unicode < 122)) {
			type = 2;// 대문자,소문자 영어

			// type = 3;// 소문자
		} else if (unicode >= 48 && unicode < 57) {
			type = 4; // 숫자
		}
		return type;
	}
}
