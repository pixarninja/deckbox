<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="beans.*"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="userInfo" class="beans.UserInfo" scope="request"/>
<%
    String username;
    if((String)request.getAttribute("username") == null) {
        username = request.getParameter("username");
    }
    else {
        username = (String)request.getAttribute("username");
    }
%>
<%@include file="header.jsp"%>
<%
    UserInfo user = userInfo.getUser(username);;
    String cardImage;
    String picture;
    if(user == null) {
        cardImage = "images/magic_card_back_hd.png";
        picture = "images/icons/battered-axe.png";
    }
    else {
        cardImage = user.getPicture();
        picture = user.getPicture();
    }
%>
<!-- Content -->
<div class="row">
    <div class="well col-xs-12 col-sm-8">
        <div class="col-xs-12">
            <h2>Create New Deck</h2><br>
            <h4>
                <p>If you would like to create a new deck, fill out the fields below and click the "Create Deck" button. You must give a title to the deck and specify if the deck is dependent upon items of a collection (i.e. the deck can only be made with items of the a specific collection).</p>
                <br><br><hr>
            </h4>
        </div>
        <div class="col-xs-12">
            <h4>
                <form id="newDeckForm" action="UserServlet" method="POST">
                    <input type="hidden" name="action" value="submit_new_deck">
                    <input type="hidden" name="username" value="<%=username%>">
                    <div class="row">
                        <div class="col-xs-5 col-sm-4">
                            <p>Deck Title</p>
                        </div>
                        <div class="col-xs-7 col-xs-8">
                            Please enter the title of the deck.<br><br>
                            <input id="input-field" name="title" type="text" required>
                        </div>
                        <div class="col-xs-12"><hr></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-5 col-sm-4">
                            <p>Deck Description</p>
                        </div>
                        <div class="col-xs-7 col-sm-8">
                            You may enter a description for this deck.<br><br>
                            <textarea id="input-field" name="description" style="width: 100%;min-height: 60px;"></textarea>
                        </div>
                        <div class="col-xs-12"><hr></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-sm-4">
                            <p>Deck Source</p>
                        </div>
                        <div class="col-xs-12 hidden-sm hidden-md hidden-lg"><br></div>
                        <div class="col-xs-12 col-sm-8">
                            Choose a source for the deck. If the deck does not depend on a collection, select "Independent". If the deck must contain items from a specific collection, select "Child Of" and choose the name of the parent collection from the drop-down list.<br><br>
                            <div class="col-xs-12">
                                <input name="source" type="radio" value="independent" checked> Independent
                            </div>
                            <div class="col-xs-12"><br></div>
                            <div class="col-xs-6">
                                <input name="source" type="radio" value="child" > Child Of
                            </div>
                            <div class="col-xs-6">
                                <select id="input-field">
                                    <option value="wishlist">Wishlist</option>
                                </select><br><br><br>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="hidden-xs col-md-4"></div>
                        <div class="col-xs-12 col-md-8">
                            <input id="form-submit" type="submit" value="Create Deck">
                        </div>
                    </div>
                </form>
                <div class="col-xs-12"><br></div>
            </h4>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>