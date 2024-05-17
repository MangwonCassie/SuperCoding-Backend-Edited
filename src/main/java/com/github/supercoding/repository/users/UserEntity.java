package com.github.supercoding.repository.users;

import com.github.supercoding.repository.passenger.Passenger;
import lombok.*;
import org.hibernate.annotations.Table;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserEntity {

    @Id
    @Column(name = "user_id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "user_name", length = 20)
    private String userName;

    @Column(name = "like_travel_place", length = 30)
    private String likeTravelPlace;

    @Column(name = "phone_num", length = 30)
    private String phoneNum;

    @OneToOne(mappedBy = "user")
    private Passenger passenger;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserEntity)) {
            return false;
        }

        UserEntity that = (UserEntity) o;

        return userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }
}
