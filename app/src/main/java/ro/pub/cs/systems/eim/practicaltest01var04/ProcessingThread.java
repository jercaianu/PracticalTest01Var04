package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.Context;
import android.content.Intent;

/**
 * Created by ajercaianu on 4/3/17.
 */

class ProcessingThread extends Thread{
    private Context context;

    public ProcessingThread(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    private void sendMessage(String data) {
        Intent intent = new Intent();
        intent.setAction("string");
        intent.putExtra("data", data);
        context.sendBroadcast(intent);
    }
}
