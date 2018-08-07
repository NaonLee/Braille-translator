package com.example.appforbraillemodule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

public class ReadTextFile extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		String txtfile = intent.getStringExtra("fileSelected");
		// FileChooser���� �޾ƿ� intent ���� ���� ��θ� String ���� ����

		File sdcard = Environment.getExternalStorageDirectory();

		//���Ϸ� ����
		File file = new File(txtfile);
		
		StringBuilder text = new StringBuilder();

		//�ؽ�Ʈ ���Ͽ��� �ؽ�Ʈ�� ���� �ϴ� �κ�
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"euc-kr")); //�ѱ� ���ڵ� euc-kr
			
			
			String line;
			while ((line = br.readLine()) != null) {
				text.append(line);
				text.append('\n');
			}
		} catch (IOException e) {
		
			Toast.makeText(getApplicationContext(), e.toString(), 1000).show();	//���� �޼��� toast message
		}

		String text_file = text.toString();	//StringBuilder �κ��� String���� ��ȯ

		Intent intent2 = new Intent(ReadTextFile.this, UpAndDown.class);// UpAndDown���� �Ѱ��ֱ� ���� intent ���� 
		intent2.putExtra("text_file", text_file);	//�ؽ�Ʈ ���� ������ ������ ���� ����
		startActivity(intent2);	//UpAndDown ����
		
		finish();
	}
}
