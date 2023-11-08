package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Member") // 만약 클래스 이름과 테이블 이름이 다르다면 해당 name에 테이블 이름을 작성해 맵핑 가능
public class Member {

    //PK값을 알려줘야 한다
    @Id
    //DB에 있는 컬럼 작성
    private Long id;
    //@Column(name = "name) -> 실제 테이블에 있는 컬럼 이름이랑 변수명이 다를 경우 맵핑 할때 사용
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
