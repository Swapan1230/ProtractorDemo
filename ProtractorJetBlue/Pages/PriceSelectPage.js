var logger = require('../Util/log');
var PriceSelectPage = function() {
	this.priceSelection = function() {

		element(by.id('flightSelectGr_0_3')).click();
		element(by.id('itineraryPriceCell_1_22')).click();

		//var totalPrice = element(by.css('#total')).getText();
		//expect(totalPrice).toBe('$429.20 usd');
	//	logger.log('info', 'totalPrice' + totalPrice);
		element(by.id('pgButtonNext')).click();

	};
};

module.exports = new PriceSelectPage();