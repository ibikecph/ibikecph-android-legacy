// Copyright (C) 2013 City of Copenhagen.
//
// This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
// If a copy of the MPL was not distributed with this file, You can obtain one at 
// http://mozilla.org/MPL/2.0/.

package com.spoiledmilk.ibikecph;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.text.Spanned;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.Tracker;
import com.spoiledmilk.ibikecph.util.IbikePreferences;
import com.spoiledmilk.ibikecph.util.IbikePreferences.Language;
import com.spoiledmilk.ibikecph.util.LOG;
import com.spoiledmilk.ibikecph.util.SMDictionary;

public class IbikeApplication extends Application {

	private static IbikeApplication instance = null;
	public IbikePreferences settings;
	public SMDictionary dictionary;
	private static Typeface normalFont, boldFont, italicFont;

	// private static GoogleAnalytics mGaInstance;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		settings = new IbikePreferences(this);
		settings.load();

		LOG.d("Creating Application");

		// settings must be loaded before this
		dictionary = new SMDictionary(this);
		dictionary.init();

		normalFont = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeueLTCom-Md.ttf");
		boldFont = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeueLTCom-Bd.ttf");
		italicFont = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeueLTCom-It.ttf");

		// TODO enable when live
		GoogleAnalytics.getInstance(this).setAppOptOut(true);
	}

	public static Spanned getSpanned(String key) {
		return instance.dictionary.get(key);
	}

	public static String getString(String key) {
		return instance.dictionary.get(key).toString();
	}

	public static Context getContext() {
		return instance.getApplicationContext();
	}

	public void changeLanguage(Language language) {
		if (settings.getLanguage() != language) {
			LOG.d("Changing language to " + language.name());
			// this must be called before settings.setLanguage() because after
			// lng setting has
			// changed, listeners will be notified and will most probably try to
			// reload strings from dictionary
			dictionary.changeLanguage(language);
			settings.setLanguage(language);
		}
	}

	public static String getLanguageString() {
		return instance.settings.language == Language.DAN ? "da" : "en";
	}

	public static IbikePreferences getSettings() {
		return instance.settings;
	}

	public static boolean isUserLogedIn() {
		return PreferenceManager.getDefaultSharedPreferences(getContext()).contains("auth_token");
	}

	public static String getAuthToken() {
		return PreferenceManager.getDefaultSharedPreferences(getContext()).getString("auth_token", "");
	}

	public static boolean areFavoritesFetched() {
		return PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean("favorites_fetched", false);
	}

	public static void setFavoritesFetched(boolean b) {
		PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putBoolean("favorites_fetched", b).commit();

	}

	public static boolean isHistoryFetched() {
		return PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean("history_fetched", false);
	}

	public static void setHistoryFetched(boolean b) {
		PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putBoolean("history_fetched", b).commit();

	}

	public static void setIsFacebookLogin(boolean b) {
		PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putBoolean("is_facebook_login", b).commit();
	}

	public static boolean isFacebookLogin() {
		return PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean("is_facebook_login", false);
	}

	public static Typeface getNormalFont() {
		return normalFont;
	}

	public static Typeface getBoldFont() {
		return boldFont;
	}

	public static Typeface getItalicFont() {
		return italicFont;
	}

	public static Tracker getTracker() {
		return EasyTracker.getTracker();
	}
}
