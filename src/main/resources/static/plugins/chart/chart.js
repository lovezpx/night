

//图
function buildChart1(dom, option){	
	if(option == null){
		return ;
	}
	var p = {
        title: {
            text: option.title,
            x: -20 //center
        },
        xAxis: {
            categories: option.categories,
            title: {
                text: option.xtitle
            }
        },
        yAxis: {
            title: {
                text: option.ytitle
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: option.unit
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: option.series
    };
	if (typeof(dom) == "string") {
		$(dom).highcharts(p);
	} else {
		dom.highcharts(p);
	}
	
}
//图
function buildChart(selector, data, param){	
	if(data == null){
		return ;
	}
	var p = {
    	chart: {
			type: data.SeriesType==null?"spline":data.SeriesType,//图类型
	        marginTop: 30,
	        marginLeft: 50,
	        marginRight: 30,
	        backgroundColor: null,//图表区背景色
            plotBackgroundColor: null,//主图表区背景色，即X轴与Y轴围成的区域的背景色
            plotBorderWidth: 0,
            plotShadow: false,
            spacingBottom:data.spacingB,//底侧空间
            zoomType: 'x'
	    },
	    title: {
	        text: data.titleText,//标题
	        align:'center',
	        verticalAlign: 'top',
            style: {
    			font: 'bold 16px "Trebuchet MS", Verdana, sans-serif'
            },
	    	y:5
	    },
	    subtitle: {
			text: data.subtitle,
			align:'right',
			x:-30,
			style: { 
				color: '#666',
				font: 'bold 12px "Trebuchet MS", Verdana, sans-serif'
			}
		},
	    colors: [	//数列颜色，折线/柱状/饼状等图的颜色
         	'#4572A7', '#AA4643', '#89A54E', '#80699B', '#3D96AE', '#DB843D', 
			'#92A8CD', '#A47D7C', '#B5CA92', '#058DC7', '#50B432', '#ED561B', 
			'#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4'
	    ],
	    xAxis: {
	        labels: {	//设置X轴各分类名称的样式style
	            style:{
	    			color:"#333",
	    		},
				step:data.step,//步阶
				staggerLines:1,//交错显示
				y:20,
	    	},
	    	tickmarkPlacement: 'on',//刻度点位置
	    	lineWidth: 1,//基线宽度
	        categories: data.categories,//X轴分类名称
	    },
	    yAxis: {
	    	max:data.max,//最小值
	    	min:data.min,//最大值
			title:{//标题
				text:data.unit,//标题名称
				align:'high',//对齐方式，顶部对齐
                offset: 0,
                rotation: 0,//角度
                y: -15,//标题偏移
                style: {
                	color: "#333",
                }
			},
			lineWidth: 2,//基线（轴线）宽度
			gridLineWidth: 1,//网格(竖线)宽度
			gridLineDashStyle: 'Solid',//竖线类型、虚线
			gridLineColor:'#c0c0c0',//网格（竖线）颜色
	        labels: {//Y轴坐标标签
	            formatter: function() {//回调函数，用于格式化输出提示框的显示内容
	                return this.value;
	            },
	            style:{
	    			color:"#333",
	    		}
	        },
	        plotLines:data.plotLines,
	    },
	    tooltip: {//提示框，当用户鼠标悬停在数据序列（比如绘制的线）或数据节点上时显示的工具提示的设置选项
	    	enabled:true,
	    	valueSuffix:data.unit,//y值后缀
	        crosshairs: true,//十字丝
	        hideDelay: 0,//延时
	        shared: true,//共享,当提示框被共享，整个绘图区都会捕捉鼠标移动
	        headerFormat: '{point.key}<br/>',
			pointFormat: '<small style="color: {series.color}">{series.name}</small>： <b>{point.y} {point.d}</b><br/>'
	    },
	    plotOptions: {//绘图区选项
	    	series: {
        		connectNulls: true,//连接空值，Defaults to false.
    			shadow:true,// 图表线的阴影Defaults to false
				animation: true,//动画
				lineWidth:3
			},
	    	column: {//柱状图
				cursor:'pointer',//鼠标移到图表上时鼠标的样式
				dataLabels:{//图上是否显示数据标签
					enabled:data.dataLabelE,
					formatter: function() {
	                	return this.y + data.unit;
	            	}
				},
			},
			line: {//折线图
				cursor:'pointer',
				dataLabels:{
					enabled:data.dataLabelE,
					formatter: function() {
	                	return this.y + data.unit;
	            	}
				},
				marker: {//图例说明上的标志
	                radius: 4,
	                lineColor: '#c0c0c0',
	                lineWidth: 1
	            }
			},
			spline: {//曲线图
				cursor:'pointer',
				dataLabels:{
					enabled:data.dataLabelE,
					formatter: function() {
	                	return this.y + data.unit;
	            	}
				},
				marker: {//图例说明上的标志
					enabled:data.marker,
	                radius: 4,
	                lineColor: '#c0c0c0',
	                lineWidth: 1
	            }	
			},
			scatter: {//散点图
				cursor:'pointer',
				dataLabels:{
					enabled:data.dataLabelE,
					formatter: function() {
	                	return this.y + data.unit;
	            	}
				},
				marker: {//图例说明上的标志
	                radius: 4,
	                lineColor: '#c0c0c0',
	                lineWidth: 1
	            }	
			}
        },
	    legend: {//图例选项，即数据类标示
        	y:60,
	    	backgroundColor: '#fff',
	    	borderColor:'#fff',
	    	borderRadius:0,
	    	shadow: true,
	        floating: true,//是否可以浮动
			enabled: data.legendshow,//是否显示图例
			verticalAlign: 'bottom',//垂直对齐
			align: 'center',//水平对齐
			layout: 'horizontal',//显示形式，支持水平horizontal和垂直vertical	horizontal默认横向显示
		},
		exporting:{
			enabled:false//用来设置是否显示'打印','导出'等功能按钮，不设置时默认为显示
		},
		series: data.series,//数据列选项
		credits:{
			enabled:false
		}
	};
	if(param){
		p = $.extend(true, {}, p, param);
	}
	if (typeof(selector) == "string") {
		$(selector).highcharts(p);
	} else {
		selector.highcharts(p);
	}
	
}
//多图
function buildCharts(selector, data, param){	
	if(data == null)
		return ;
	var p = {
    	chart: {
			type: 'spline',//图类型
	        marginTop: 30,
	        marginLeft: 70,
	        marginRight: 70,
	        backgroundColor: null,//图表区背景色
            plotBackgroundColor: null,//主图表区背景色，即X轴与Y轴围成的区域的背景色
            plotBorderWidth: 0,
            plotShadow: false,
            spacingBottom:data.spacingB,//底侧空间
            zoomType: 'x'
	    },
	    title: {
	        text: data.titleText,//标题
	        align:'center',
	        verticalAlign: 'top',
            style: {
    			font: 'bold 16px "Trebuchet MS", Verdana, sans-serif'
            },
	    	y:5
	    },
	    subtitle: {
			text: data.subtitle,
			align:'right',
			x:-50,
			style: { 
				color: '#666',
				font: 'bold 12px "Trebuchet MS", Verdana, sans-serif'
			}
		},
		colors: [	//数列颜色，折线/柱状/饼状等图的颜色
             	'#4572A7', '#AA4643', '#89A54E', '#80699B', '#3D96AE', '#DB843D', 
				'#92A8CD', '#A47D7C', '#B5CA92', '#058DC7', '#50B432', '#ED561B', 
				'#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4'
		],
	    xAxis: [{
	        labels: {	//设置X轴各分类名称的样式style
	            style:{
	    			color:"#333",
	    		},
				step:data.step,//步阶
				staggerLines:1,//交错显示
				y:20,
	    	},
	    	tickmarkPlacement: 'on',//刻度点位置
	    	lineWidth: 1,//基线宽度
	        categories: data.categories,//X轴分类名称
	    }],
	    yAxis:data.yAxis,
	    tooltip: {//提示框，当用户鼠标悬停在数据序列（比如绘制的线）或数据节点上时显示的工具提示的设置选项
			enabled:true,
	        crosshairs: true,//十字丝
	        hideDelay: 0,//延时
	        shared: true,//共享,当提示框被共享，整个绘图区都会捕捉鼠标移动
	        headerFormat: '{point.key}<br/>',
			pointFormat: '<small style="color: {series.color}">{series.name}</small>： <b>{point.y} {point.d}</b><br/>'
	    },
	    plotOptions: {//绘图区选项
	    	series: {
        		connectNulls: true,//连接空值，Defaults to false.
    			shadow:true,// 图表线的阴影Defaults to false
				animation: false,//动画
				lineWidth:3
			},
			spline: {//曲线图
				cursor:'pointer',
				dataLabels:{
					enabled:false,
				},
				marker: {//图例说明上的标志
					enabled: true,
	                radius: 4,
	                lineColor: '#c0c0c0',
	                lineWidth: 1
	            }	
			}
        },
	    legend: {//图例选项，即数据类标示
        	y:30,
	    	backgroundColor: '#fff',
	    	borderColor:'#fff',
	    	borderRadius:0,
	    	shadow: true,
	        floating: true,//是否可以浮动
			enabled: true,//是否显示图例
			verticalAlign: 'bottom',//垂直对齐
			align: 'center',//水平对齐
			layout: 'horizontal',//显示形式，支持水平horizontal和垂直vertical	horizontal默认横向显示
		},
		exporting:{
			enabled:false
		},
		credits:{
			enabled:false
		},
		series: data.series,//数据列选项
	};
	if(param){
		p = $.extend(true, {}, p, param);
	}
	$(selector).highcharts(p);
}
//饼图
function buildPieChart(selector, data){
	if(data == null){
		return ;
	}
	var p = {
		chart: {
			type: 'pie',
			marginTop: 20,
	        backgroundColor: null,//图表区背景色
	        plotBackgroundColor: null,//主图表区背景色，即X轴与Y轴围成的区域的背景色
	        plotBorderWidth: 0,
	        plotShadow: false,
	    },
	    title: {
	        text: data.titleText,//标题
	        align:'center',
	        verticalAlign: 'top',
	        style: {
				fontWeight: 'bold',
	        },
	    },
        tooltip: {
	    	enabled:false,
        	hideDelay: 0,
            pointFormat: '<br><b>{point.y}</b>'
        },
        legend: {//图例选项，即数据类标示
        	y:30,
	    	backgroundColor: '#fff',
	    	borderColor:'#fff',
	    	borderRadius:0,
	    	shadow: true,
	        floating: true,//是否可以浮动
			enabled: data.legendshow,//是否显示图例
			verticalAlign: 'top',//垂直对齐
			align: 'right',//水平对齐
			layout: 'vertical horizontal',//显示形式，支持水平horizontal和垂直vertical	horizontal默认横向显示
		},
        plotOptions: {
            pie: {
        	 	cursor:'pointer',
                dataLabels: {
                    enabled: data.dataLabelE,
                    color:'#000',
                	distance: data.distance,
					formatter: function() {
                        return '<b>'+ Highcharts.numberFormat(this.percentage, 1) +'%'+'</b>';
                     }
                },
                events:{
					click: data.pieClick	
				},
                allowPointSelect: true,//点击标签时允许序列的点被选
                showInLegend: true,
			    borderWidth: 0,// 边框宽度
                startAngle: 0,//饼图块开始的角度
                endAngle: 360,
                size: 75,
                center: ['50%', '50%']//饼图的中心相对于绘图区
            }
        },
		exporting:{
			enabled:false
		},
		series:data.series
    };
	p = $.extend(true, p, {colors:data.colors});//深度拷贝，对象拷贝
	$(selector).highcharts(p);	
}