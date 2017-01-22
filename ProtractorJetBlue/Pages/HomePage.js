//var logger = require('./Util/log.js');
var jsonData = require('../json/Objects.json');

var HomePage = function() {

	this.loginAsUser = function() {

		element(by.id(jsonData.locators.loginpage.username)).sendKeys(
				jsonData.userdetails.email);

		element(by.id(jsonData.locators.loginpage.password)).sendKeys(
				jsonData.userdetails.passcode);

		element(by.id(jsonData.locators.loginpage.SignIn)).click();

	};

	this.flightSelection = function() {

		element(by.model('tempParam')).clear();
		element(by.model('tempParam')).sendKeys('LGA');
		element(by.id('jbBookerArrive')).clear();
		element(by.id('jbBookerArrive')).sendKeys('DFW');
		element(by.model('departCal.date')).sendKeys('01/20/2017');
		element(by.model('returnCal.date')).sendKeys('01/25/2017');
		element(
				by
						.xpath('/html/body/div[5]/div[1]/div[3]/div/div[3]/form/input[5]'))
				.click();
	};

};
module.exports = new HomePage();
