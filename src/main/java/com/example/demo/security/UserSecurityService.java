package com.example.demo.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.User_infoDAO;
import com.example.demo.entity.User_info;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {

	private final User_infoDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User_info> _userinfo = userDAO.findById(username);
		if(_userinfo == null || _userinfo.isEmpty()) {
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
		}

		User_info user_info = _userinfo.get();
		List<GrantedAuthority> authorities = new ArrayList<>();

		if("admin".equals(username)) {
			authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
		}else {
			authorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
		}
				
		return new User(user_info.getId(), user_info.getPwd(), authorities);
	}
}
