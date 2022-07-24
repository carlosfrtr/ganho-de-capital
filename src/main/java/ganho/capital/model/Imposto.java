package ganho.capital.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Imposto {

	private BigDecimal tax;
	
	public Imposto(BigDecimal tax) {
		this.tax = tax.setScale(2, RoundingMode.HALF_UP);
	}
	
	public Imposto() {
		this.tax = BigDecimal.ZERO.setScale(2);
	}
	
	public BigDecimal getTax() {
		return tax;
	}
}
