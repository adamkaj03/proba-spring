package com.adam.buzas.onlab.main.model;

import com.adam.buzas.onlab.main.dto.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "felhasznalo")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    /**
     * Lombok segítségével a felhasználóhoz tartozó összes adatnak van getter és settere
     * A security rész miatt kell a UserDetails osztályt megvalósítani
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nev")
    private String name;

    @Column(name="felhasznalo_nev")
    private String username;

    private String email;

    @Column(name="jelszo")
    private String password;

    @Column(name = "szerep")
    @Enumerated(EnumType.STRING)
    private Role role;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
