package com.example.appforbraillemodule;
/*
 * 파일 선택하기
 * 
 */
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class FileChooser extends ListActivity {    
	private File currentDir;
	private FileArrayAdapter adapter;
	private FileFilter fileFilter;
	private File fileSelected;
	private ArrayList<String> extensions;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			if (extras.getStringArrayList("filterFileExtension") != null) {
				extensions = extras.getStringArrayList("filterFileExtension");
				Log.i("AAA", extensions.size()+ "");
				fileFilter = new FileFilter() {
					public boolean accept(File pathname) {
						Log.i("AAA", pathname.getName()+ "");
						Log.i("AAA", pathname.getName()+ "");
						return (pathname.getName().contains(".")?extensions.contains(pathname.getName().substring(pathname.getName().lastIndexOf("."))):true);
					}
				};
			}
		}
		
		currentDir = new File("/sdcard/");
		fill(currentDir);		
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {  //뒤로가기 버튼을 눌렀을 때
        if (keyCode == KeyEvent.KEYCODE_BACK) {	//상위폴더가 있을 경우 상위 폴더로 이동
        	if ((!currentDir.getName().equals("sdcard")) && (currentDir.getParentFile() != null)) {
	        	currentDir = currentDir.getParentFile();
	        	fill(currentDir);
        	} else {	// 상위폴더가 없으면 종료 
        		finish();
        	}
            return false;
        }
        return super.onKeyDown(keyCode, event);
	}

	private void fill(File f) {	//읽어온 파일및 폴더를 채움
		File[] dirs = null;
		if (fileFilter != null)
			dirs = f.listFiles(fileFilter);
		else 
			dirs = f.listFiles();
			
		this.setTitle(getString(R.string.currentDir) + ": " + f.getName());	//현재 위치
		List<Option> dir = new ArrayList<Option>();
		List<Option> fls = new ArrayList<Option>();
		try {
			for (File ff : dirs) {		
				if (ff.isDirectory() && !ff.isHidden())	// 폴더는 Folder 명시
					dir.add(new Option(ff.getName(), getString(R.string.folder), ff
							.getAbsolutePath(), true, false));
				else {		//파일은 filesize 명시
					if (!ff.isHidden())
						fls.add(new Option(ff.getName(), getString(R.string.fileSize) + ": "
								+ ff.length(), ff.getAbsolutePath(), false, false));
				}
			}
		} catch (Exception e) {

		}
		Collections.sort(dir);
		Collections.sort(fls);
		dir.addAll(fls);
		if (!f.getName().equalsIgnoreCase("sdcard")) {
			if (f.getParentFile() != null) dir.add(0, new Option("..", getString(R.string.parentDirectory), f.getParent(), false, true));
		}
		adapter = new FileArrayAdapter(FileChooser.this, R.layout.file_view,
				dir);
		this.setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) { //파일 선택 후 파일 경로를 넘겨줌
		super.onListItemClick(l, v, position, id);
		Option o = adapter.getItem(position);	//파일 정보 받아오기
		if (o.isFolder() || o.isParent()) {		//선택한 파일이 folder인지 parent 인지 확인 
			currentDir = new File(o.getPath());
			fill(currentDir);
		} else {
			onFileClick(o);
			fileSelected = new File(o.getPath());  // 선택한 파일 경로 저장
			
			Intent intent = new Intent(FileChooser.this,	//Intent 설정 
				ReadTextFile.class);
			
			intent.putExtra("fileSelected", fileSelected.getAbsolutePath());	//파일 경로를 intent의 넘겨주는 값으로 저장
			startActivity(intent);	// ReadTextFile 시작
			setResult(Activity.RESULT_OK, intent);
			finish();	
		}		
	}

	private void onFileClick(Option o) {	// 선택한 파일 확인 toast message 
		Toast.makeText(this, "File Clicked: " + o.getName(), 1000)
				.show();
	}
		
}