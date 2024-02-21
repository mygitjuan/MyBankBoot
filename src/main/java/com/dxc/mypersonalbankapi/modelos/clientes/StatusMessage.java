package com.dxc.mypersonalbankapi.modelos.clientes;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StatusMessage {
    private Integer status;
    private String message;

}