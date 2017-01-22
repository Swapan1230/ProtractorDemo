var logger = require('../Util/log');
var BasePage = function() {

	this.navigateToURL = function(url) {
		
		browser.get(url);

		console.log(url);
		logger.log('info', 'application url = ' + url);

	};

	this.getPageTitle = function() {
		return browser.getTitle();

	}

};
module.exports = new BasePage();