package com.example.appforbraillemodule;
/*
 * ���� �����ϱ�
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
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {  //�ڷΰ��� ��ư�� ������ ��
        if (keyCode == KeyEvent.KEYCODE_BACK) {	//���������� ���� ��� ���� ������ �̵�
        	if ((!currentDir.getName().equals("sdcard")) && (currentDir.getParentFile() != null)) {
	        	currentDir = currentDir.getParentFile();
	        	fill(currentDir);
        	} else {	// ���������� ������ ���� 
        		finish();
        	}
            return false;
        }
        return super.onKeyDown(keyCode, event);
	}

	private void fill(File f) {	//�о�� ���Ϲ� ������ ä��
		File[] dirs = null;
		if (fileFilter != null)
			dirs = f.listFiles(fileFilter);
		else 
			dirs = f.listFiles();
			
		this.setTitle(getString(R.string.currentDir) + ": " + f.getName());	//���� ��ġ
		List<Option> dir = new ArrayList<Option>();
		List<Option> fls = new ArrayList<Option>();
		try {
			for (File ff : dirs) {		
				if (ff.isDirectory() && !ff.isHidden())	// ������ Folder ���
					dir.add(new Option(ff.getName(), getString(R.string.folder), ff
							.getAbsolutePath(), true, false));
				else {		//������ filesize ���
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
	protected void onListItemClick(ListView l, View v, int position, long id) { //���� ���� �� ���� ��θ� �Ѱ���
		super.onListItemClick(l, v, position, id);
		Option o = adapter.getItem(position);	//���� ���� �޾ƿ���
		if (o.isFolder() || o.isParent()) {		//������ ������ folder���� parent ���� Ȯ�� 
			currentDir = new File(o.getPath());
			fill(currentDir);
		} else {
			onFileClick(o);
			fileSelected = new File(o.getPath());  // ������ ���� ��� ����
			
			Intent intent = new Intent(FileChooser.this,	//Intent ���� 
				ReadTextFile.class);
			
			intent.putExtra("fileSelected", fileSelected.getAbsolutePath());	//���� ��θ� intent�� �Ѱ��ִ� ������ ����
			startActivity(intent);	// ReadTextFile ����
			setResult(Activity.RESULT_OK, intent);
			finish();	
		}		
	}

	private void onFileClick(Option o) {	// ������ ���� Ȯ�� toast message 
		Toast.makeText(this, "File Clicked: " + o.getName(), 1000)
				.show();
	}
		
}