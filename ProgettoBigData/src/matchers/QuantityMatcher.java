package matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import resources.Point;
import resources.Product;

public class QuantityMatcher extends BaseMatcher<Object>{
	
	private String productName;
	private Integer quantity;

	public QuantityMatcher(String productName, Integer quantity) {
		this.productName = productName;
		this.quantity = quantity;
	}
	
	@Override
	public boolean matches(Object object) {
		Point p = (Point) object;
		Product product = p.getProduct(this.productName);
		return product != null && product.getQuantity() >= quantity;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("Match only products \"")
				   .appendValue(this.productName)
				   .appendText("\" with quantity more or equal than ")
				   .appendValue(this.quantity);
	}

}
