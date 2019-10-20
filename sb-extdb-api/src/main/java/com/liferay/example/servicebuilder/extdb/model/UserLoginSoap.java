/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.example.servicebuilder.extdb.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class UserLoginSoap implements Serializable {

	public static UserLoginSoap toSoapModel(UserLogin model) {
		UserLoginSoap soapModel = new UserLoginSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setScreenName(model.getScreenName());
		soapModel.setSystemName(model.getSystemName());
		soapModel.setLastLogin(model.getLastLogin());
		soapModel.setTotalLogins(model.getTotalLogins());
		soapModel.setLongestTimeBetweenLogins(
			model.getLongestTimeBetweenLogins());
		soapModel.setShortestTimeBetweenLogins(
			model.getShortestTimeBetweenLogins());

		return soapModel;
	}

	public static UserLoginSoap[] toSoapModels(UserLogin[] models) {
		UserLoginSoap[] soapModels = new UserLoginSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserLoginSoap[][] toSoapModels(UserLogin[][] models) {
		UserLoginSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new UserLoginSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserLoginSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserLoginSoap[] toSoapModels(List<UserLogin> models) {
		List<UserLoginSoap> soapModels = new ArrayList<UserLoginSoap>(
			models.size());

		for (UserLogin model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserLoginSoap[soapModels.size()]);
	}

	public UserLoginSoap() {
	}

	public String getPrimaryKey() {
		return _uuid;
	}

	public void setPrimaryKey(String pk) {
		setUuid(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public String getScreenName() {
		return _screenName;
	}

	public void setScreenName(String screenName) {
		_screenName = screenName;
	}

	public String getSystemName() {
		return _systemName;
	}

	public void setSystemName(String systemName) {
		_systemName = systemName;
	}

	public Date getLastLogin() {
		return _lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		_lastLogin = lastLogin;
	}

	public long getTotalLogins() {
		return _totalLogins;
	}

	public void setTotalLogins(long totalLogins) {
		_totalLogins = totalLogins;
	}

	public long getLongestTimeBetweenLogins() {
		return _longestTimeBetweenLogins;
	}

	public void setLongestTimeBetweenLogins(long longestTimeBetweenLogins) {
		_longestTimeBetweenLogins = longestTimeBetweenLogins;
	}

	public long getShortestTimeBetweenLogins() {
		return _shortestTimeBetweenLogins;
	}

	public void setShortestTimeBetweenLogins(long shortestTimeBetweenLogins) {
		_shortestTimeBetweenLogins = shortestTimeBetweenLogins;
	}

	private String _uuid;
	private String _screenName;
	private String _systemName;
	private Date _lastLogin;
	private long _totalLogins;
	private long _longestTimeBetweenLogins;
	private long _shortestTimeBetweenLogins;

}