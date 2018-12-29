package com.example.julio.socketplusqr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}


	public void iniciarVentanaQR(View v) {
		Intent ventanaqr = new Intent(MainActivity.this, ActivitiQR.class);
		startActivity(ventanaqr);
	}

}
