package com.example.aa74_notificacionestilo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private NotificationManager notificador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button boton = (Button) findViewById(R.id.button1);

        notificador = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                notification(
                        1,
                        android.R.drawable.stat_sys_warning,
                        "NOTIFICACIÓN",
                        "NUEVO ESTILO DE NOTIFICACIONES"
                );
            }
        });
    }

    public void notification(int id, int iconId, String titulo, String contenido) {

        NotificationCompat.Builder builder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setLargeIcon(BitmapFactory.decodeResource(
                                getResources(),
                                R.mipmap.ic_launcher)
                        )
                        .setSmallIcon(iconId)
                        .setContentTitle(titulo)
                        .setColor(getResources().getColor(R.color.purple_500))
                        .setContentText(contenido).setNumber(id);

        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE);

        builder.setFullScreenIntent(fullScreenPendingIntent, true);

        // Construir la notificación y emitirla
        notificador.notify(id, builder.build());
    }
}