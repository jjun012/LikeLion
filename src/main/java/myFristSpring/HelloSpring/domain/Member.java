package myFristSpring.HelloSpring.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor
@Entity
public class Member {
    @Setter
    @Id @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String userId;
    private String password;
    @Setter
    private String nickname;

    public Member(String userId, String password) {
        this.userId = userId;
        this.setPassword(password);
    }

    public void setPassword(String password) {
        this.password = passwordEncoder(password);
    }
    private static final PasswordEncoder passwordEncoder =new BCryptPasswordEncoder();

    public String passwordEncoder(String password) {
        return passwordEncoder.encode(password);
    }
    public boolean checkPassword(String rawPassword) {
        return passwordEncoder.matches(rawPassword,this.password);
    }
}
