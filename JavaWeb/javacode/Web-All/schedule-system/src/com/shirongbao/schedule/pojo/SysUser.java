package com.shirongbao.schedule.pojo;

import lombok.*;

import java.io.Serializable;

//@EqualsAndHashCode
//@Getter
//@Setter
//@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data  // getter setter equals hashCode toString
public class SysUser implements Serializable {
    private Integer uid;
    private String username;
    private String userPwd;
}
