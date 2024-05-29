package com.SIS.SPM.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AES128 extends Password {
    private String secretKey;

    private boolean validKey(String sk){
        if( sk.length() ==16) return true;
        else return false;
    }
}
