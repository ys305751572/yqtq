<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../inc/taglibs.jsp" %>
<!-- Add event -->
<div class="modal fade" id="addNew-event">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Add an Event</h4>
            </div>
            <div class="modal-body">
                <form class="form-validation" role="form">
                    <%--<div class="form-group">--%>
                        <%--<label for="chk" class="col-md-2 control-label">预定状态:</label>--%>
                        <%--<input type="checkbox" id="chk" />可预订--%>
                    <%--</div>--%>
                    <%--<div class="div_hidden" id="div_hidden" style="display: none">--%>
                        <div class="form-group">
                            <label for="s_hour" class="col-md-2 control-label">开始时间</label>
                            <div class="col-md-9">
                                <input type="text" id="s_hour" value="" name="sDate" class="input-sm form_datetime form-control validate" placeholder="..." >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="e_minute" class="col-md-2 control-label"></label>
                            <div class="col-md-9">
                                <input type="text" id="e_minute" value="" name="eDate" class="input-sm form_datetime form-control validate" placeholder="..." >
                            </div>
                        </div>
                    <%--</div>--%>
                    <input type="hidden" id="getStart" />
                    <input type="hidden" id="getEnd" />
                </form>
            </div>

            <div class="modal-footer">
                <input type="submit" class="btn btn-info btn-sm" id="addEvent" value="Add Event">
                <button type="button" class="btn btn-info btn-sm" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal Resize alert -->
<div class="modal fade" id="editEvent">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Edit Event</h4>
            </div>
            <div class="modal-body">
                <div id="eventInfo"></div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-info btn-sm" data-dismiss="modal">Okay</button>
                <button type="button" class="btn btn-info btn-sm" id="editCancel" data-dismiss="modal">Cancel</button>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        if($(".icheckbox_minimal").attr("aria-checked")=="true"){
            $("#div_hidden").css('display','block');
        }else {
            $("#div_hidden").css('display','none');
        }

    })
</script>
<script>
    $('.form_datetime').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 1,
        forceParse: 0,
        format: "HH:mm"
    });
</script>
<%--<div class="modal fade" id="addNew-event" tabindex="-1" role="dialog">--%>
    <%--<div class="modal-dialog">--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button class="close" type="button" data-dismiss="modal" aria-hidden="true">&times;</button>--%>
                <%--<h4 class="modal-title">Modal title</h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body">--%>
                <%--<form class="form-horizontal" role="form">--%>
                    <%--<div class="form-group">--%>
                        <%--<label for="s_hour" class="col-md-2 control-label">开始时间</label>--%>
                        <%--<div class="col-md-9">--%>
                            <%--<select id="s_hour" class="select">--%>
                                <%--<option value="00">00</option>--%>
                                <%--<option value="01">01</option>--%>
                                <%--<option value="02">02</option>--%>
                                <%--<option value="03">03</option>--%>
                                <%--<option value="04">04</option>--%>
                                <%--<option value="05">05</option>--%>
                                <%--<option value="06">06</option>--%>
                                <%--<option value="07">07</option>--%>
                                <%--<option value="08" selected>08</option>--%>
                                <%--<option value="09">09</option>--%>
                                <%--<option value="10">10</option>--%>
                                <%--<option value="11">11</option>--%>
                                <%--<option value="12">12</option>--%>
                                <%--<option value="13">13</option>--%>
                                <%--<option value="14">14</option>--%>
                                <%--<option value="15">15</option>--%>
                                <%--<option value="16">16</option>--%>
                                <%--<option value="17">17</option>--%>
                                <%--<option value="18">18</option>--%>
                                <%--<option value="19">19</option>--%>
                                <%--<option value="20">20</option>--%>
                                <%--<option value="21">21</option>--%>
                                <%--<option value="22">22</option>--%>
                                <%--<option value="23">23</option>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label for="e_minute" class="col-md-2 control-label"></label>--%>
                        <%--<div class="col-md-9">--%>
                            <%--<select id="s_minute" class="select">--%>
                                <%--<option value="00" selected>00</option>--%>
                                <%--<option value="10">10</option>--%>
                                <%--<option value="20">20</option>--%>
                                <%--<option value="30">30</option>--%>
                                <%--<option value="40">40</option>--%>
                                <%--<option value="50">50</option>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label for="e_hour" class="col-md-2 control-label">结束时间</label>--%>
                        <%--<div class="col-md-9">--%>
                            <%--<select id="e_hour" class="select">--%>
                                <%--<option value="00">00</option>--%>
                                <%--<option value="01">01</option>--%>
                                <%--<option value="02">02</option>--%>
                                <%--<option value="03">03</option>--%>
                                <%--<option value="04">04</option>--%>
                                <%--<option value="05">05</option>--%>
                                <%--<option value="06">06</option>--%>
                                <%--<option value="07">07</option>--%>
                                <%--<option value="08">08</option>--%>
                                <%--<option value="09">09</option>--%>
                                <%--<option value="10">10</option>--%>
                                <%--<option value="11">11</option>--%>
                                <%--<option value="12" selected>12</option>--%>
                                <%--<option value="13">13</option>--%>
                                <%--<option value="14">14</option>--%>
                                <%--<option value="15">15</option>--%>
                                <%--<option value="16">16</option>--%>
                                <%--<option value="17">17</option>--%>
                                <%--<option value="18">18</option>--%>
                                <%--<option value="19">19</option>--%>
                                <%--<option value="20">20</option>--%>
                                <%--<option value="21">21</option>--%>
                                <%--<option value="22">22</option>--%>
                                <%--<option value="23">23</option>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label for="e_minute" class="col-md-2 control-label"></label>--%>
                        <%--<div class="col-md-9">--%>
                            <%--<select id="e_minute" class="select">--%>
                                <%--<option value="00" selected>00</option>--%>
                                <%--<option value="10">10</option>--%>
                                <%--<option value="20">20</option>--%>
                                <%--<option value="30">30</option>--%>
                                <%--<option value="40">40</option>--%>
                                <%--<option value="50">50</option>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</form>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<input type="submit" class="btn btn-info btn-sm" id="addEvent" value="确定">--%>
                <%--<button type="button" class="btn btn-info btn-sm" data-dismiss="modal">取消</button>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>