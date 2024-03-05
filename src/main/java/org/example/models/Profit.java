package org.example.models;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
public class Profit implements Serializable {
    @Getter
    private double money = 0;
}
