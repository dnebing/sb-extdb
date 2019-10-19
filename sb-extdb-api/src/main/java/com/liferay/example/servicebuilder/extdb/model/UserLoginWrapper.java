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

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link UserLogin}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserLogin
 * @generated
 */
public class UserLoginWrapper implements ModelWrapper<UserLogin>, UserLogin {

	public UserLoginWrapper(UserLogin userLogin) {
		_userLogin = userLogin;
	}

	@Override
	public Class<?> getModelClass() {
		return UserLogin.class;
	}

	@Override
	public String getModelClassName() {
		return UserLogin.class.getName();
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

	@Override
	public Object clone() {
		return new UserLoginWrapper((UserLogin)_userLogin.clone());
	}

	@Override
	public int compareTo(UserLogin userLogin) {
		return _userLogin.compareTo(userLogin);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _userLogin.getExpandoBridge();
	}

	/**
	 * Returns the last login of this user login.
	 *
	 * @return the last login of this user login
	 */
	@Override
	public Date getLastLogin() {
		return _userLogin.getLastLogin();
	}

	/**
	 * Returns the longest time between logins of this user login.
	 *
	 * @return the longest time between logins of this user login
	 */
	@Override
	public long getLongestTimeBetweenLogins() {
		return _userLogin.getLongestTimeBetweenLogins();
	}

	/**
	 * Returns the primary key of this user login.
	 *
	 * @return the primary key of this user login
	 */
	@Override
	public String getPrimaryKey() {
		return _userLogin.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _userLogin.getPrimaryKeyObj();
	}

	/**
	 * Returns the screen name of this user login.
	 *
	 * @return the screen name of this user login
	 */
	@Override
	public String getScreenName() {
		return _userLogin.getScreenName();
	}

	/**
	 * Returns the shortest time between logins of this user login.
	 *
	 * @return the shortest time between logins of this user login
	 */
	@Override
	public long getShortestTimeBetweenLogins() {
		return _userLogin.getShortestTimeBetweenLogins();
	}

	/**
	 * Returns the system name of this user login.
	 *
	 * @return the system name of this user login
	 */
	@Override
	public String getSystemName() {
		return _userLogin.getSystemName();
	}

	/**
	 * Returns the total logins of this user login.
	 *
	 * @return the total logins of this user login
	 */
	@Override
	public long getTotalLogins() {
		return _userLogin.getTotalLogins();
	}

	/**
	 * Returns the uuid of this user login.
	 *
	 * @return the uuid of this user login
	 */
	@Override
	public String getUuid() {
		return _userLogin.getUuid();
	}

	@Override
	public int hashCode() {
		return _userLogin.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _userLogin.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _userLogin.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _userLogin.isNew();
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a user login model instance should use the <code>UserLogin</code> interface instead.
	 */
	@Override
	public void persist() {
		_userLogin.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_userLogin.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_userLogin.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_userLogin.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_userLogin.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the last login of this user login.
	 *
	 * @param lastLogin the last login of this user login
	 */
	@Override
	public void setLastLogin(Date lastLogin) {
		_userLogin.setLastLogin(lastLogin);
	}

	/**
	 * Sets the longest time between logins of this user login.
	 *
	 * @param longestTimeBetweenLogins the longest time between logins of this user login
	 */
	@Override
	public void setLongestTimeBetweenLogins(long longestTimeBetweenLogins) {
		_userLogin.setLongestTimeBetweenLogins(longestTimeBetweenLogins);
	}

	@Override
	public void setNew(boolean n) {
		_userLogin.setNew(n);
	}

	/**
	 * Sets the primary key of this user login.
	 *
	 * @param primaryKey the primary key of this user login
	 */
	@Override
	public void setPrimaryKey(String primaryKey) {
		_userLogin.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_userLogin.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the screen name of this user login.
	 *
	 * @param screenName the screen name of this user login
	 */
	@Override
	public void setScreenName(String screenName) {
		_userLogin.setScreenName(screenName);
	}

	/**
	 * Sets the shortest time between logins of this user login.
	 *
	 * @param shortestTimeBetweenLogins the shortest time between logins of this user login
	 */
	@Override
	public void setShortestTimeBetweenLogins(long shortestTimeBetweenLogins) {
		_userLogin.setShortestTimeBetweenLogins(shortestTimeBetweenLogins);
	}

	/**
	 * Sets the system name of this user login.
	 *
	 * @param systemName the system name of this user login
	 */
	@Override
	public void setSystemName(String systemName) {
		_userLogin.setSystemName(systemName);
	}

	/**
	 * Sets the total logins of this user login.
	 *
	 * @param totalLogins the total logins of this user login
	 */
	@Override
	public void setTotalLogins(long totalLogins) {
		_userLogin.setTotalLogins(totalLogins);
	}

	/**
	 * Sets the uuid of this user login.
	 *
	 * @param uuid the uuid of this user login
	 */
	@Override
	public void setUuid(String uuid) {
		_userLogin.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<UserLogin>
		toCacheModel() {

		return _userLogin.toCacheModel();
	}

	@Override
	public UserLogin toEscapedModel() {
		return new UserLoginWrapper(_userLogin.toEscapedModel());
	}

	@Override
	public String toString() {
		return _userLogin.toString();
	}

	@Override
	public UserLogin toUnescapedModel() {
		return new UserLoginWrapper(_userLogin.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _userLogin.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserLoginWrapper)) {
			return false;
		}

		UserLoginWrapper userLoginWrapper = (UserLoginWrapper)obj;

		if (Objects.equals(_userLogin, userLoginWrapper._userLogin)) {
			return true;
		}

		return false;
	}

	@Override
	public UserLogin getWrappedModel() {
		return _userLogin;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _userLogin.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _userLogin.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_userLogin.resetOriginalValues();
	}

	private final UserLogin _userLogin;

}