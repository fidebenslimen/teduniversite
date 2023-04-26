package com.example.teduniversite.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;

@Data
@Entity
@DiscriminatorColumn(name="utilisateur_type")
public class utilisateur implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Getter
       @Setter
        private Long userid;
    @Size(max = 20)
    private String username;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @Size(max = 20)
    private String firstname;
    @Size(max = 20)
    private String lastname;

    private int cin;

    private String etatUser;


    @NotBlank
    @Size(max = 8)
    @Size(min = 8)
    @NumberFormat
    private String phoneNumber;

    @Temporal(value=TemporalType.DATE)
    @DateTimeFormat(pattern = "yyy-MM-dd")

    private Date dob;
    @Size(max = 120)
    private String password;
    @JsonIgnore

    private int failedLoginAttempts;


    @JsonIgnore
    private int payment_status;

    @JsonIgnore
    private LocalDateTime creationDate;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))

    private Set<Role> roles = new HashSet<>();


    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private utilisateur_bloqué ban;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

    public utilisateur(Long userid, String username, String email, String firstname, String lastname, int cin, String phoneNumber, Date dob, String password) {
        this.userid = userid;
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.cin = cin;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.password = password;
    }
    public utilisateur(){}

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", cin=" + cin +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dob=" + dob +
                ", password='" + password+ '\'' +
                ", creationDate=" + creationDate +

                '}';
    }


}
