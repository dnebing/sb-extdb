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

package com.liferay.example.servicebuilder.extdb.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.example.servicebuilder.extdb.model.UserLogin;
import com.liferay.example.servicebuilder.extdb.service.base.UserLoginLocalServiceBaseImpl;
import com.liferay.portal.kernel.cal.Duration;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.commons.lang.time.StopWatch;

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
	private static final Log logger = LogFactoryUtil.getLog(UserLoginLocalServiceImpl.class);

    /**
     * updateUserLogin: Updates the user login record with the given info.
     * @param userId User who logged in.
     * @param loginDate Date when the user logged in.
     */
    public void updateUserLogin(final long userId, final Date loginDate) {
        UserLogin login = null;

        // first try to get the existing record for the user
        try {
            login = getUserLogin(userId);
        } catch (PortalException e) {
            logger.error("Error getting user login for user id " + userId, e);
        }

        if (login == null) {
            // user has never logged in before, need a new record
            if (logger.isDebugEnabled()) logger.debug("User " + userId + " has never logged in before.");

            // create a new record
            login = createUserLogin(userId);

            // update the login date
            login.setLastLogin(loginDate);

            // initialize the values
            login.setTotalLogins(1);
            login.setShortestTimeBetweenLogins(Long.MAX_VALUE);
            login.setLongestTimeBetweenLogins(0);

            // add the login
            addUserLogin(login);
        } else {
            // user has logged in before, just need to update record.
            if (logger.isDebugEnabled()) logger.debug("User " + userId + " has logged in before, updating the record.");

            // increment the logins count
            login.setTotalLogins(login.getTotalLogins() + 1);

            // determine the duration time between the current and last login
            long duration = loginDate.getTime() - login.getLastLogin().getTime();

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
            updateUserLogin(login);
        }
    }
}
