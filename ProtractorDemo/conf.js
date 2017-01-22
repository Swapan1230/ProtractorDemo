// configuration file.
//var XLSX = require('./ExcelTesT.js');
exports.config = {
	directConnect : true,
	// seleniumAddress : 'http://localhost:4444/wd/hub',
	// Capabilities to be passed to the webdriver instance.

	// seleniumArgs:
	// ['-Dwebdriver.ie.driver=C:\Users\sbhowmik\AppData\Roaming\npm\node_modules\protractor\node_modules\webdriver-manager\selenium/IEDriverServer.exe']
	// 'browserName': 'internet explorer'
	capabilities : {
		'browserName' : 'chrome'

	// 'platform' : 'ANY',
	// 'version' : '11'
	},
	// Framework to use. Jasmine is recommended.
	framework : 'jasmine2',

	// Spec patterns are relative to the current working directory when
	// protractor is called.
	specs : [ 'pageObject_Spec.js' ],

	// Options to be passed to Jasmine.
	jasmineNodeOpts : {
		defaultTimeoutInterval : 60000
	},
	onPrepare : function() {

		 browser.ignoreSynchronization = true;

		// Override the timeout for webdriver.
		browser.driver.manage().timeouts().implicitlyWait(10000);
		// Add a screenshot reporter and store screenshots to `/Reports`:
		var HtmlScreenshotReporter = require('protractor-jasmine2-screenshot-reporter');
		jasmine.getEnv().addReporter(new HtmlScreenshotReporter({
			dest : './Reports',
			filename : 'Results.html'
		}));

	},

	onComplete : function() {
		console.log("Sending Mail with reports for the test execution.");
		var sys = require('util')
		var exec = require('child_process').exec;
		function puts(error, stdout, stderr) {
			sys.puts(stdout)
		}
		exec("node mail.js", puts);
	},

/*
 * onPrepare: function() { var jasmineReporters = require('jasmine-reporters'); //
 * returning the promise makes protractor wait for the reporter config before
 * executing tests return browser.getProcessedConfig().then(function(config) { //
 * you could use other properties here if you want, such as platform and version
 * var browserName = config.capabilities.browserName;
 * 
 * var junitReporter = new jasmineReporters.JUnitXmlReporter({ consolidateAll:
 * false, savePath: 'testresults', modifyReportFileName:
 * function(generatedFileName, suite) { // this will produce distinct file names
 * for each capability, // e.g. 'firefox.SuiteName' and 'chrome.SuiteName'
 * return browserName + '.' + generatedFileName; } });
 * jasmine.getEnv().addReporter(junitReporter); }); }
 */
};
