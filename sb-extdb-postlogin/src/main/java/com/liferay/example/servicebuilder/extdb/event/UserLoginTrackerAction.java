package com.liferay.example.servicebuilder.extdb.event;

import com.liferay.example.servicebuilder.extdb.service.UserLoginLocalService;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.Date;

import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * class UserLoginTrackerAction: This is the post login hook to track user logins.
 *
 * @author dnebinger
 * @author Antonio Musarra
 */
@Component(
	immediate = true, property = "key=login.events.post",
	service = LifecycleAction.class
)
public class UserLoginTrackerAction implements LifecycleAction {

	/**
	 * processLifecycleEvent: Invoked when the registered event is triggered.
	 *
	 * @param lifecycleEvent
	 * @throws ActionException
	 */
	@Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent)
		throws ActionException {

		// okay, we need the user login for the event

		User user = null;

		try {
			user = _portal.getUser(lifecycleEvent.getRequest());
		}
		catch (PortalException pe) {
			if (_log.isErrorEnabled()) {
				_log.error(
					"Error accessing login user: " + pe.getMessage(), pe);
			}
		}

		if (Validator.isNull(user)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Could not find the logged in user, nothing to track.");

			}

			return;
		}

		if (_log.isDebugEnabled()) {
			_log.debug("User [" + user.getScreenName() + "] has logged in.");
		}

		// we have the user, let's invoke the service
		_userLoginLocalService.updateUserLogin(
			user.getUserUuid(), new Date(), user.getScreenName(),
			user.getLoginIP());
	}

	@Reference(policyOption = ReferencePolicyOption.GREEDY)
	private UserLoginLocalService _userLoginLocalService;

	@Reference
	private Portal _portal;

	private static final Log _log = LogFactoryUtil.getLog(
		UserLoginTrackerAction.class);
}