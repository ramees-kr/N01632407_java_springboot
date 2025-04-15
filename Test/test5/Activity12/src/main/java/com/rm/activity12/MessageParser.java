package com.rm.activity12;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class MessageParser implements Serializable {
    private String name;
    private String message;

}
