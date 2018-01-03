<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String username = (String)request.getAttribute("username");
    String buffer = username;
    if(username != null && (username.length() > 16)) {
        username = "";
    }
%>
<%@include file="header.jsp"%>
<!-- Add code here -->
<div class="row">
    <div class="well col-xs-12 col-sm-8">
        <div class="col-xs-12">
            <%if(username == null) {%>
            <h2>Register</h2>
            <br>
            <h4>Fill out the fields below with a chosen username and password in order to register for an account.</h4>
            <br>
            <%}%>
            <%if(username != null && buffer.equals("error: password mismatch")) {%>
            <h2>Registration Error: The passwords you entered don't match</h2>
            <h4>Please re-enter your information below</h4>
            <%}%>
            <%if(username != null && buffer.equals("error: username already in use")) {%>
            <h2>Registration Error: The username you entered is already taken. Please select a different username</h2>
            <h4>Please re-enter your information below</h4>
            <%}%>
            <%if(username != null && buffer.equals("error: username is too long")) {%>
            <h1>Registration Error: The username you entered is too long</h1> <br>
            <h4>Please select a username shorter than 16 characters long</h4> <br>
            <%}%>
            <%if(username != null && buffer.equals("error: password is too long")) {%>
            <h1>Registration Error: The password you entered is too long</h1> <br>
            <h4>Please select a password shorter than 24 characters long</h4> <br>
            <%}%>
            <div class="row">
                <form id="registerForm" action="UserServlet" method="POST">
                    <input type="hidden" name="action" value="new-user">
                    <h4>
                        <div class="row">
                            <div class="col-xs-4">
                                <input type="hidden" name="action" value="validate">
                                <span class="pull-right">Name:&nbsp;&nbsp;</span>
                            </div>
                            <div class="col-xs-7">
                                Enter your real name. This will not be displayed to other users.<br><br>
                                <input id="input-field" name="name" type="text" required /><br><br>
                            </div>
                            <div class="col-xs-1"></div>
                        </div>
                        <div class="row">
                            <div class="col-xs-4">
                                <input type="hidden" name="action" value="validate">
                                <span class="pull-right">Email:&nbsp;&nbsp;</span>
                            </div>
                            <div class="col-xs-7">
                                Enter your email. This will not be displayed to other users.<br><br>
                                <input id="input-field" name="email" type="text" required /><br><br>
                            </div>
                            <div class="col-xs-1"></div>
                        </div>
                        <div class="row">
                            <div class="col-xs-4">
                                <input type="hidden" name="action" value="validate">
                                <span class="pull-right">Age:&nbsp;&nbsp;</span>
                            </div>
                            <div class="col-xs-7">
                                Enter your age. This will not be displayed to other users.<br><br>
                                <input id="input-field" name="age" type="text" required /><br><br>
                            </div>
                            <div class="col-xs-1"></div>
                        </div>
                        <div class="row">
                            <div class="col-xs-4">
                                <input type="hidden" name="action" value="validate">
                                <span class="pull-right">Username:&nbsp;&nbsp;</span>
                            </div>
                            <div class="col-xs-7">
                                Choose a username less than 16 characters long. This is the name that will be displayed to other users and will be required for when you login.<br><br>
                                <input id="input-field" name="username" type="text" size="16" required /><br><br>
                            </div>
                            <div class="col-xs-1"></div>
                        </div>
                        <div class="row">
                            <div class="col-xs-4">
                                <input type="hidden" name="action" value="validate">
                                <span class="pull-right">Password:&nbsp;&nbsp;</span>
                            </div>
                            <div class="col-xs-7">
                                Choose a password less than 24 characters long.<br><br>
                                <input id="input-field" name="password" type="text" required /><br><br>
                            </div>
                            <div class="col-xs-1"></div>
                        </div>
                        <div class="row">
                            <div class="col-xs-4">
                                <input type="hidden" name="action" value="validate">
                                <span class="pull-right">Confirm Password:&nbsp;&nbsp;</span>
                            </div>
                            <div class="col-xs-7">
                                Please enter the same password as above.<br><br>
                                <input id="input-field" name="confirm" type="text" required /><br><br><br>
                                <input id="form-submit" type="submit" value="Register"><br><br><br><br>
                            </div>
                            <div class="col-xs-1"></div>
                        </div>
                    </h4>
                </form>
            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>