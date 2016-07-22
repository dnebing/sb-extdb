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

import aQute.bnd.annotation.ProviderType;

import com.liferay.example.servicebuilder.extdb.model.UserLogin;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing UserLogin in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see UserLogin
 * @generated
 */
@ProviderType
public class UserLoginCacheModel implements CacheModel<UserLogin>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserLoginCacheModel)) {
			return false;
		}

		UserLoginCacheModel userLoginCacheModel = (UserLoginCacheModel)obj;

		if (userId == userLoginCacheModel.userId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, userId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{userId=");
		sb.append(userId);
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

		userLoginImpl.setUserId(userId);

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
		userId = objectInput.readLong();
		lastLogin = objectInput.readLong();

		totalLogins = objectInput.readLong();

		longestTimeBetweenLogins = objectInput.readLong();

		shortestTimeBetweenLogins = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(userId);
		objectOutput.writeLong(lastLogin);

		objectOutput.writeLong(totalLogins);

		objectOutput.writeLong(longestTimeBetweenLogins);

		objectOutput.writeLong(shortestTimeBetweenLogins);
	}

	public long userId;
	public long lastLogin;
	public long totalLogins;
	public long longestTimeBetweenLogins;
	public long shortestTimeBetweenLogins;
}