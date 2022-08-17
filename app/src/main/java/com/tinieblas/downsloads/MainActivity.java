package com.tinieblas.downsloads;

import static androidx.core.app.NotificationCompat.PRIORITY_DEFAULT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;
    TextView resultado, Url, Destino;
    private RadioButton radioButtonmp3, radioButtonmp4;

    private final static String CHANNEL_ID = "Notificacion";
    private final static int Notificacion_ID = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        resultado = findViewById(R.id.textView);
        Url = findViewById(R.id.textView1);
        Destino = findViewById(R.id.editTextdestino);
        radioButtonmp3 = findViewById(R.id.radio_mp3);
        radioButtonmp4 = findViewById(R.id.radio_mp4);

        //final ClipboardManager clipBoard= (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        //clipBoard.setText();
        //Url.setText(clipBoard.getText());
        //Toast.makeText(this, clipBoard.getText()+"aa", Toast.LENGTH_SHORT).show();

        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }
        //Python python = Python.getInstance();
        //PyObject pythonObjeto = python.getModule("script");

        //button.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        try{

                    /*NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(MainActivity.this, "M_CH_ID");

                    notificationBuilder.setAutoCancel(true)
                            .setDefaults(Notification.DEFAULT_ALL)
                            .setWhen(System.currentTimeMillis())
                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                            .setTicker("Hearty365")
                            .setPriority(Notification.PRIORITY_MAX) // this is deprecated in API 26 but you can still use for below 26. check below update for 26 API
                            .setContentTitle("Default notification")
                            .setContentText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
                            .setContentInfo("Info");

                    NotificationManager notificationManager = (NotificationManager) MainActivity.this.getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(1, notificationBuilder.build());*/
                    //clipboard.setText("Text to copy");
                    //clipboard.getText();

                    /*File ruta_sd = Environment.getExternalStorageDirectory();
                    File nuevaCarpeta = new File(ruta_sd.getAbsolutePath(), "miCarpetaa");
                    nuevaCarpeta.mkdirs();
                    nuevaCarpeta.canRead();
                    nuevaCarpeta.canWrite();
                    //nuevaCarpeta.getAbsolutePath();

                    //File nuevaCarpeta = new File(ruta_sd.getAbsolutePath(), "miCarpeta");
                    //resultado.setText(nuevaCarpeta.getPath());
                    resultado.setText(nuevaCarpeta.getAbsolutePath());
                    //System.out.println(nuevaCarpeta.getAbsolutePath());*/
                    //String folder_main = "Tinieblas Descargas";

                   // resultado.setText(f.getAbsolutePath());

                    //String filtrar = ".mp3";
                    //PyObject object = pythonObjeto.callAttr("main", filtrar, Url.getText().toString(), Destino.getText().toString());
                    //resultado.setText(object.toString());

                    //resultado.setText("hola");

                    //createNotificationChannel();
                    //notificaty("pathhhhhhhhhh");
                    //File uri = new File(Uri.parse("/storage/emulated/0/Download/Blah.mp4").getPath());
                    //Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    //uri.getPath();


                    //startActivity(intent);

                    //Toast.makeText(MainActivity.this, "hola", Toast.LENGTH_SHORT).show();
                //}catch (Exception e){
               //     resultado.setText(""+e);
               // }


           // }
       // });
//
        //downsload(MainActivity.this);
    }

    /*public String onRadioButton(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        String filtrar="";
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_mp4:
                if (checked)
                    // Pirates are the best
                    Toast.makeText(this, ".mp4", Toast.LENGTH_SHORT).show();
                    //procesando(filtrar);
                    return ".mp4";
            case R.id.radio_mp3:
                if (checked)
                    // Ninjas rule
                    Toast.makeText(this, ".mp3", Toast.LENGTH_SHORT).show();
                    //procesando(filtrar);
                    return ".mp3";
        }
        return "Error";
    }*/

    public void procesando(String formato){
        try {
            Python python = Python.getInstance();
            PyObject pythonObjeto = python.getModule("script");
            PyObject object = pythonObjeto.callAttr("main", formato, Url.getText().toString(), Destino.getText().toString());
            resultado.setText(object.toString());
        }catch (Exception e){
            resultado.setText(e.toString());
        }

    }
    public void descargar(View view){
        String folder_main = Environment.getExternalStorageDirectory()+"/Download/Tinieblas Descargas";
        File file = new File(Environment.getExternalStorageDirectory()+"/Download/", folder_main);

        if (!file.exists()) {
            file.mkdirs();
            //Toast.makeText(this, "No esta creado pero ya ha sido creado", Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(this, "Ya esta creado", Toast.LENGTH_SHORT).show();
        if (radioButtonmp3.isChecked()){
            Toast.makeText(this, "mp3", Toast.LENGTH_SHORT).show();
            procesando(".mp3");
        }else
            if (radioButtonmp4.isChecked()){
                Toast.makeText(this, "mp4", Toast.LENGTH_SHORT).show();
                procesando(".mp4");
        }else
            if(!radioButtonmp3.isChecked() || !radioButtonmp4.isChecked()){
                Toast.makeText(this, "Escoga el formato", Toast.LENGTH_SHORT).show();
            }
    }

    public void ViewVideo(View view){
        //String folder_main = Environment.getExternalStorageDirectory()+"/Download/Tinieblas Descargas";
        //File f = new File(Environment.getExternalStorageDirectory()+"/Download", folder_main);
        /*Uri uri = Uri.parse(folder_main);
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(uri, "*");*/
        //startActivity(intent);
    }

    private void createNotificationChannel() {
        //   26                    30
        if (Build.VERSION_CODES.O <= Build.VERSION.SDK_INT) {
            CharSequence name = "Notificacion";
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public void notificaty(String path) {
        setPendingIntent(path);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setContentTitle("Descarga Exitosa...");
        builder.setContentText(path);
        builder.setPriority(PRIORITY_DEFAULT);
        builder.setLights(Color.BLUE, 1000, 1000);
        builder.setVibrate(new long[]{1000, 1000, 1000, 1000});
        builder.setDefaults(Notification.DEFAULT_SOUND);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(Notificacion_ID, builder.build());

    }

    private void setPendingIntent(String path){
        Toast.makeText(this, "PUM " + path, Toast.LENGTH_SHORT).show();
    }

    private void showNewNotification(){
        //setPendi
    }

    public void downsload(Context context){

        try {
            // ex: http://www.youtube.com/watch?v=Nj6PFaDmp6c
            /*String url = "http://www.youtube.com/watch?v=Nj6PFaDmp6c";
            // ex: "/Users/axet/Downloads"
            String path = "C:\\Users\\master\\Pictures\\TokoMegawa\\Productos";
            VGet v = new VGet(new URL(url), new File(path));
            v.download();*/
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }
    }

    @Override
    public void onClick(View view) {

    }
}


















