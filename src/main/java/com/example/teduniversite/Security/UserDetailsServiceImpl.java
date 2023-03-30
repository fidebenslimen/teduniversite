package com.example.teduniversite.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.teduniversite.repository.utilisateurrepository;
import com.example.teduniversite.entities.utilisateur;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    utilisateurrepository userrepo;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        utilisateur user = userrepo.findBy(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }


/*
    public List<GrantedAuthority> getAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRolename().name()));
        }
        return authorities;

    }*/
}
