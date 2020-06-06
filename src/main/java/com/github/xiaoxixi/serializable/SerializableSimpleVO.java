package com.github.xiaoxixi.serializable;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SerializableSimpleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String userName;

    private String pwd;

    public String toString(){
        return "[" + userId + "]:[" + userName + "]:[" + pwd + "]";
    }
}
