package hellojpa;

import javax.persistence.Column;
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
    @Column(unique = true, length = 10) //컬럼 설정도 가능
    private int age;

    public Member(){ //기본생성자가 있어야 문제가 발생하지 않는다.

    }

    public Member(Long id, String name){
        this.id = id;
        this.name = name;
    }

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
