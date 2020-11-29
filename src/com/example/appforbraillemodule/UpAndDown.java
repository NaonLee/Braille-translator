package com.example.appforbraillemodule;
//블루투스를 연결하고 버튼 제어
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class UpAndDown extends BT_Preference implements OnClickListener, TextToSpeech.OnInitListener  {
    private TextToSpeech tts; 

	int index = 0;
	String tvstr = null;
	// public static LinkedList<Integer> HOTLIST;
	public static LinkedList<Integer> buflist;

	//어댑터
    BluetoothAdapter mBluetoothAdapter;
    
    //블루투스 열기
	BluetoothSocket mmSocket;

	//블루투스 기기 정보
    BluetoothDevice mmDevice;
    
    //블루투스로 데이터 전송
    OutputStream mmOutputStream;
    //블루투스에서 데이터 받기
    InputStream mmInputStream;
    
    boolean is_connected = false;
    String devicename;
    
   	//글자 읽어오기
    Thread workerThread;
    byte[] readBuffer;
    int readBufferPosition;
    int counter;
    volatile boolean stopWorker;
    
    
    Button up;
    Button down;
    
    Button sendButton;
    TextView info_textview;
    EditText myTextbox;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upanddown);
        

        //MainActivity에서 저장한 블루투스 이름 
        SharedPreferences mPairedSettings;
        mPairedSettings = getSharedPreferences(BT_PREFERENCES, Context.MODE_PRIVATE);
        if (!mPairedSettings.contains(BP_PREFERENCES_PAIRED_DEVICE)) {
          //블루투스가 선택되지 않았을 경우
        }
        devicename = mPairedSettings.getString(BP_PREFERENCES_PAIRED_DEVICE, "");
        
        
        //텍스트를 음성으로 읽어주는 기능(비활성화)
        tts = new TextToSpeech(this, this); 
        


		Intent intent = getIntent(); // intent 받아오기
		String book_text = intent.getStringExtra("text_file"); // 텍스트 string
																// 받아오기
        
		// HOTLIST = new LinkedList<Integer>();
				buflist = new LinkedList<Integer>();
				// String a = "";// 텍스트 받아온것

				TextFile txt = new TextFile(book_text);// 객체생성
				for (int i = 0; i < book_text.length(); i++) {

					char buf = txt.getOnetext();// 한글자 받아오기
					int type = txt.getType(buf);
					// 글자의 타입을 받아와서 하는일을 분리
					if (type == 1) {
						// 한글이야
						int[] chojungjong = HangulUniTrans.chojungjong(buf);
						HangulUniTrans.mapping(chojungjong);
					} else if (type == 0) {
						// 공백 공백
						buflist.add(47);// 공백
					} else if (type == 2) {
						buflist.addAll(Uni2Braille.Eng(buf));

						// 영어
					} else if (type == 4) {

						// 숫자
					}
				}
				
        up = (Button)findViewById(R.id.btnup);
        down = (Button)findViewById(R.id.btndown);
        
        up.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                try 
                {
                    /****** 블루투스에 전송할 부분 *******/
    				if (index == 0) {
    					for (int j = 0; j < index + 1; j++) { // 초기 아무것도 없을때 처음을
    															// 보내주는 부분
    						if (tvstr == null) {
    							tvstr = buflist.get(j) + " ";
    							 mmOutputStream.write((int) buflist.get(j));
    							
    						} else {
    							tvstr += buflist.get(j) + " ";
    							 mmOutputStream.write((int) buflist.get(j));
    						}
    					}
    					tvstr = null;
    					index += 1;
    				} else { // 다음 글자 읽어오기
    					for (int j = index; j < index + 1; j++) {
    						if (tvstr == null) {
    							tvstr = buflist.get(j) + " ";
    							 mmOutputStream.write((int) buflist.get(j));
    							
    						} else {
    							tvstr += buflist.get(j) + " ";
    							 mmOutputStream.write((int) buflist.get(j));
    						}
    					}
    					tvstr = null;
    					index += 1;
    				}
    			}
                catch (IOException ex) { 
                }
            }
        });
        
        down.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                try 
                {
                	/****** 블루투스에 전송할 부분 *******/
    				if (index == 0) { // 초기 아무것도 없을때 처음을 보내주는 부분
    					for (int j = 0; j < index + 1; j++) {
    						if (tvstr == null) {
    							tvstr = buflist.get(j) + " ";
    							 mmOutputStream.write((int) buflist.get(j));
   						} else {
    							tvstr += buflist.get(j) + " ";
    							 mmOutputStream.write((int) buflist.get(j));
    						}
    					}
    					tvstr = null;
    					index += 1;
    				}

    				else { // 이전 글자 읽어오기
    					for (int j = index - 1; j < index; j++) {
    						if (tvstr == null) {
    							tvstr = buflist.get(j) + " ";
    							 mmOutputStream.write((int) buflist.get(j));
   						} else {
    							tvstr += buflist.get(j) + " ";
    							 mmOutputStream.write((int) buflist.get(j));
    							}
    					}
    					tvstr = null;
    					index -= 1;
    				}           
    			}               
                catch (IOException ex) {               
                }
            }     
        });
    }
    
    //뒤로가기 버튼을 누를 때 블루투스 연결 끊기
    @Override 
    public void onBackPressed() { 
    	try 
        {
		     try {

		            Thread.sleep(500);

		      } catch (InterruptedException e) { }
			 closeBT();
			 try {

		            Thread.sleep(1000);

		      } catch (InterruptedException e) { }
        }
        catch (IOException ex) { }
    	super.onBackPressed(); 
    } 

    //tts초기화 성공시 블루투스 연결
    public void onInit(int status) { 
    	  
        if (status == TextToSpeech.SUCCESS) { 
  
            int result = tts.setLanguage(Locale.US); 
  
            if (result == TextToSpeech.LANG_MISSING_DATA 
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) { 
                Log.e("TTS", "This Language is not supported"); 
            } else { 
               // btnSpeak.setEnabled(true); 
                
                try 
                {
                    findBT();
                    openBT();
                    
                }
                catch (IOException ex) {
                	startActivity(new Intent(UpAndDown.this, MainActivity.class));
                    
                }
            } 
  
        } else { 
            Log.e("TTS", "Initilization Failed!"); 
        } 
    } 
    
    //텍스트의 음성화(비할성화)
    private void speakOut(String txt) { 
    	  
        //String text = txtText.getText().toString(); 
    	
  
        tts.speak(txt, TextToSpeech.QUEUE_FLUSH, null); 
    }
    
    //이용자가 선택한 블루투스 탐색
    void findBT()
    {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter == null)
        {
        }
        
        if(!mBluetoothAdapter.isEnabled())
        {
            Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBluetooth, 0);
        }
        
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if(pairedDevices.size() > 0)
        {
            for(BluetoothDevice device : pairedDevices)
            {
                if(device.getName().equals(devicename)) 
                {
                    mmDevice = device;
                    break;
                }
            }
        }
    }
    
    //블루투스와 핸드폰 연결
    void openBT() throws IOException
    {
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); //시리얼번호
        mmSocket = mmDevice.createRfcommSocketToServiceRecord(uuid);        
        mmSocket.connect();
        mmOutputStream = mmSocket.getOutputStream();
        mmInputStream = mmSocket.getInputStream();
        is_connected = true;
        
        
    }
 
    
    //핸드폰과 연결 해제
    void closeBT() throws IOException
    {
        try {

            Thread.sleep(100);

      } catch (InterruptedException e) { }
        stopWorker = true;
        mmOutputStream.close();
        mmInputStream.close();
        mmSocket.close();
        
        is_connected = false;
    }
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
