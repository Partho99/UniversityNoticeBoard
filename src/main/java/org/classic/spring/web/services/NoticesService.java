package org.classic.spring.web.services;

import java.util.List;

import org.classic.spring.web.dao.Notice;
import org.classic.spring.web.dao.NoticesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service("noticesService")
public class NoticesService {

	private NoticesDao noticesDao;

	@Autowired
	public void setNoticesDao(NoticesDao noticesDao) {
		this.noticesDao = noticesDao;
	}

	public List<Notice> getCurrent() {
		return noticesDao.getNotices();
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public void create(Notice notice) {
		noticesDao.createNotice(notice);
	}

	public void throwTestException() {
//		throw new IllegalAccessError();

		noticesDao.getNotice(9999);
	}

}
