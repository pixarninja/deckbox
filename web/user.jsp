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
<div class="well row">
    <div class="col-xs-12">
        <div class="col-xs-12">
            <h2>Username</h2><br>
            <h4>
                <p>Below is your profile information. You may edit your information by selecting the "Edit Information" button. You may edit any decks or collections you have recorded by selecting the item's title, which will take you to the item's information page. Below you will also find your favorited items, friends, and a log of your site history.</p>
                <br><br><hr>
            </h4>
        </div>
        <div class="col-xs-12 col-sm-4">
            <h4>
                <img width="100%" src="<%=picture%>" alt="<%=picture%>" id="center-img"></img>
                <br><br>
                <form id="editForm" action="UserServlet" method="POST">
                    <input type="hidden" name="action" value="edit-profile">
                    <input type="hidden" name="username" value="<%=username%>">
                    <button id="form-submit" type="submit"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;Edit Information</button>
                </form>
                <br>
            </h4>
        </div>
        <div class="col-xs-1 hidden-sm hidden-md hidden-lg"></div>
        <div class="col-xs-12 col-sm-8">
            <h3>Personal Information<hr></h3>
            <h4>
                <div class="col-xs-12">
                    <div class="row">
                        <div class="col-xs-4 col-sm-2">
                            <div class="row">
                                <p>Name</p>
                            </div>
                        </div>
                        <div class="col-xs-8 col-sm-10">
                            <div class="row">
                                <p>Derp</p>
                            </div>
                        </div>
                        <div class="col-xs-12"><br></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-4 col-sm-2">
                            <div class="row">
                                <p>Email</p>
                            </div>
                        </div>
                        <div class="col-xs-8 col-sm-10">
                            <div class="row">
                                <p>Derp</p>
                            </div>
                        </div>
                        <div class="col-xs-12"><br></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-4 col-sm-2">
                            <div class="row">
                                <p>Age</p>
                            </div>
                        </div>
                        <div class="col-xs-8 col-sm-10">
                            <div class="row">
                                <p>Derp</p>
                            </div>
                        </div>
                        <div class="col-xs-12"><br></div>
                    </div>
                </div>
            </h4>
            <h3>Site Information<hr></h3>
            <h4>
                <div class="col-xs-12">
                    <div class="row">
                        <div class="col-xs-12 col-sm-3">
                            <div class="row">
                                <p>Collections</p>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-9">
                            <div class="row">
                                <p>Derp</p>
                            </div>
                        </div>
                        <div class="col-xs-12"><br></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-sm-3">
                            <div class="row">
                                <p>Decks</p>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-9">
                            <div class="row">
                                <p>Derp</p>
                            </div>
                        </div>
                        <div class="col-xs-12"><br></div>
                    </div>
                </div>
            </h4>
        </div>
        <div class="col-xs-12">
            <h3>Favorites<hr></h3>
            <h4>
                <div class="row">
                    <div class="col-xs-6 col-sm-4 col-md-2">
                        <img width="100%" src="<%=cardImage%>" alt="<%=cardImage%>" id="center-img"></img>
                    </div>
                    <div class="col-xs-6 col-sm-8 col-md-10">
                        <div class="row">
                            <p>Name</p>
                            <p>Date</p>
                            <div class="hidden-xs">
                                <hr id="in-line-hr">
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sit amet quam pretium lacus convallis ultricies eu sed metus. Vestibulum a molestie quam. Praesent in scelerisque tortor. Etiam vulputate orci et erat imperdiet feugiat. Praesent bibendum non purus vel consequat. Quisque a venenatis ex. Pellentesque consequat neque dui, eget commodo ipsum fermentum vel. Donec lacinia feugiat elementum. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Duis quis diam augue. Vivamus accumsan consectetur nibh vel sodales. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed nec tellus eget est rutrum tempus et at dui.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 hidden-sm hidden-md hidden-lg"><br></div>
                    <div class="col-xs-12 hidden-sm hidden-md hidden-lg">
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sit amet quam pretium lacus convallis ultricies eu sed metus. Vestibulum a molestie quam. Praesent in scelerisque tortor. Etiam vulputate orci et erat imperdiet feugiat. Praesent bibendum non purus vel consequat. Quisque a venenatis ex. Pellentesque consequat neque dui, eget commodo ipsum fermentum vel. Donec lacinia feugiat elementum. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Duis quis diam augue. Vivamus accumsan consectetur nibh vel sodales. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed nec tellus eget est rutrum tempus et at dui.</p>
                    </div>
                    <div class="col-xs-12"><br></div>
                </div>
            </h4>
            <h3>Friends<hr></h3>
            <h4>
                <div class="row">
                    <div class="col-xs-6 col-sm-4 col-md-2">
                        <img width="100%" src="<%=picture%>" alt="<%=picture%>" id="center-img"></img>
                    </div>
                    <div class="col-xs-6 col-sm-8 col-md-10">
                        <div class="row">
                            <p>Name</p>
                            <p>Date</p>
                            <div class="hidden-xs">
                                <hr id="in-line-hr">
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sit amet quam pretium lacus convallis ultricies eu sed metus. Vestibulum a molestie quam. Praesent in scelerisque tortor. Etiam vulputate orci et erat imperdiet feugiat. Praesent bibendum non purus vel consequat. Quisque a venenatis ex. Pellentesque consequat neque dui, eget commodo ipsum fermentum vel. Donec lacinia feugiat elementum. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Duis quis diam augue. Vivamus accumsan consectetur nibh vel sodales. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed nec tellus eget est rutrum tempus et at dui.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 hidden-sm hidden-md hidden-lg"><br></div>
                    <div class="col-xs-12 hidden-sm hidden-md hidden-lg">
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sit amet quam pretium lacus convallis ultricies eu sed metus. Vestibulum a molestie quam. Praesent in scelerisque tortor. Etiam vulputate orci et erat imperdiet feugiat. Praesent bibendum non purus vel consequat. Quisque a venenatis ex. Pellentesque consequat neque dui, eget commodo ipsum fermentum vel. Donec lacinia feugiat elementum. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Duis quis diam augue. Vivamus accumsan consectetur nibh vel sodales. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed nec tellus eget est rutrum tempus et at dui.</p>
                    </div>
                    <div class="col-xs-12"><br></div>
                </div>
            </h4>
            <h3>History<hr></h3>
            <h4>
                <div class="row">
                    <div class="col-xs-6 col-sm-4 col-md-2">
                        <img width="100%" src="<%=picture%>" alt="<%=picture%>" id="center-img"></img>
                    </div>
                    <div class="col-xs-6 col-sm-8 col-md-10">
                        <div class="row">
                            <p>Name</p>
                            <p>Date</p>
                            <div class="hidden-xs">
                                <hr id="in-line-hr">
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sit amet quam pretium lacus convallis ultricies eu sed metus. Vestibulum a molestie quam. Praesent in scelerisque tortor. Etiam vulputate orci et erat imperdiet feugiat. Praesent bibendum non purus vel consequat. Quisque a venenatis ex. Pellentesque consequat neque dui, eget commodo ipsum fermentum vel. Donec lacinia feugiat elementum. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Duis quis diam augue. Vivamus accumsan consectetur nibh vel sodales. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed nec tellus eget est rutrum tempus et at dui.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 hidden-sm hidden-md hidden-lg"><br></div>
                    <div class="col-xs-12 hidden-sm hidden-md hidden-lg">
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sit amet quam pretium lacus convallis ultricies eu sed metus. Vestibulum a molestie quam. Praesent in scelerisque tortor. Etiam vulputate orci et erat imperdiet feugiat. Praesent bibendum non purus vel consequat. Quisque a venenatis ex. Pellentesque consequat neque dui, eget commodo ipsum fermentum vel. Donec lacinia feugiat elementum. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Duis quis diam augue. Vivamus accumsan consectetur nibh vel sodales. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed nec tellus eget est rutrum tempus et at dui.</p>
                    </div>
                    <div class="col-xs-12"><br></div>
                </div>
            </h4>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>