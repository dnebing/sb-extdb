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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <p>
 * This class is a wrapper for {@link UserLogin}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserLogin
 * @generated
 */
@ProviderType
public class UserLoginWrapper
	extends BaseModelWrapper<UserLogin>
	implements UserLogin, ModelWrapper<UserLogin> {

	public UserLoginWrapper(UserLogin userLogin) {
		super(userLogin);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("screenName", getScreenName());
		attributes.put("systemName", getSystemName());
		attributes.put("lastLogin", getLastLogin());
		attributes.put("totalLogins", getTotalLogins());
		attributes.put(
			"longestTimeBetweenLogins", getLongestTimeBetweenLogins());
		attributes.put(
			"shortestTimeBetweenLogins", getShortestTimeBetweenLogins());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		String screenName = (String)attributes.get("screenName");

		if (screenName != null) {
			setScreenName(screenName);
		}

		String systemName = (String)attributes.get("systemName");

		if (systemName != null) {
			setSystemName(systemName);
		}

		Date lastLogin = (Date)attributes.get("lastLogin");

		if (lastLogin != null) {
			setLastLogin(lastLogin);
		}

		Long totalLogins = (Long)attributes.get("totalLogins");

		if (totalLogins != null) {
			setTotalLogins(totalLogins);
		}

		Long longestTimeBetweenLogins = (Long)attributes.get(
			"longestTimeBetweenLogins");

		if (longestTimeBetweenLogins != null) {
			setLongestTimeBetweenLogins(longestTimeBetweenLogins);
		}

		Long shortestTimeBetweenLogins = (Long)attributes.get(
			"shortestTimeBetweenLogins");

		if (shortestTimeBetweenLogins != null) {
			setShortestTimeBetweenLogins(shortestTimeBetweenLogins);
		}
	}

	/**
	 * Returns the last login of this user login.
	 *
	 * @return the last login of this user login
	 */
	@Override
	public Date getLastLogin() {
		return model.getLastLogin();
	}

	/**
	 * Returns the longest time between logins of this user login.
	 *
	 * @return the longest time between logins of this user login
	 */
	@Override
	public long getLongestTimeBetweenLogins() {
		return model.getLongestTimeBetweenLogins();
	}

	/**
	 * Returns the primary key of this user login.
	 *
	 * @return the primary key of this user login
	 */
	@Override
	public String getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the screen name of this user login.
	 *
	 * @return the screen name of this user login
	 */
	@Override
	public String getScreenName() {
		return model.getScreenName();
	}

	/**
	 * Returns the shortest time between logins of this user login.
	 *
	 * @return the shortest time between logins of this user login
	 */
	@Override
	public long getShortestTimeBetweenLogins() {
		return model.getShortestTimeBetweenLogins();
	}

	/**
	 * Returns the system name of this user login.
	 *
	 * @return the system name of this user login
	 */
	@Override
	public String getSystemName() {
		return model.getSystemName();
	}

	/**
	 * Returns the total logins of this user login.
	 *
	 * @return the total logins of this user login
	 */
	@Override
	public long getTotalLogins() {
		return model.getTotalLogins();
	}

	/**
	 * Returns the uuid of this user login.
	 *
	 * @return the uuid of this user login
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the last login of this user login.
	 *
	 * @param lastLogin the last login of this user login
	 */
	@Override
	public void setLastLogin(Date lastLogin) {
		model.setLastLogin(lastLogin);
	}

	/**
	 * Sets the longest time between logins of this user login.
	 *
	 * @param longestTimeBetweenLogins the longest time between logins of this user login
	 */
	@Override
	public void setLongestTimeBetweenLogins(long longestTimeBetweenLogins) {
		model.setLongestTimeBetweenLogins(longestTimeBetweenLogins);
	}

	/**
	 * Sets the primary key of this user login.
	 *
	 * @param primaryKey the primary key of this user login
	 */
	@Override
	public void setPrimaryKey(String primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the screen name of this user login.
	 *
	 * @param screenName the screen name of this user login
	 */
	@Override
	public void setScreenName(String screenName) {
		model.setScreenName(screenName);
	}

	/**
	 * Sets the shortest time between logins of this user login.
	 *
	 * @param shortestTimeBetweenLogins the shortest time between logins of this user login
	 */
	@Override
	public void setShortestTimeBetweenLogins(long shortestTimeBetweenLogins) {
		model.setShortestTimeBetweenLogins(shortestTimeBetweenLogins);
	}

	/**
	 * Sets the system name of this user login.
	 *
	 * @param systemName the system name of this user login
	 */
	@Override
	public void setSystemName(String systemName) {
		model.setSystemName(systemName);
	}

	/**
	 * Sets the total logins of this user login.
	 *
	 * @param totalLogins the total logins of this user login
	 */
	@Override
	public void setTotalLogins(long totalLogins) {
		model.setTotalLogins(totalLogins);
	}

	/**
	 * Sets the uuid of this user login.
	 *
	 * @param uuid the uuid of this user login
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	protected UserLoginWrapper wrap(UserLogin userLogin) {
		return new UserLoginWrapper(userLogin);
	}

}