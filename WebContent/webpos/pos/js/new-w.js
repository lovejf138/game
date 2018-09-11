//加载代理商下拉列表
function loadAgentSelectList(){
	$.ajax({
		url : "/api/web/agent/select_list.do",
		type: "post",
		dataType : "json",
		success  : function (data){
			if(data.flag == 1){
				vmModel.agentSelectList(data.list);
				var $agentId = $("#agentId").val();
				if("" != $agentId){
					$("#commerceInfo").attr("checked", true);		
					commerceInfoClick();
					$("#agentSelectId").attr('value', $agentId);
					$("#agentId").val("");
					loadCommerceTeamSelectList();
				}
			}
		}
	});
}

//根据代理商加载商务组下拉列表
function loadCommerceTeamSelectList() {

	$.ajax({
		url:"/api/web/commerce_team/select_commerce_team_list.do",
		type:"post",
		data:{
			agent_id:$("#agentSelectId").val()
		},
		dataType:"json",
		success:function (data){
			if(data.flag==1){
				vmModel.commerceTeamSelectList(data.list);
				var $commerceTeamId = $("#commerceTeamId").val();
				if("" != $commerceTeamId){
					$("#commerceTeamSelectId").attr("value", $commerceTeamId);
					$("#commerceTeamId").val("");
					loadCommerceSelectList();
				}
			}
		}
	});
}

//根据商务组加载商务下拉列表
function loadCommerceSelectList(){

	$.ajax({
		url: "/api/web/commerce_team/select_commerce_list.do",
		type: "post",
		data:{
			commerce_team_id : $("#commerceTeamSelectId").val()
		},
		dataType: "json",
		success: function (data){
			if(data.flag == 1){
				vmModel.commerceSelectList(data.list);
				var $commerceId = $("#commerceId").val();
				if("" != $commerceId){
					$("#commerceSelectId").attr("value", $commerceId);
					$("#commerceId").val("");
				}
			}
		}
	})
}