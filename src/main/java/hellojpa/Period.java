package hellojpa;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable //값타입이라는 것을 알려주기 위한 어노테이션
public class Period {

    //기간으로 묶고 싶을 경우
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
