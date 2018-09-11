

$(function (){
	for (h = 0; h <= 9; h++) {
		
		document.getElementById("hour")[h]=new Option("0"+h+"时", "0"+h)
	}
	for (h = 10; h <= 23; h++) {
		document.getElementById("hour")[h]=new Option(h+"时", h)
	}
	for (m = 0; m <= 9; m++) {
		document.getElementById("minute")[m]=new Option("0"+m+"分", "0"+m)
		
	}
	for (m = 10; m <= 59; m++) {
		document.getElementById("minute")[m]=new Option(m+"分", m)
	}
	
	for (s = 0; s <= 9; s++) {
		
		document.getElementById("second")[s]=new Option("0"+s+"秒", "0"+s)
	}
	for (s = 10; s <= 59; s++) {
	
		document.getElementById("second")[s]=new Option(s+"秒", s)
	}
	
})

function setTime() {
	$("#timeSelect").show();
}
function confirmTime(){
	$("#settlementTime").val($("#hour").val()+":"+$("#minute").val()+":"+$("#second").val());
	$("#timeSelect").hide();
}
