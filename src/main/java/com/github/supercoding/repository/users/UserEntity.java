package com.github.supercoding.repository.users;

import com.github.supercoding.repository.passenger.Passenger;
import lombok.Builder;
import org.hibernate.annotations.Table;

import javax.persistence.*;

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



    public UserEntity() {

    }

    public UserEntity(Integer userId, String userName, String likeTravelPlace, String phoneNum) {
        this.userId = userId;
        this.userName = userName;
        this.likeTravelPlace = likeTravelPlace;
        this.phoneNum = phoneNum;
    }



    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLikeTravelPlace() {
        return likeTravelPlace;
    }

    public void setLikeTravelPlace(String likeTravelPlace) {
        this.likeTravelPlace = likeTravelPlace;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

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
