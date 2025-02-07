package com.emre.galleristapp.handlers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

//Exception mesajı herhangi bir tipte olabilir
//String olabilir veya Key ,Value çifti olabilir
public class CustomException<E> {
    private String path;
    private String hostName;
    private Date createdTime;
    private E message;
}
