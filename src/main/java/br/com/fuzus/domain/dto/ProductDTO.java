package br.com.fuzus.domain.dto;

import java.math.BigDecimal;

public record ProductDTO(
    Long id,
    String description,
    BigDecimal price,
    Integer stock
) {}
