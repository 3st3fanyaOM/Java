package modelo;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BultoCorrosivo extends Bulto {

	public BultoCorrosivo(String descripcion, BigDecimal peso) {
		super(descripcion, peso);
		setVoluminoso(false);
	}

	@Override
	public BigDecimal getSobreCosteDiario() {
		if (getVoluminoso() == true) {
			return new BigDecimal(40).setScale(2, RoundingMode.HALF_UP);
		}
		return new BigDecimal(20).setScale(2, RoundingMode.HALF_UP);
	}

}
