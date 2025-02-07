package com.emre.galleristapp.handlers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError<E> {
    //api error da herhangi bir tipte olabilir bu tipi exceptiona geçiririz
    private CustomException<E> exception;
    private Integer status;
}

