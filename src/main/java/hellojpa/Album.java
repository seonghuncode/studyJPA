package hellojpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A") // -> 자식 클래스에서(기본값 : 엔티티명) 부모클래스에서 @DiscriminatorColumn에 들어가는 값을 설정
public class Album extends Item{
    private String artist;
}
