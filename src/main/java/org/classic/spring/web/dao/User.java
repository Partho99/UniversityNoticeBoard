package org.classic.spring.web.dao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.classic.spring.web.validation.ValidEmail;

public class User {

	@NotBlank(message = "username must not be blank")
	@Size(min = 5, max = 15)
	@Pattern(regexp = "^\\w{5,}$", message = "username can only consist of numbers, letters and underscore")
	private String username;

	@NotBlank(message = "password must not be blank")
	@Pattern(regexp = "^\\S+$", message = "password cannot contain spaces")
	private String password;

	@ValidEmail(message = "use valid email only")
	private String email;
	private boolean enabled = false;
	private String authority;

	public User() {
		super();
	}

	public User(String username, String password, String email, boolean enabled, String authority) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
		this.authority = authority;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
