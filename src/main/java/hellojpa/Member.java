package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
//@Table(name="MBR") 테이블명 다를때
@TableGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
/*
@SequenceGenerator(
        name="MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",    // 매칭할 데이터베이스 시퀀스 이름
        initialValue = 1, allocationSize = 1)
 */
public class Member{

    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name="USERNAME")
    private String name;

    @Embedded
    private Period workPeriod;

    @Embedded
    private Address homeAddress;


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
}
