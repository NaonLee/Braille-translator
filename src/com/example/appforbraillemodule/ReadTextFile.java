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
		// FileChooser에서 받아온 intent 에서 파일 경로를 String 으로 저장

		File sdcard = Environment.getExternalStorageDirectory();

		//파일로 저장
		File file = new File(txtfile);
		
		StringBuilder text = new StringBuilder();

		//텍스트 파일에서 텍스트를 저장 하는 부분
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"euc-kr")); //한글 인코딩 euc-kr
			
			
			String line;
			while ((line = br.readLine()) != null) {
				text.append(line);
				text.append('\n');
			}
		} catch (IOException e) {
		
			Toast.makeText(getApplicationContext(), e.toString(), 1000).show();	//에러 메세지 toast message
		}

		String text_file = text.toString();	//StringBuilder 부분을 String으로 변환

		Intent intent2 = new Intent(ReadTextFile.this, UpAndDown.class);// UpAndDown으로 넘겨주기 위한 intent 설정 
		intent2.putExtra("text_file", text_file);	//텍스트 파일 내용을 보내기 위해 저장
		startActivity(intent2);	//UpAndDown 실행
		
		finish();
	}
}
