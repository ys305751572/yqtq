<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-cn">
<div class="modal fade" id="addNew-event" tabindex="-1" role="dialog">+
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">场地禁用时间</h4>
            </div>
            <div class="modal-body" id="reservebox">
                <form class="form-validation" role="form">
                    <div>
                        <label for="start" class="col-md-2 control-label">开始时间</label>
                        <%--<input type="text" id="start" value="" name="sDate" class="input-sm form_datetime form-control validate" placeholder="..." >--%>
                        <select id="start" class="select">
                            <option value="08:00">08:00</option>
                            <option value="10:00">10:00</option>
                            <option value="12:00">12:00</option>
                            <option value="14:00">14:00</option>
                            <option value="16:00">16:00</option>
                            <option value="18:00">18:00</option>
                            <option value="20:00">20:00</option>
                            <option value="22:00">22:00</option>
                        </select>
                        <label for="end" class="col-md-2 control-label">结束时间</label>
                        <%--<input type="text" id="end" value="" name="sDate" class="input-sm form_datetime form-control validate" placeholder="..." >--%>
                        <select id="end" class="select">
                            <option value="10:00">10:00</option>
                            <option value="12:00">12:00</option>
                            <option value="14:00">14:00</option>
                            <option value="16:00">16:00</option>
                            <option value="18:00">18:00</option>
                            <option value="20:00">20:00</option>
                            <option value="22:00">22:00</option>
                            <option value="23:59">24:00</option>
                        </select>
                    </div>
                    <input type="hidden" id="getStart" />
                    <input type="hidden" id="getEnd" />
                </form>
            </div>

            <div class="modal-body" align="center">
                <input type="submit" class="btn btn-info btn-sm" id="addEvent" value="确定">
                <button type="button" class="btn btn-info btn-sm" data-dismiss="modal">关闭</button>
                <div id="but" style='float: left'></div>
            </div>
        </div>
    </div>
</div>
<script>
    $(".prev").parent().find("tr").attr("hidden");
    $('.form_datetime').datetimepicker({
        format: 'hh:00',
        weekStart: 1,
        autoclose: true,
        startView: 1,
        minView: 1,
        forceParse: false,
        language: 'zh-CN'
    });
</script>
</html>
