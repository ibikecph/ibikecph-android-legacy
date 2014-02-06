// Copyright (C) 2013 City of Copenhagen.
//
// This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
// If a copy of the MPL was not distributed with this file, You can obtain one at 
// http://mozilla.org/MPL/2.0/.
package com.spoiledmilk.ibikecph.search;

import com.fasterxml.jackson.databind.JsonNode;
import com.spoiledmilk.ibikecph.R;

public class OriestData extends SearchListItem {

	public OriestData(JsonNode jsonNode) {
		super(jsonNode, nodeType.ORIEST);
	}

	public String getName() {
		String ret = "";
		ret = jsonNode.get("vejnavn").get("navn").asText() + " "
				+ jsonNode.get("husnr").asText() + ", "
				+ jsonNode.get("postnummer").get("nr").asText() + " "
				+ jsonNode.get("kommune").get("navn").asText() + ", Danmark";
		return ret;
	}

	public String getAdress() {
		String ret = "";
		ret = jsonNode.get("vejnavn").get("navn").asText() + " "
				+ jsonNode.get("husnr").asText() + ", "
				+ jsonNode.get("postnummer").get("nr").asText() + " "
				+ jsonNode.get("kommune").get("navn").asText() + ", Danmark";
		return ret;
	}

	public String getStreet() {
		String ret = "";
		ret = jsonNode.get("vejnavn").get("navn").asText();
		return ret;
	}

	public int getOrder() {
		return 2;
	}

	public String getZip() {
		String ret = "";
		ret = jsonNode.get("postnummer").get("nr").asText();
		return ret;
	}

	public String getCity() {
		String ret = "";
		ret = jsonNode.get("kommune").get("navn").asText();
		return ret;
	}

	public String getCountry() {
		String ret = "";
		ret = "";
		return ret;
	}

	public String getSource() {
		String ret = "";
		ret = "autocomplete";
		return ret;
	}

	public String getSubSource() {
		String ret = "";
		ret = "oiorest";
		return ret;
	}

	public int getRelevance(String srchString) {
		int ret = -1;
		String tmp = jsonNode.get("vejnavn").get("navn").asText() + " "
				+ jsonNode.get("husnr").asText() + ", "
				+ jsonNode.get("postnummer").get("nr").asText() + " "
				+ jsonNode.get("kommune").get("navn").asText() + ", Danmark";
		ret = pointsForName(tmp, tmp, srchString);
		return ret;
	}

	public String getGeocodeUrl() {
		String ret = "";
		ret = jsonNode.get("vejnavn").get("href").asText();
		return ret;
	}

	@Override
	public int getIconResourceId() {
		return R.drawable.search_magnify_icon;
	}

	@Override
	public String getFormattedNameForSearch() {
		return getName();
	}
	
	@Override
	public String getOneLineName() {
		return getName();
	}
}
