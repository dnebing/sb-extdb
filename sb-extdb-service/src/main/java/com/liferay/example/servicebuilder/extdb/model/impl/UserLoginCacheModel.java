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

package com.liferay.example.servicebuilder.extdb.model.impl;

import com.liferay.example.servicebuilder.extdb.model.UserLogin;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The cache model class for representing UserLogin in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class UserLoginCacheModel
	implements CacheModel<UserLogin>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserLoginCacheModel)) {
			return false;
		}

		UserLoginCacheModel userLoginCacheModel = (UserLoginCacheModel)obj;

		if (uuid.equals(userLoginCacheModel.uuid)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, uuid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", screenName=");
		sb.append(screenName);
		sb.append(", systemName=");
		sb.append(systemName);
		sb.append(", lastLogin=");
		sb.append(lastLogin);
		sb.append(", totalLogins=");
		sb.append(totalLogins);
		sb.append(", longestTimeBetweenLogins=");
		sb.append(longestTimeBetweenLogins);
		sb.append(", shortestTimeBetweenLogins=");
		sb.append(shortestTimeBetweenLogins);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UserLogin toEntityModel() {
		UserLoginImpl userLoginImpl = new UserLoginImpl();

		if (uuid == null) {
			userLoginImpl.setUuid("");
		}
		else {
			userLoginImpl.setUuid(uuid);
		}

		if (screenName == null) {
			userLoginImpl.setScreenName("");
		}
		else {
			userLoginImpl.setScreenName(screenName);
		}

		if (systemName == null) {
			userLoginImpl.setSystemName("");
		}
		else {
			userLoginImpl.setSystemName(systemName);
		}

		if (lastLogin == Long.MIN_VALUE) {
			userLoginImpl.setLastLogin(null);
		}
		else {
			userLoginImpl.setLastLogin(new Date(lastLogin));
		}

		userLoginImpl.setTotalLogins(totalLogins);
		userLoginImpl.setLongestTimeBetweenLogins(longestTimeBetweenLogins);
		userLoginImpl.setShortestTimeBetweenLogins(shortestTimeBetweenLogins);

		userLoginImpl.resetOriginalValues();

		return userLoginImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		screenName = objectInput.readUTF();
		systemName = objectInput.readUTF();
		lastLogin = objectInput.readLong();

		totalLogins = objectInput.readLong();

		longestTimeBetweenLogins = objectInput.readLong();

		shortestTimeBetweenLogins = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		if (screenName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(screenName);
		}

		if (systemName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(systemName);
		}

		objectOutput.writeLong(lastLogin);

		objectOutput.writeLong(totalLogins);

		objectOutput.writeLong(longestTimeBetweenLogins);

		objectOutput.writeLong(shortestTimeBetweenLogins);
	}

	public String uuid;
	public String screenName;
	public String systemName;
	public long lastLogin;
	public long totalLogins;
	public long longestTimeBetweenLogins;
	public long shortestTimeBetweenLogins;

}