package com.github.supercoding.web.dto.items;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Spec {

    @ApiModelProperty(name="cpu", value="Item Cpu",example = "Google Tensor")
    private String cpu;

    @ApiModelProperty(name="capacity", value="Item 용량 Spec",example = "25GB")
    private String capacity;

}
