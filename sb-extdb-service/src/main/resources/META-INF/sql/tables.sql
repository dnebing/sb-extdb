create table ExtDB_UserLogin (
	userId LONG not null primary key,
	lastLogin DATE null,
	totalLogins LONG,
	longestTimeBetweenLogins LONG,
	shortestTimeBetweenLogins LONG
);