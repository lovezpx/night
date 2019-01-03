// JavaScript Document
(function($){

	function init(container){
		var state = $.data(container, 'datespinner');
		
		getName(container);
		$(container).addClass("easyui-timespinner timespinner-f spinner-f textbox-f");
		$(container).css("display","none");
		state.options.width = state.options.width == 'auto' ? $(container).width() : state.options.width;
		$panelSpan = $("<span>").addClass("textbox spinner")
			.css("width",(state.options.width-2) + "px")
			.css("height",(state.options.height-2) + "px");
		$span = $("<span>").addClass("textbox-addon textbox-addon-right")
			.css("right","0px");
		$containA = $("<a>")
			.attr("href","javascript:void(0)")
			.attr("icon-index","0")
			.attr("tabindex","-1")
			.css("width","18px")
			.css("height",(state.options.height-2) + "px")
			.addClass("textbox-icon spinner-arrow");
		$upA = $("<a href=\"javascript:void(0)\" class=\"spinner-arrow-up\" tabindex=\"-1\">");
		$downA = $('<a href="javascript:void(0)" class="spinner-arrow-down" tabindex="-1">');
		$showInput = $('<input type="text" class="textbox-text validatebox-text textbox-prompt" autocomplete="off" placeholder="" style="margin-left: 0px; margin-right: 18px; padding-top: 1px; padding-bottom: 1px; width: '+(state.options.width-28)+'px;line-height:'+(state.options.height-4)+'px;color:#000">');
		$valueInput = $('<input type="hidden" class="textbox-value" value="">');
		
		$(container).after($panelSpan);
		$panelSpan.append($span);
		$span.append($containA);
		$containA.append($upA);
		$containA.append($downA);
		$panelSpan.append($showInput);
		$panelSpan.append($valueInput);
		
		setValueDom(container,$valueInput);
		setShowValueDom(container,$showInput);
		setName(container,$valueInput);
		bindEvent(container,$containA,$showInput);
	}
	
	
	
	
	//获取name，并将container节点的name删除
	function getName (container) {
		var name = $(container).attr("name");
		var state = $.data(container, 'datespinner');
		state.name = name;
		if (undefined != name) {
			$(container).attr("datespinnername",name);
			$(container).removeAttr("name");
		}
	}
	//将表单name绑定到实际input上
	function setName (container,$valueInput) {
		
		var name = $.data(container, 'datespinner').name;
		
		if (undefined != name) {
			$valueInput.attr("name",name);
		}
	}
	//将实际值存放的input放到$.data中
	function setValueDom(container,$valueInput) {
		var state = $.data(container, 'datespinner');
		$valueInput.val(timeStamp2String(state.options.value,state.options.valFormate));
		state.valueDom = $valueInput[0];
		state.value = state.options.value.getTime();
	}
	//将显示input放到$.data中
	function setShowValueDom(container,$showInput) {
		var state = $.data(container, 'datespinner');
		
		$showInput.val(timeStamp2String(state.options.value,state.options.formate));
		state.showValueDom = $showInput[0];
	}
	//设置显示值
	function setText(container,param) {
		var state = $.data(container, 'datespinner');
		var showValueDom = state.showValueDom;
		$(showValueDom).val(timeStamp2String(param,state.options.formate));
	}
	//获取显示值
	function getText(container) {
		var state = $.data(container, 'datespinner');
		var showValueDom = state.showValueDom;
		return $(showValueDom).val();
	}
	//设置实际值
	function setValue(container,param) {
		var state = $.data(container, 'datespinner');
		var valueInput = state.valueDom;
		$(valueInput).val(timeStamp2String(param,state.options.valFormate));
	}
	//获取实际值
	function getValue(container) {
		var state = $.data(container, 'datespinner');
		var value = state.value;
		var d = new Date();
		d.setTime(value);
		return d;
	}
	
	//获取实际字符串
	function getVal(container) {
		var state = $.data(container, 'datespinner');
		var $valueInput = state.valueDom;
		
		return $valueInput.val();
	}
	//操作时事件加减，并设置值
	function op(container,optype) {
		var state = $.data(container, 'datespinner');
		var date = new Date();
		date.setTime(state.value);
		var incre = state.options.increment;
		if (optype == "down") {
			incre = 0 - incre;
		}
		
		date = optime(date,incre,state.options.type);
		state.value = date.getTime();
		setValue(container, date);
		setText(container,date);
		
	}
	//绑定加减点击事件， 绑定光标事件
	function bindEvent (container,$containA,$showInput) {
		var state = $.data(container,"datespinner");
		
		
		$containA.on("click","a",function(event) {
			var a = event.currentTarget;
			if ($(a).hasClass("spinner-arrow-up")) {
				op(container,"up");
				getTypeInFormateAndSelect($showInput[0],state);
			} else if ($(a).hasClass("spinner-arrow-down")) {
				op(container,"down");
				getTypeInFormateAndSelect($showInput[0],state);
			}
		});
		
		var formate = state.options.formate;
		var datearr = ["yyyy","MM","dd","HH","mm","ss"];
		state.options.type = $.fn.datespinner.defaults.type;
		//绑定光标事件
		$showInput.on("click",function(event){
			var pos = getCursorPosition(this);
			console.log("光标位置："+pos);
			getTypePosInFormateAndSelect(this,pos,state);
			
		});
	}
	//获取文本域光标位置
	function getCursorPosition(dom){
		var cursurPosition=-1;
		if(dom.selectionStart){//非IE浏览器
			cursurPosition=dom.selectionStart;
		}else{//IE
			var range = document.selection.createRange();
			range.moveStart("character",-dom.value.length);
			cursurPosition=range.text.length;
		}
		return cursurPosition;
	}
	//设置光标位置，并选中文本
	function setCursorPosition(dom,start,end){
		
		if(dom.selectionStart){//非IE浏览器
			dom.selectionStart=start;
			dom.selectionEnd=end;
		}else{//IE
			var range = dom.createTextRange();
			range.moveStart("character",start);
			range.moveEnd("character",end);
			range.select();
		}
	}
	//获取date类型在formate中的起始位置，并选中
	function getTypePosInFormateAndSelect(dom,pos,state) {
		var formate = state.options.formate;
		var datearr = ["yyyy","MM","dd","HH","mm","ss"];
		for (var i=0;i < datearr.length; i++) {
			var name = datearr[i];
			var index = formate.indexOf(name);
			if (index > -1) {
				if (pos >= index && pos <= (index + name.length)) {
					state.options.type = name;
					setCursorPosition(dom,index,(index + name.length));
					break;
				}
			}
		}
	}
	//根据选中类型在formate的位置选中文本
	function getTypeInFormateAndSelect(dom,state) {
		var formate = state.options.formate;
		var type = state.options.type;
		var index = formate.indexOf(type);
		setCursorPosition(dom,index,(index + type.length));
	}
	
	function getInv(container,et,type,inv) {
		var state = $.data(container,"datespinner");
		var arr = [];
		var st = getValue(container);
		var temptime = new Date();
		temptime.setTime(st.getTime());
		temptime.setHours(0, 0, 0, 0);
		while (temptime.getTime() <= et.getTime()) {
			arr.push(timeStamp2String(temptime, state.options.formate));
			temptime = optime(temptime, inv, type);
		}
		return arr;
	}
	
	//把时间转换成字符串
	function timeStamp2String(datetime,formate){
    
		var year = datetime.getFullYear();
		var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
		var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
		var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
		var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
		var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
		var str = formate;
		str = str.replace("yyyy",year);
		str = str.replace("MM",month);
		str = str.replace("dd",date);
		str = str.replace("HH",hour);
		str = str.replace("mm",minute);
		str = str.replace("ss",second);
		return str;
	}
	//操作时间加减
	function optime(datetime,incre,type){
		var year = datetime.getFullYear();
		var month = datetime.getMonth();
		var date = datetime.getDate();
		var hour = datetime.getHours();
		var minute = datetime.getMinutes();
		var second = datetime.getSeconds();
		
		if ("yyyy" == type) {
			year += incre;
		} else if ("MM" == type) {
			month += incre;
		} else if ("dd" == type) {
			date += incre;
		} else if ("HH" == type) {
			hour += incre;
		} else if ("mm" == type) {
			minute += incre;
		} else if ("ss" == type) {
			second += incre;
		}
		return new Date(year,month,date,hour,minute,second);
		
	}
	
	$.fn.datespinner = function(options, param){
		if (typeof options == 'string'){
			return $.fn.datespinner.methods[options](this, param);
		}
		
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'datespinner');
			if (state){
				$.extend(state.options, options);
			} else {
				$.data(this, 'datespinner', {
					options: $.extend({}, $.fn.datespinner.defaults, options),
					datespinner: $(this).addClass('datespinner'),
					panels: []
				});
				init(this);
			}
			
		});
	};
	
	$.fn.datespinner.methods = {
		options: function(jq){
			return $.data(jq[0], 'datespinner').options;
		},
		getValue : function(jq) {
			return getValue(jq[0]);
		},
		setValue : function(jq,params) {
			return setValue(jq[0],params);
		},
		getText : function(jq) {
			return getText(jq[0]);
		},
		setText : function(jq,params) {
			return setText(jq[0],params);
		},
		getInv : function(jq,params) {
			return getInv(jq[0], params.et, params.type, params.inv);
		}
		
	};
	
	$.fn.datespinner.parseOptions = function(target){
		var t = $(target);
		return $.extend({}, $.parser.parseOptions(target, [
			'width','height',
		]));
	};
	
	$.fn.datespinner.defaults = {
		width: 'auto',
		height: '22',
		value: new Date(),
		min: null,
		max: null,
		increment: 1,
		type : "MM",
		formate : "yyyy年MM月",
		valFormate : "yyyy-MM",
		spin : function(down){},
		
		
	};
})(jQuery);
