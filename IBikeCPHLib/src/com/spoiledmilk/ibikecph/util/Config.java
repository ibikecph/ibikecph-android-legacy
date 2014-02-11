// Copyright (C) 2013 City of Copenhagen.
//
// This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
// If a copy of the MPL was not distributed with this file, You can obtain one at 
// http://mozilla.org/MPL/2.0/.
package com.spoiledmilk.ibikecph.util;

public class Config {
	// public static String serverUrl = "http://ibikecph-staging.herokuapp.com/api";
	public static String serverUrl = "http://www.ibikecph.dk/api";
	public static final String API_SERVER_LOGIN = serverUrl + "/login";
	public static final String API_SERVER_REGISTER = serverUrl + "/users";
	public static final String GOOGLE_API_KEY = "AIzaSyAZwBZgYS-61R-gIvp4GtnekJGGrIKh0Dk";
	public static final String OSRM_SERVER_CARGO = "http://routes.ibikecph.dk/cargobike";
	public static final String OSRM_SERVER_BICYCLE = "http://routes.ibikecph.dk";
	public static String OSRM_SERVER = "http://routes.ibikecph.dk";
	public static final String GEOCODER = "http://geo.oiorest.dk/adresser";
	// TODO change the hash key on the facebook app configuration when the app
	// is submmited, using the signed app key
}
