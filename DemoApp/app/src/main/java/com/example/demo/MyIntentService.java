package com.example.demo;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.demo.utils.ThreadUtils;

import timber.log.Timber;

public class MyIntentService extends IntentService {

    Handler mHandler = new Handler(Looper.getMainLooper());

    public MyIntentService(String name) {
        super(name);
    }

    public MyIntentService() {
        super(MyIntentService.class.getSimpleName());
    }

    public static void enqueue(Context context) {
        Intent intent = new Intent(context, MyIntentService.class);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        ThreadUtils.sleep(15 * 1000);
        showToast("15s elapsed.");

        ThreadUtils.sleep(15 * 1000);
        showToast("30s elapsed.");

        ThreadUtils.sleep(15 * 1000);
        showToast("45s elapsed.");

        ThreadUtils.sleep(15 * 1000);
        showToast("60s elapsed.");

        ThreadUtils.sleep(15 * 1000);
        showToast("90s elapsed.");

        ThreadUtils.sleep(15 * 1000);
        showToast("90s elapsed.");
    }

    @Override
    public void onCreate() {
        Timber.d("onCreate");
        super.onCreate();
        showToast("CREATED");
    }

    @Override
    public void onDestroy() {
        Timber.d("onDestroy");
        super.onDestroy();
        showToast("DESTROYED");
    }

    void showToast(String message) {
        final String textClass = this.getClass().getSimpleName();
        final String text = textClass + ": " + message;
        Timber.d("showToast: text=%s", text);

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
