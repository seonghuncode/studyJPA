package hellojpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B") // -> 자식 클래스에서(기본값 : 엔티티명) 부모클래스에서 @DiscriminatorColumn에 들어가는 값을 설정
public class Book extends Item {
    private  String author;
    private String isbn;
}
