// Copyright (C) 2013 City of Copenhagen.
//
// This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
// If a copy of the MPL was not distributed with this file, You can obtain one at 
// http://mozilla.org/MPL/2.0/.

package com.spoiledmilk.ibikecph;

import java.text.SimpleDateFormat;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.analytics.tracking.android.EasyTracker;

public class AboutActivity extends Activity {

	TextView textAboutTitle;
	TextView textAboutText;
	ImageButton btnBack;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.about_activity);

		btnBack = ((ImageButton) findViewById(R.id.btnBack));
		btnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AboutActivity.this.finish();
				overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

			}

		});

		textAboutTitle = (TextView) findViewById(R.id.textAboutTitle);
		textAboutText = (TextView) findViewById(R.id.textAboutText);
		// textAboutText.setMovementMethod(new ScrollingMovementMethod());
		textAboutText.setMovementMethod(LinkMovementMethod.getInstance());
		textAboutText.setClickable(true);

		getBuildInfo();
	}

	@Override
	public void onResume() {
		super.onResume();
		initStrings();
	}

	private void initStrings() {
		textAboutTitle.setText(IbikeApplication.getString("about_ibikecph_title"));
		textAboutTitle.setTypeface(IbikeApplication.getBoldFont());
		String text = IbikeApplication.getString("about_text");
		textAboutText.setText(text);
		// textAboutText.setTypeface(IbikeApplication.getNormalFont());
	}

	@Override
	public void onStart() {
		super.onStart();
		EasyTracker.getInstance().activityStart(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		EasyTracker.getInstance().activityStop(this);
	}

	protected void getBuildInfo() {

		try {
			ApplicationInfo ai = getPackageManager().getApplicationInfo(getPackageName(), 0);
			ZipFile zf = new ZipFile(ai.sourceDir);
			ZipEntry ze = zf.getEntry("classes.dex");
			long time = ze.getTime();
			String s = SimpleDateFormat.getInstance().format(new java.util.Date(time));
			((TextView) findViewById(R.id.textBuild)).setText("Build: " + s);
		} catch (Exception e) {
		}

		// new Thread(new Runnable() {
		// @Override
		// public void run() {
		// String urlString = "https://rink.hockeyapp.net/api/2/apps/" + MapActivity.HOCKEY_APP_ID + "/app_versions";
		// JsonNode ret = null;
		// LOG.d("GET api request, url = " + urlString);
		// HttpParams myParams = new BasicHttpParams();
		// HttpConnectionParams.setConnectionTimeout(myParams, 20000);
		// HttpConnectionParams.setSoTimeout(myParams, 20000);
		// HttpClient httpclient = new DefaultHttpClient(myParams);
		// HttpGet httpget = null;
		//
		// URL url = null;
		//
		// try {
		//
		// url = new URL(urlString);
		// httpget = new HttpGet(url.toString());
		// httpget.setHeader("Content-type", "application/json");
		// httpget.setHeader("X-HockeyAppToken", "de2dc9b834364fa1b701905faf8e6863");
		// HttpResponse response = httpclient.execute(httpget);
		// String serverResponse = EntityUtils.toString(response.getEntity());
		// LOG.d("API response = " + serverResponse);
		// ret = Util.stringToJsonNode(serverResponse);
		// long timestamp = ret.get("app_versions").get(0).get("timestamp").asLong();
		// final Date date = new Date(timestamp * 1000);
		// AboutActivity.this.runOnUiThread(new Runnable() {
		// @Override
		// public void run() {
		// ((TextView) findViewById(R.id.textBuild)).setText("Build: " + date.toString());
		// }
		// });
		// } catch (Exception e) {
		// if (e != null && e.getLocalizedMessage() != null)
		// LOG.e(e.getLocalizedMessage());
		// }
		//
		// }
		// }).start();
	}

}
