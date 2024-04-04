package com.example.restapi.domain;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) //인자값을 받지않는 생성자 , 엑세스 권한을 내부에서만 갖도록
@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Auto Increment > 데이터가 추가 될 때마다, 자동으로 증가
    @Column(name = "id", updatable = false)//고유해야하니까 수정 못하도록
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
}
