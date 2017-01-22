var excelData = require('./Util/ExcelTest.js');
var jsonData = require('./json/Objects.json');
//var using = require('jasmine-data-provider');
var logger = require('./Util/log');
var home = require('./Pages/HomePage.js');
var base = require('./Pages/BasePage.js');
// var price = require('./Pages/PriceSelectionPage.js');
describe('angularjs homepage', function() {
	it('should greet the named user', function() {
		// var base = require('./Pages/BasePage.js');
		//base.navigateToURL('http://www.jetblue.com/#/');
		base.navigateToURL(jsonData.testsiteurl);
		//home.loginAsUser();
	//	home.flightSelection();
		// price.PriceSelectionPage();
		var excel = require('./Util/ExcelTesT.js');
		logger.log('info', 'first log message');
		
	});
});
