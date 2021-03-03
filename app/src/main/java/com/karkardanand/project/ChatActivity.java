package com.karkardanand.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.DatagramChannel;

public class ChatActivity extends AppCompatActivity  {
EditText etchat;

Handler handler;
LinearLayout linearLayout;
private WebSocketClient webSocketClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        createWebSocketClient();
        etchat = (EditText)findViewById(R.id.edtmsg);
        linearLayout=(LinearLayout)findViewById(R.id.line123);
        handler = new Handler();
//        socket.connect();
//        socket.on("msg",handlerIncomingMessage);


    }

    private void createWebSocketClient() {

        URI uri;
        try {
            // Connect to local host
            uri = new URI("ws://111.111.111.11:8081/ws/chat/700f4ffadc36f73aabccbaf0c814650d/12/");
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        webSocketClient =new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {

            }

            @Override
            public void onMessage(String message) {

                TextView txt=new TextView(getApplicationContext());
                txt.setText(message);
                txt.setTextSize(22);
                txt.setTextColor( Color.BLACK );
                txt.setBackgroundColor( Color.BLUE );
                txt.setBackground( ContextCompat.getDrawable( ChatActivity.this,R.drawable.oderchat ) );

                linearLayout.addView( txt );

            }

            @Override
            public void onClose(int code, String reason, boolean remote) {

            }

            @Override
            public void onError(Exception ex) {

            }
        };
//        };
//        webSocketClient.setConnectTimeout(10000);
//        webSocketClient.setReadTimeout(60000);
//        webSocketClient.enableAutomaticReconnection(5000);
        webSocketClient.connect();
    }

//    public Emitter.Listener handlerIncomingMessage=new Emitter.Listener() {
//        @Override
//        public void call(final Object... args) {
//
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    JSONObject jsonObject=(JSONObject)args[0];
//                    try {
//                        Log.e("Exereeee", jsonObject.toString() );
//                        String msg="",send="";
//
//                        msg=jsonObject.getString("msg").toString();
//                        send=jsonObject.getString("send").toString();
//                        Log.e("Exereeee", send+":"+msg);
//                        TextView txt=new TextView(getApplicationContext());
//                        txt.setText(msg);
//                        txt.setTextSize(22);
//                        //Log.e("Exereeee21", msg );
//                        if (send.equals("me")){
//                            txt.setBackgroundColor(Color.YELLOW);
//                            txt.setGravity(Gravity.LEFT);
//                        }else {
//                            txt.setBackgroundColor(Color.BLUE);
//                            txt.setGravity(Gravity.RIGHT);
//                        }
//                        linearLayout.addView(txt);
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
//    };

    @Override
    protected void onDestroy() {

//        socket.disconnect();
        super.onDestroy();
    }
    public void sendmsg(String msg){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put( "message",msg);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        webSocketClient.send( jsonObject.toString() );
//        socket.emit("msg",msg);
    }
    public void btnSend(View view){
        RelativeLayout relativeLayout=new RelativeLayout( getApplicationContext() );
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
//        relativeLayout.setLayoutParams( params2 );
//        ImageView imageView=new ImageView( getApplicationContext() );
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT
//        );
//        imageView.setLayoutParams( params );
//        imageView.setBackground( ContextCompat.getDrawable( ChatActivity.this,R.drawable.ic_mychat) );
//        TextView txt=new TextView(getApplicationContext());
//        txt.setText(etchat.getText().toString());
//        txt.setTextSize(22);
//        txt.setTextColor( Color.WHITE );
//       // txt.setBackgroundColor( Color.BLUE );
//       // txt.setGravity( Gravity.CENTER_HORIZONTAL );
//        params.gravity=Gravity.RIGHT;
//        params.setMargins(0, 10, 0, 0);
//        txt.setLayoutParams( params );
//        txt.setPaddingRelative( 40,10,40,140 );
//        txt.setPadding( 40,10,40,140 );
//        txt.setGravity(Gravity.CENTER);
//        relativeLayout.addView( imageView );
//        relativeLayout.addView( txt );
      //  txt.setBackground( ContextCompat.getDrawable( ChatActivity.this,R.drawable.ic_mychat) );



        linearLayout.addView( relativeLayout );



    }


}