package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Member") // 만약 클래스 이름과 테이블 이름이 다르다면 해당 name에 테이블 이름을 작성해 맵핑 가능
public class Member extends  BaseEntity{

    @Id //PK로 매핑
    @GeneratedValue()
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

//    public Locker getLocker() {
//        return locker;
//    }
//
//    public void setLocker(Locker locker) {
//        this.locker = locker;
//    }

//    public List<MemberProduct> getMemberProduct() {
//        return memberProduct;
//    }
//
//    public void setMemberProduct(List<MemberProduct> memberProduct) {
//        this.memberProduct = memberProduct;
//    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Team team;

//    @OneToOne
//    @JoinColumn(name = "LOCKER_ID") //주인
//    private Locker locker;
//
//    @OneToMany(mappedBy = "member")
//    private List<MemberProduct> memberProduct = new ArrayList<>();

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

}
