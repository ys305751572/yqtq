<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../inc/taglibs.jsp" %>
<script>
    //省 市
     function selectCity(data){
        var cId = $("#cId").val();
        if(data!=""){
            $.ajax({
                url:"${contextPath}/admin/reserve/selectCity",
                data:{
                    "provinceId":data
                },
                success:function(data){
                    $("#cityId").empty();
                    var op = "<option value=''>请选择城市</option>";
                    for(var i= 0;i<data.length;i++){
                        var cityId = data[i].cityId;
                        var city = data[i].city;
                        if(cId==cityId){
                            op += "<option value='"+cityId+"' selected>"+city+"</option>";
                        }else{
                            op += "<option value='"+cityId+"'>"+city+"</option>";
                        }
                    }
                    $("#cityId").append(op);
                    $("#cityId").selectpicker('refresh');
                }
            });
        }else{
            $("#cityId").empty();
            $("#cityId").append("<option value=''>"+"城市"+"</option>");
            $("#cityId").selectpicker('refresh');
        }
    }
    //地图
     function map (){
         var iWidth=1000; //弹出窗口的宽度;
         var iHeight=600; //弹出窗口的高度;
         var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
         var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
         window.open("${contextPath}/admin/stadium/map","地图","height="+iHeight+",width="+iWidth+",top="+iTop +",left="+iLeft+",toolbar=no,menubar=no,resizable=no,location=no,status=no");
     }
</script>