package com.silkycoders1.jsystemssilkycodders1;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerLookupRequest(
	@NotBlank
	@Size(max = 80)
	String firstName,
	@NotBlank
	@Size(max = 80)
	String lastName,
	@NotBlank
	@Size(max = 40)
	String pesel
) {
}
