package com.example.appforbraillemodule;

public class TextFile {

	// ���ڸ� �޾ƿͼ� ��ü�����Ҳ�
	// �Ӽ���� �޼ҵ���� ����

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

	// �ѱ��� �޾ƿ����� �� ���� Ÿ�� �˷��ֱ�
	public int getType(char s) {
		int type = 9;
		// �ѱ�, ����, ����, ��ȣ, ����
		char what = text.charAt(strpt);
		int unicode = (int) what;
		// �����ڵ� �ѱ� ���� : 44032, �� : 55199
		if (unicode >= 44032 && unicode <= 55199) {
			type = 1; // �ѱ�
		} else if (what == ' ') {
			type = 0;// ����
		} else if ((unicode >= 65 && unicode < 90)
				|| (unicode >= 97 && unicode < 122)) {
			type = 2;// �빮��,�ҹ��� ����

			// type = 3;// �ҹ���
		} else if (unicode >= 48 && unicode < 57) {
			type = 4; // ����
		}
		return type;
	}
}
