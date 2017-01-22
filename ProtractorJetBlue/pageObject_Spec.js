var callExcel = require('./Util/ExcelTest.js');
var jsonData = require('./json/Objects.json');
var logger = require('./Util/log');
var home = require('./Pages/HomePage.js');
var base = require('./Pages/BasePage.js');
var price = require('./Pages/PriceSelectPage.js');
describe(
		'Jetblue homepage',
		function() {
			it(
					'user will be able to login successfully and user will be able to select departing, arrival airport',
					function() {
						base.navigateToURL(jsonData.testsiteurl);
						logger.log('info', 'Browser opened successfully');
						home.loginAsUser();
						logger.log('info',
								'User entered credentials successfully');
						home.flightSelection();

						// price.priceSelection();
						logger.log('info', 'first log message');
						console.log("Hello Protractor");
					});
		});
