/**
 * Created by Selenium on 22-12-2015.
 */
var nodemailer = require("nodemailer");

var transport = nodemailer.createTransport("SMTP", {
    host: "smtp.gmail.com", // hostname
    secureConnection: true, // use SSL
    port: 465, // port for secure SMTP
    auth: {
        user: "swradha@gmail.com",
        pass: "Sblr2015"
    }
});

console.log("SMTP Configured");

var mailOptions = {
    from: 'swradha@gmail.com.com', // sender address
    to: 'bhowmiknyc2008@gmail.com', // list of receivers
    subject: 'Report for Test Result', // Subject line
    text: 'Contains the test result for the smoke test in html file', // plaintext body
    attachments: [
        { 
           'filename': 'Results.html',
        	//'filename': 'junitresults-angularjshomepage.xml',
            'filePath': './Reports/Results.html',
           // 'filePath': './testresults/junitresults-angularjshomepage.xml',
        }

    ]
};
transport.sendMail(mailOptions, function (error, response) {
    if (error) {
        console.log(error);
    } else {
        console.log("Message sent: " + response.message);
    }

});
