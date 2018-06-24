function commonAjax(searchData,url){  
	var re;
	searchData=JSON.stringify(searchData);
	$.ajax({
        type :"POST",
        url  : url,
        data : searchData,
        dataType: "json",
        async: false,
        contentType: "application/json",  
        success : function(data) {
        	re= data;
        //	console.log(re);
   		 }
    }) 
    return re;
}  
  
