package com.liferay.example.servicebuilder.extdb.event;

import com.liferay.example.servicebuilder.extdb.service.UserLoginLocalService;
import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.osgi.util.tracker.ServiceTracker;

/**
 * class UserLoginTrackerAction: This is the post login hook to track user logins.
 *
 * @author dnebinger
 */
@Component(
	immediate = true, property = "key=login.events.post",
	service = LifecycleAction.class
)
public class UserLoginTrackerAction implements LifecycleAction {

	/**
	 * getService: Returns the user tracker service instance.
	 * @return UserLoginLocalService The instance to use.
	 */
	public UserLoginLocalService getService() {
		return _serviceTracker.getService();
	}

	/**
	 * processLifecycleEvent: Invoked when the registered event is triggered.
	 * @param lifecycleEvent
	 * @throws ActionException
	 */
	@Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent)
		throws ActionException {

		// okay, we need the user login for the event

		User user = null;

		try {
			user = PortalUtil.getUser(lifecycleEvent.getRequest());
		}
		catch (PortalException pe) {
			logger.error("Error accessing login user: " + pe.getMessage(), pe);
		}

		if (user == null) {
			logger.warn("Could not find the logged in user, nothing to track.");

			return;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("User [" + user.getScreenName() + "] has logged in.");
		}

		// we have the user, let's invoke the service

		getService().updateUserLogin(user.getUserId(), new Date());

		// alternatively we could just use the local service util:
		// UserLoginLocalServiceUtil.updateUserLogin(user.getUserId(), new Date());

	}

	private static final Log logger = LogFactoryUtil.getLog(
		UserLoginTrackerAction.class);

	// use the OSGi service tracker to get an instance of the service when available.

	private ServiceTracker<UserLoginLocalService, UserLoginLocalService>
		_serviceTracker = ServiceTrackerFactory.open(
			UserLoginLocalService.class);

}