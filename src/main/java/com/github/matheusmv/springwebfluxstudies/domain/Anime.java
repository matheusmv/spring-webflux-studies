package com.github.matheusmv.springwebfluxstudies.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@With
@Table("anime")
public class Anime {

    @Id
    private Long id;

    @NotNull
    @NotEmpty(message = "name cannot be empty")
    private String name;
}
