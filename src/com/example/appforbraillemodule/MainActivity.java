package com.example.appforbraillemodule;
//맨 처음 화면, 블루투스 선택
import java.util.Set;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends BT_Preference {

	BluetoothAdapter mBluetoothAdapter;
	BluetoothDevice mmDevice;
    BluetoothSocket mmSocket;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //페어링 된 블루투스를 ListView로 보여줌
        ListView menuList = (ListView) findViewById(R.id.PairedView_Menu);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if(pairedDevices.size() > 0)
        {
        	String[] paired = new String[pairedDevices.size()];
        	int i = 0;
        	for(BluetoothDevice device : pairedDevices)
            {
        		paired[i++] = device.getName();  	
            }
        	
        	ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, R.layout.menu_item, paired);
            menuList.setAdapter(adapt);
            
            //ListView에서 블루투스 기기 선택시
            menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            	
            	
                public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                
                    TextView textView = (TextView) itemClicked;
                    String strText = textView.getText().toString();
                    
                    //선택된 블루투스 기기 정보를 넘겨주기 위해 BT_Pregerence에서 정의한 BP_PREFERENCES_PAIRED_DEVICE의 이름으로 저장
                    SharedPreferences mPairedSettings;
                    mPairedSettings = getSharedPreferences(BT_PREFERENCES, Context.MODE_PRIVATE);
                    Editor editor = mPairedSettings.edit();
                    editor.putString(BP_PREFERENCES_PAIRED_DEVICE, strText);
                    editor.commit();
                    
                    //Text 선택으로 넘어감
                    startActivity(new Intent(MainActivity.this, TextActivity.class));                    
                }
            });            
        }              
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
