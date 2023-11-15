package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Member") // 만약 클래스 이름과 테이블 이름이 다르다면 해당 name에 테이블 이름을 작성해 맵핑 가능
public class Member {

    @Id //PK로 매핑
    @GeneratedValue()
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "TEAM_ID")
    private Long  teamId;


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

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
