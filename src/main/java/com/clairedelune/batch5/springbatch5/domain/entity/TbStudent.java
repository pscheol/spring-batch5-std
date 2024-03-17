package com.clairedelune.batch5.springbatch5.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tb_student")
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TbStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

}
