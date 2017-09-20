package br.com.cmdweb.supermercadopromocoes;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by gabriel.malaquias on 29/08/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {



    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if(remoteMessage.getNotification() != null){
            Log.d("TAG-FCM", "Mensagem da notificação: " + remoteMessage.getNotification().getBody());
            this.enviarNotificacao(remoteMessage.getNotification().getBody(), remoteMessage.getData());
        }

        if(remoteMessage.getData() != null && remoteMessage.getData().size() > 0){
            Log.d("TAG-FCM", "Dados: " + remoteMessage.getData());
        }

    }

    private void enviarNotificacao(String mensagem, Map<String, String> dados){
        Intent intent = new Intent(this, MainActivity.class);

        Bundle bundle = new Bundle();
        for(String key: dados.keySet()){
            String valor = dados.get(key).toString();

            bundle.putString(key,valor);
        }
        intent.putExtras(bundle);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("FCM - Notificação")
                .setContentText((mensagem))
                .setAutoCancel(true)
                .setSound(som)
                .setContentIntent(pendingIntent);

        NotificationManager notificationMensager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationMensager.notify(0, notificationBuilder.build());
    }
}
