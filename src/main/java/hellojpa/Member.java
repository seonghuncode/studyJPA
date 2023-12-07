package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "Member") // 만약 클래스 이름과 테이블 이름이 다르다면 해당 name에 테이블 이름을 작성해 맵핑 가능
public class Member {

    @Id //PK로 매핑
    @GeneratedValue()
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    //기간으로 묶고 싶을 경우
//    private LocalDateTime startDate;
//    private LocalDateTime endDate;

    // -> 기간으로 묶고 싶을 경우 이렇게 변경
    @Embedded
    private Period workPeriod;

//    //주소로 묶고 싶을 경우
//    private String city;
//    private String street;
//    private String zipcode;

    // -> 주소로 묶고 싶을 경우 이렇게 변경
    @Embedded
    private Address homeAddress;

    @ElementCollection //매핑하는 어노테이션
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns =
        @JoinColumn(name = "MEMBER_ID")
    )
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }



//    //값 타입 매핑 방법
//    @ElementCollection //매핑하는 어노테이션
//    @CollectionTable(name = "ADDRESS", joinColumns =
//        @JoinColumn(name = "MEMBER_ID") //외래키로 잡는다.
//    )
//    private List<Address> addressHistory = new ArrayList<>();

    //엔티티 매핑 방법 (값타입을 사용할 경우 복작하기 때문에 엔티티 매핑을 고려)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID") //1대다 단방향 매핑
    private List<AddressEntity> addressHistory = new ArrayList<>();


    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public List<AddressEntity> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<AddressEntity> addressHistory) {
        this.addressHistory = addressHistory;
    }
}
