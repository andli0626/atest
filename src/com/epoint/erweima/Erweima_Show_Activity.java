package com.epoint.erweima;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class Erweima_Show_Activity extends Activity implements OnClickListener
{

    private WebView show_url_wv;
    private Button show_stretch_btn;
    private Button show_shrink_btn;
    private long lastClickTime = 0;
    private boolean flag = false;
    private int scale = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.erweima_show_activity);
        initView();
        initIntent();
    }

    private void initView() {
        show_url_wv = (WebView) findViewById(R.id.show_url_wv);
        show_url_wv.setInitialScale(scale);
        show_url_wv.getSettings().setJavaScriptEnabled(true);
        show_url_wv.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
//        show_url_wv.getSettings().setBuiltInZoomControls(true);
        show_url_wv.getSettings().setSupportZoom(true);
        show_url_wv.setOnTouchListener(new OnTouchListener()
        {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        if (System.currentTimeMillis() - lastClickTime < 500) {
                            showOrHideTitle();
                        }
                        lastClickTime = System.currentTimeMillis();
                        break;
                }
                return false;
            }
        });
        show_url_wv.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        show_stretch_btn = (Button) findViewById(R.id.show_stretch_btn);
        show_stretch_btn.setOnClickListener(this);
        show_shrink_btn = (Button) findViewById(R.id.show_shrink_btn);
        show_shrink_btn.setOnClickListener(this);
    }

    private void showOrHideTitle() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        if (flag) {
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attrs);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            flag = false;
        }
        else {
            attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(attrs);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            flag = true;
        }
    }

    private void initIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            show_url_wv.loadUrl(bundle.getString("url"));
        }
    }

    @Override
    public void onClick(View v) {
        if (v == show_stretch_btn) {
            scale += 5;
            if (scale >= 200) {
                scale = 200;
            }
            show_url_wv.setInitialScale(scale);
        } else if (v == show_shrink_btn) {
            scale -= 5;
            if (scale <= 50) {
                scale = 50;
            }
            show_url_wv.setInitialScale(scale);            
        }
    }

}
