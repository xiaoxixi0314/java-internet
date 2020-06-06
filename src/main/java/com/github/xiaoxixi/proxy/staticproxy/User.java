package com.github.xiaoxixi.proxy.staticproxy;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class User {

    private Long userId;

    private String userName;

    private String sex;

}
