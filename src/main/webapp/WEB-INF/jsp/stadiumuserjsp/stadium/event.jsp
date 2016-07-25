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
                    <div class="form-group">
                        <label for="eventName">Event Name</label>
                        <input type="text" class="input-sm form-control validate[required]" id="eventName" placeholder="...">
                    </div>

                    <input type="hidden" id="getStart" />
                    <input type="hidden" id="getEnd" />
                </form>
            </div>

            <div class="modal-footer">
                <input type="submit" class="btn btn-info btn-sm" id="addEvent" value="确定">
                <button type="button" class="btn btn-info btn-sm" data-dismiss="modal">取消</button>
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
