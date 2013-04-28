//The following functions needs to implemented to enable the webpages with integrisign

//The getHashdata function will be called by applet to get the hash data.
//e.g form fields that needs to be included in hash should be concatinated into a single string
//Hidden fields meant to hold Integrisign data should be omitted from hash as they doesn't carry any
//value prior to the act of signing.

function getHashData() {
	//In this example uid,uname field values are sent to Hash
	//Similar way any number of form field values can be sent to hash
	var hashstr;
	var uid = jQuery(".uid").val();
	hashstr = uid;
	return hashstr;
}

// This signnow function responsible for the capture and paint the signature on web forms.

function signNow() {

	var signObj = document.getElementById("sign");
	var uid = jQuery(".uid").val();
	var lastName = jQuery(".lastName").val();
	var firstName = jQuery(".firstName").val();
	// The thickness can be in the range of 1 - 9, anything above 9 will be treated as the default value 2.
	signObj.setSignThickness(2);
	signObj.enableWhiteSpaceRemoval(true);
	signObj.enableMaxEnlargementFactor(true);
	signObj.setMaxEnlargementFactor(1.25);
	signObj.setEnableLCDButtons(true);
	signObj.signNowEx(uid, lastName + "," + firstName, "demo", "demo", "demo",
			"demo", true);

}

// trybytes function gets the raw signature and bmp data as strings this should be
// called before submit after signnow
// The returned strings from calls to applets are assigned to form hidden fields.

function tryBytes() {
	var signObj = document.getElementById("sign");
	var pngstr;
	isSigned = signObj.isSigned();
	//if(sunpluginstatus ==0)
	//signObj.setSignThickness(5);
	// Information can only be extracted if isSigned() returns true
	if (isSigned == true) {
		pngstr = signObj.getPNGString(x, 125, 60, true);
		//alert(pngstr);
	} else
		alert("Before Submit form must be signed");
}