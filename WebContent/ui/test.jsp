<%@ include file="/WEB-INF/ui/commoninclude.jsp" %>

<script src='<c:url value="/js/form/jquery.form.js" />' type="text/javascript"></script>

<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/styles.css" />' />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/redmond/jquery-ui-1.8.16.custom.css" />' />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/css/jqgrid/ui.jqgrid.css" />' />
<link rel="stylesheet" type="text/css" 	href='<c:url value="/css/memu-0.1.css" />' />
<script src='<c:url value="/js/jquery/jquery-1.6.2.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery/jquery-ui-1.8.16.custom.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/grid.locale-en.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jqgrid/jquery.jqGrid.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery.json-2.3.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery.memu-0.1.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/form/jquery.form.js" />' type="text/javascript"></script>


<html>
<body>
	<form id="validationForm" action="/imgmt/common/listproducts.form" method="GET"> 
    Username: <input type="text" name="username" /> 
    Password: <input type="password" name="password" /> 
    <input type="submit" value="Submit" /> 
</form>
</body>

<script type="text/javascript">
$(document).ready(function() { 
    // bind form using ajaxForm 
	$('#validationForm').ajaxForm(function() { 
        alert("Thank you for your comment!"); 
    }); 
});

function validate(formData, jqForm, options) { 
    // formData is an array of objects representing the name and value of each field 
    // that will be sent to the server;  it takes the following form: 
    // 
    // [ 
    //     { name:  username, value: valueOfUsernameInput }, 
    //     { name:  password, value: valueOfPasswordInput } 
    // ] 
    // 
    // To validate, we can examine the contents of this array to see if the 
    // username and password fields have values.  If either value evaluates 
    // to false then we return false from this method. 
 
    for (var i=0; i < formData.length; i++) { 
        if (!formData[i].value) { 
            alert('Please enter a value for both Username and Password'); 
            return false; 
        } 
    } 
    alert('Both fields contain values.'); 
}

</script>

</html>