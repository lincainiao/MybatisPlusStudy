package com.lin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.awt.print.PrinterGraphics;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class User {
    private long id;
    private String name;
    private long age;
    private String email;
}
