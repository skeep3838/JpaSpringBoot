var item = [];

//function pagePoint(page){	
//	$.ajax({
//		type: "GET",
//		url: ("items/page/"+page),
//		success: function (data) {
//			var itemJson=data;
//			$("#mainTable").
//			alert("ajax: "+itemJson);
//		}
//	});
//}

function itemCount(){	
	var price = document.getElementById("price").value;
	$.ajax({
	type: "GET",
	url: ("items/"+price),
	success: function (data) {
		var itemCountN=data;
		alert("itemCount: "+itemCountN);
	}
});
}

//選擇購買商品-------------------------------------------------------
function getQty(id) {
	
	// 判斷onchange的商品是否新增過
	var flag = false;
	var itemObject = {};
	item.forEach(function(item, index) {
		if (item.iid == id) {
			item.qty = document.getElementById(id).value;
			flag = true;
//			break;
		}
	});
	if (flag == false) {	
		item.push({
			iid:id,
			qty:document.getElementById(id).value
		});
	}


}

$("form").submit(function() {
	var jsonString = JSON.stringify(item);
	$("#orderDetail").val(jsonString);
//	alert($("#orderDetail").val());
	item=[];
	return;
});