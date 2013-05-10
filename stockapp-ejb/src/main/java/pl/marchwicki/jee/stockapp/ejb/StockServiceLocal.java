package pl.marchwicki.jee.stockapp.ejb;

public interface StockServiceLocal {

	public void addStock(String symbol, Integer amount);
	
}
