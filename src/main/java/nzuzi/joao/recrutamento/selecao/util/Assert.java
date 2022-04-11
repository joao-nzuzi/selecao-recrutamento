package nzuzi.joao.recrutamento.selecao.util;

import java.math.BigDecimal;
import java.util.List;


public class Assert {

	public static boolean isNotNull(final Object... objects) {
		boolean isNotNull = true;
		if (objects != null) {
			for (Object o : objects) {
				if (o == null) {
					isNotNull = false;
					break;
				}
			}
		} else isNotNull = false;
		return isNotNull;
	}


	public static boolean isNull(final Object... objects){
		boolean isnull = true;
		if (objects == null) {
			return isnull;
		}
		for (Object o : objects) {
			if (o != null) {
				isnull = false;
				break;
			}
		}
		return isnull;
	}

	public static boolean isNull(final String... strings){
		boolean isNullOrEmpty = true;
		if (strings == null) {
			return isNullOrEmpty;
		}
		for (String o : strings) {
			if (o == null) {
				continue;
			} else if (!o.isEmpty() || !o.equals("null") || !o.trim().isEmpty()) {
				isNullOrEmpty = false;
				break;
			}
		}
		return isNullOrEmpty;
	}

	public static boolean isNotNullOrEmpty(final String param){
		return param != null && !param.isEmpty();
	}

	public static boolean isNullOrEmpty(final String param){
		return param == null || param.isEmpty();
	}

	public static boolean isEmpty(final String param){
		return param.isEmpty();
	}

	public static boolean isNotEmpty(final BigDecimal param){
		return param.compareTo(BigDecimal.ZERO) != 0;
	}

	public static boolean isNotEqual(final Double value1, final Double value2){
		return !(value1.equals(value2));
	}

	public static boolean isEmpty(final List<?> param){
		return param == null || param.isEmpty();
	}

	public static boolean isNotNullOrEmpty(final List<?> param){
		return param == null || param.isEmpty();
	}

	public static boolean isNotNullAndNotEmpty(final List<?> param){
		return param != null && !param.isEmpty();
	}

	public static boolean isNotNullAndNotEmpty(String param){
		return param != null && !param.trim ().isEmpty();
	}

	public static boolean isNullOrEmpty(final List<?> param){
		return isEmpty(param);
	}

	public static boolean isNotEmpty(String param) {
		return !param.isEmpty();
	}

	public static boolean isNotNullOrEmptyOrNA(final String param){
		return param != null && !param.isEmpty() && !param.equals("NA") && !param.matches("NA\\d*");
	}

}
