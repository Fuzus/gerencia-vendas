package br.com.fuzus.domain.dto;

public record OrderDTO(Long id, Long clientId, OrderProductDTO orderProduct) {
}
