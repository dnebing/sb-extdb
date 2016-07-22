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

package com.liferay.example.servicebuilder.extdb.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.example.servicebuilder.extdb.exception.NoSuchUserLoginException;
import com.liferay.example.servicebuilder.extdb.model.UserLogin;
import com.liferay.example.servicebuilder.extdb.service.UserLoginLocalServiceUtil;
import com.liferay.example.servicebuilder.extdb.service.persistence.UserLoginPersistence;
import com.liferay.example.servicebuilder.extdb.service.persistence.UserLoginUtil;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.TransactionalTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.junit.runner.RunWith;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class UserLoginPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = UserLoginUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<UserLogin> iterator = _userLogins.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UserLogin userLogin = _persistence.create(pk);

		Assert.assertNotNull(userLogin);

		Assert.assertEquals(userLogin.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		UserLogin newUserLogin = addUserLogin();

		_persistence.remove(newUserLogin);

		UserLogin existingUserLogin = _persistence.fetchByPrimaryKey(newUserLogin.getPrimaryKey());

		Assert.assertNull(existingUserLogin);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addUserLogin();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UserLogin newUserLogin = _persistence.create(pk);

		newUserLogin.setLastLogin(RandomTestUtil.nextDate());

		newUserLogin.setTotalLogins(RandomTestUtil.nextLong());

		newUserLogin.setLongestTimeBetweenLogins(RandomTestUtil.nextLong());

		newUserLogin.setShortestTimeBetweenLogins(RandomTestUtil.nextLong());

		_userLogins.add(_persistence.update(newUserLogin));

		UserLogin existingUserLogin = _persistence.findByPrimaryKey(newUserLogin.getPrimaryKey());

		Assert.assertEquals(existingUserLogin.getUserId(),
			newUserLogin.getUserId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingUserLogin.getLastLogin()),
			Time.getShortTimestamp(newUserLogin.getLastLogin()));
		Assert.assertEquals(existingUserLogin.getTotalLogins(),
			newUserLogin.getTotalLogins());
		Assert.assertEquals(existingUserLogin.getLongestTimeBetweenLogins(),
			newUserLogin.getLongestTimeBetweenLogins());
		Assert.assertEquals(existingUserLogin.getShortestTimeBetweenLogins(),
			newUserLogin.getShortestTimeBetweenLogins());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		UserLogin newUserLogin = addUserLogin();

		UserLogin existingUserLogin = _persistence.findByPrimaryKey(newUserLogin.getPrimaryKey());

		Assert.assertEquals(existingUserLogin, newUserLogin);
	}

	@Test(expected = NoSuchUserLoginException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<UserLogin> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("ExtDB_UserLogin", "userId",
			true, "lastLogin", true, "totalLogins", true,
			"longestTimeBetweenLogins", true, "shortestTimeBetweenLogins", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		UserLogin newUserLogin = addUserLogin();

		UserLogin existingUserLogin = _persistence.fetchByPrimaryKey(newUserLogin.getPrimaryKey());

		Assert.assertEquals(existingUserLogin, newUserLogin);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UserLogin missingUserLogin = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingUserLogin);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		UserLogin newUserLogin1 = addUserLogin();
		UserLogin newUserLogin2 = addUserLogin();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUserLogin1.getPrimaryKey());
		primaryKeys.add(newUserLogin2.getPrimaryKey());

		Map<Serializable, UserLogin> userLogins = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, userLogins.size());
		Assert.assertEquals(newUserLogin1,
			userLogins.get(newUserLogin1.getPrimaryKey()));
		Assert.assertEquals(newUserLogin2,
			userLogins.get(newUserLogin2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, UserLogin> userLogins = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(userLogins.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		UserLogin newUserLogin = addUserLogin();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUserLogin.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, UserLogin> userLogins = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, userLogins.size());
		Assert.assertEquals(newUserLogin,
			userLogins.get(newUserLogin.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, UserLogin> userLogins = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(userLogins.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		UserLogin newUserLogin = addUserLogin();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUserLogin.getPrimaryKey());

		Map<Serializable, UserLogin> userLogins = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, userLogins.size());
		Assert.assertEquals(newUserLogin,
			userLogins.get(newUserLogin.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = UserLoginLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<UserLogin>() {
				@Override
				public void performAction(UserLogin userLogin) {
					Assert.assertNotNull(userLogin);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		UserLogin newUserLogin = addUserLogin();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UserLogin.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("userId",
				newUserLogin.getUserId()));

		List<UserLogin> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		UserLogin existingUserLogin = result.get(0);

		Assert.assertEquals(existingUserLogin, newUserLogin);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UserLogin.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("userId",
				RandomTestUtil.nextLong()));

		List<UserLogin> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		UserLogin newUserLogin = addUserLogin();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UserLogin.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("userId"));

		Object newUserId = newUserLogin.getUserId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("userId",
				new Object[] { newUserId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingUserId = result.get(0);

		Assert.assertEquals(existingUserId, newUserId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UserLogin.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("userId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("userId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected UserLogin addUserLogin() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UserLogin userLogin = _persistence.create(pk);

		userLogin.setLastLogin(RandomTestUtil.nextDate());

		userLogin.setTotalLogins(RandomTestUtil.nextLong());

		userLogin.setLongestTimeBetweenLogins(RandomTestUtil.nextLong());

		userLogin.setShortestTimeBetweenLogins(RandomTestUtil.nextLong());

		_userLogins.add(_persistence.update(userLogin));

		return userLogin;
	}

	private List<UserLogin> _userLogins = new ArrayList<UserLogin>();
	private UserLoginPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}