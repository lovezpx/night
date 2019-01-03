top.window.index = {
	//new tab页面
	newPage:function(title,url){
		if ($('#tab').tabs('exists', title)) {
			 $('#tab').tabs('select', title);
		}else{
			$("#tab").tabs('add',{  
			    title:title,  
			    closable:true,  
			    content:'<iframe scrolling="no" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>'
			}); 
		}
	},
	cw :new commonWin("#win"),
	showDialog:function(title,url,width,height){
		top.window.index.cw.resize(width,height);
		top.window.index.cw.setIframe(url);
		top.window.index.cw.open();
		top.window.index.cw.setTitle(title);
	},
	closeDialog:function(tableId){
		top.window.index.cw.close();
		if(tableId)
		top.window.index.datagridReload(tableId);
	},
	datagridReload:function(tableId,serial){
		top.window.datagridReload(tableId,serial);
	}
}