package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Member") // 만약 클래스 이름과 테이블 이름이 다르다면 해당 name에 테이블 이름을 작성해 맵핑 가능
public class Member {

    @Id //PK로 매핑
    private Long id;
    //객체는 username을 사용 DB는 name으로 사용할 경우,  insertable = true, : 컬럼을 수정했을 경우 DB에 insert를 할것인지 말것인지 결정(기본true)
    @Column(name = "name", insertable = true, updatable = true)
    private String username;
    private Integer age;
    @Enumerated(EnumType.STRING) //자바의 enum타입을 DB에서 사용하고 싶을 경우
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;  //최신버전에서는 private LocalDate testLocalDate; 이런식으로 사용이 가능하다.
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Lob
    private String description;

    public Member(){ //기본생성자가 있어야 문제가 발생하지 않는다.

    }

    public Member(Long id, String name){
    }



}
