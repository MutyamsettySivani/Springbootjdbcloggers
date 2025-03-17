package com.springboot.jdbc.Entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Library {

    private int bookId;
    private String bookName;
    private String bookCategory;
}
