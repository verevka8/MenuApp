package com.example.test.data.network.webSocketClient;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.test.BuildConfig;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompCommand;
import ua.naiksoftware.stomp.dto.StompHeader;
import ua.naiksoftware.stomp.dto.StompMessage;


public class WebSocketClient {
    private StompClient client;
    private WebSocketCallBack messageCallback;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final String TAG = "MyWebSockets";

    public WebSocketClient(WebSocketCallBack messageCallback) {
        this.messageCallback = messageCallback;
    }

    public void connectToSession(String sessionId){
        String BASE_URL = "ws://" + BuildConfig.BASE_URL + "/gs";
        client = Stomp.over(Stomp.ConnectionProvider.JWS, BASE_URL);
        client.connect();

        Disposable disposable = client.topic("/topic/session." + sessionId).subscribe(topicMessage -> {
            Log.d(TAG, "Received: " + topicMessage.getPayload());
            messageCallback.onMessageReceived(topicMessage.getPayload());
        }, throwable -> {
            Log.d(TAG, "Error on subscribe", throwable);
        });
        compositeDisposable.add(disposable);

        Disposable disposable1 = client.lifecycle().subscribe(lifecycleEvent -> {
            switch (lifecycleEvent.getType()) {
                case OPENED:
                    Log.d(TAG, "Stomp connection opened");
                    break;
                case ERROR:
                    Log.e(TAG, "Error", lifecycleEvent.getException());
                    break;
                case CLOSED:
                    Log.d(TAG, "Stomp connection closed");
                    break;
            }
        });
        compositeDisposable.add(disposable1);
    }

    @SuppressLint("CheckResult")
    public void sendMessage(String message,String sessionId, String destination) {
        List<StompHeader> headers = new ArrayList<>();
        headers.add(new StompHeader(StompHeader.DESTINATION,destination));
        headers.add(new StompHeader("sessionId",sessionId));
        StompMessage stompMessage = new StompMessage(StompCommand.SEND, headers,message);
        client.send(stompMessage).subscribe(() -> {
            Log.d(TAG, "Message sent successfully");
        }, throwable -> {
            Log.d(TAG, "Error sending message", throwable);
        });
    }

    public void clear() {
        compositeDisposable.dispose();
    }

}
