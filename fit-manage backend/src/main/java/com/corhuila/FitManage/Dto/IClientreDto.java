package com.corhuila.FitManage.Dto;

import jakarta.validation.constraints.NotNull;

public interface IClientreDto {
    Long getId();
    @NotNull
    String getEmail();
    String getPassword();
}
