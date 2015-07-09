package com.epoint.erweima;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Erweima_Main_Activity extends Activity implements OnClickListener {

	public static final int REQUEST_CODE_CAPTURE = 1;

	private ImageButton main_capture_ib;
	private EditText main_url_et;
	private Button main_clean_btn;
	private Button main_load_url_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.erweima_main_activity);
		initView();
		main_url_et.setText(getUrl());
	}

	private void initView() {
		main_capture_ib = (ImageButton) findViewById(R.id.main_capture_ib);
		main_capture_ib.setOnClickListener(this);
		main_url_et = (EditText) findViewById(R.id.main_url_et);
		main_clean_btn = (Button) findViewById(R.id.main_clean_btn);
		main_clean_btn.setOnClickListener(this);
		main_load_url_btn = (Button) findViewById(R.id.main_load_url_btn);
		main_load_url_btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_capture_ib:
			break;
		case R.id.main_clean_btn:
			break;
		case R.id.main_load_url_btn:
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE_CAPTURE) {
			if (resultCode == RESULT_OK) {
				main_url_et.setText(data.getStringExtra("url") == null ? ""
						: data.getStringExtra("url"));
				setUrl(main_url_et.getText().toString());
			}
		}
	}

	private void setUrl(String url) {
		SharedPreferences sp = getSharedPreferences("urldata", MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("url", url);
		editor.commit();
	}

	private String getUrl() {
		SharedPreferences sp = getSharedPreferences("urldata", MODE_PRIVATE);
		return sp.getString("url", "");
	}
}
