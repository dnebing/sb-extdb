/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 * <p>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.example.servicebuilder.extdb.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.example.servicebuilder.extdb.model.UserLogin;
import com.liferay.example.servicebuilder.extdb.service.base.UserLoginLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;

/**
 * The implementation of the user login local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.example.servicebuilder.extdb.service.UserLoginLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserLoginLocalServiceBaseImpl
 * @see com.liferay.example.servicebuilder.extdb.service.UserLoginLocalServiceUtil
 */
@ProviderType
public class UserLoginLocalServiceImpl extends UserLoginLocalServiceBaseImpl {

	/**
	 * updateUserLogin: Updates the user login record with the given info.
	 *
	 * @param userUUID  User who logged in.
	 * @param loginDate Date when the user logged in.
	 * @param screenName Screen Name of the user
	 * @param systemName The system name (es. Hostname or FQDN, or VirtualHost)
	 */
	public void updateUserLogin(
		final String userUUID, final Date loginDate, final String screenName,
		final String systemName) {
		UserLogin login = null;

		// first try to get the existing record for the user

		try {
			login = getUserLogin(userUUID);
		}
		catch (PortalException pe) {
			if (_log.isErrorEnabled()) {
				_log.error(
					"Error getting user login for user id " + userUUID, pe);
			}
		}

		if (Validator.isNull(login)) {

			// user has never logged in before, need a new record

			if (_log.isDebugEnabled()) {
				_log.debug("User " + userUUID + " has never logged in before.");
			}

			// create a new record

			login = createUserLogin(userUUID);

			// update the login date

			login.setLastLogin(loginDate);

			// initialize the values

			login.setTotalLogins(1);
			login.setShortestTimeBetweenLogins(Long.MAX_VALUE);
			login.setLongestTimeBetweenLogins(0);
			login.setScreenName(screenName);
			login.setSystemName(systemName);

			// add the login

			addUserLogin(login);
		}
		else {

			// user has logged in before, just need to update record.

			if (_log.isDebugEnabled()) {
				_log.debug(
					"User " + userUUID +
					" has logged in before, updating the record.");
			}

			// increment the logins count

			login.setTotalLogins(login.getTotalLogins() + 1);

			// determine the duration time between the current and last login

			long duration =
				loginDate.getTime() -
				login.getLastLogin(
				).getTime();

			// if this duration is longer than last, update the longest duration.

			if (duration > login.getLongestTimeBetweenLogins()) {
				login.setLongestTimeBetweenLogins(duration);
			}

			// if this duration is shorter than last, update the shortest duration.

			if (duration < login.getShortestTimeBetweenLogins()) {
				login.setShortestTimeBetweenLogins(duration);
			}

			// update the last login timestamp

			login.setLastLogin(loginDate);

			// update the record

			login.setScreenName(screenName);
			login.setSystemName(systemName);

			updateUserLogin(login);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UserLoginLocalServiceImpl.class);

}