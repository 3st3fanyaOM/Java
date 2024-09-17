package modelo;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BultoInflamable extends Bulto {

	public BultoInflamable(String descripcion, BigDecimal peso) {
		super(descripcion, peso);
		setVoluminoso(false);
	}

	@Override
	public BigDecimal getSobreCosteDiario() {
		if (getVoluminoso() == true) {
			return new BigDecimal(60).setScale(2, RoundingMode.HALF_UP);
		}
		return new BigDecimal(30).setScale(2, RoundingMode.HALF_UP);
	}

}
