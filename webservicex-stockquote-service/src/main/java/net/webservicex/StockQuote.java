package net.webservicex;

import javax.ejb.Stateless;
import javax.jws.WebService;

import net.webservicex.StockQuoteSoap;

@Stateless
@WebService(serviceName="StockQuote",  targetNamespace="http://www.webserviceX.NET/",
		wsdlLocation="stockquote.wsdl")
public class StockQuote implements StockQuoteSoap {

	public String getQuote(String symbol) {
		System.out.println("Returning quote for [symbol="+symbol+"]");
		if ("YHOO".equals(symbol)) {
			return "<StockQuotes><Stock><Symbol>YHOO</Symbol><Last>15.19</Last><Date>5/11/2012</Date><Time>4:00pm</Time><Change>-0.25</Change><Open>14.90</Open><High>15.44</High><Low>14.80</Low><Volume>21134710</Volume><MktCap>18.630B</MktCap><PreviousClose>15.44</PreviousClose><PercentageChange>-1.62%</PercentageChange><AnnRange>11.09 - 18.84</AnnRange><Earns>0.877</Earns><P-E>17.61</P-E><Name>Yahoo! Inc.</Name></Stock></StockQuotes>";
		} else if ("IBM".equals(symbol)) {
			return "<StockQuotes><Stock><Symbol>IBM</Symbol><Last>201.17</Last><Date>5/11/2012</Date><Time>4:01pm</Time><Change>+0.57</Change><Open>199.96</Open><High>202.12</High><Low>199.95</Low><Volume>2391580</Volume><MktCap>232.0B</MktCap><PreviousClose>200.60</PreviousClose><PercentageChange>+0.28%</PercentageChange><AnnRange>157.13 - 210.69</AnnRange><Earns>13.407</Earns><P-E>14.96</P-E><Name>International Bus</Name></Stock></StockQuotes>";
		} else if ("GOOG".equals(symbol)) {
			return "<StockQuotes><Stock><Symbol>GOOG</Symbol><Last>605.23</Last><Date>5/11/2012</Date><Time>4:00pm</Time><Change>-8.43</Change><Open>609.48</Open><High>614.55</High><Low>604.77</Low><Volume>2099726</Volume><MktCap>197.3B</MktCap><PreviousClose>613.66</PreviousClose><PercentageChange>-1.37%</PercentageChange><AnnRange>473.02 - 670.25</AnnRange><Earns>32.998</Earns><P-E>18.60</P-E><Name>Google Inc.</Name></Stock></StockQuotes>";
		} else {
			return "<StockQuotes><Stock><Symbol>ATT</Symbol><Last>0.00</Last><Date>N/A</Date><Time>N/A</Time><Change>N/A</Change><Open>N/A</Open><High>N/A</High><Low>N/A</Low><Volume>N/A</Volume><MktCap>N/A</MktCap><PreviousClose>N/A</PreviousClose><PercentageChange>N/A</PercentageChange><AnnRange>N/A - N/A</AnnRange><Earns>N/A</Earns><P-E>N/A</P-E><Name>ATT</Name></Stock></StockQuotes>";
		}
	}

}
