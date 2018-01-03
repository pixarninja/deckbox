<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%String username = request.getParameter("username");%>
<%@include file="header.jsp"%>
<script src="js/scripts.js"></script>
<!-- Add code here -->
<div class="well row">
    <div class = col-xs-12>
        <h2>Advanced Search</h2><br>
        <h4>
            Fill in the fields below in order to search our database for cards, decks, or users. In order to search for multiple keywords of a single category, separate each query parameter with a '|' (vertical pipe) character.
        </h4><br><br>
        <div class="col-xs-12 col-md-5">
            <h3>Cards<hr></h3>
            <form id="searchCardsForm" action="Servlet" method="POST">
                <input type="hidden" name="action" value="games">
                <input type="hidden" name="username" value="<%=username%>">
                <div class="row">
                    <div class="col-xs-4">
                        <span class="pull-right">Order:&nbsp;&nbsp;</span>
                    </div>
                    <div class="col-xs-4">
                        <input name="order" type="radio" value="asc" checked> Ascending&nbsp;&nbsp;
                    </div>
                    <div class="col-xs-4">
                        <input name="order" type="radio" value="dsc" > Descending
                    </div>
                    <div class="col-xs-12"><hr></div>
                </div>
                <div class="row">
                    <div class="col-xs-4">
                        <span class="pull-right">Rarity:&nbsp;&nbsp;</span>
                    </div>
                    <div class="col-xs-4">
                        <input name="common" type="checkbox">&nbsp;Common
                    </div>
                    <div class="col-xs-4">
                        <input name="uncommon" type="checkbox">&nbsp;Uncommon
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-4"></div>
                    <div class="col-xs-4">
                        <input name="rare" type="checkbox">&nbsp;Rare
                    </div>
                    <div class="col-xs-4">
                        <input name="mythic rare" type="checkbox">&nbsp;Mythic Rare
                    </div>
                    <div class="col-xs-12"><hr></div>
                </div>
                <div class="row">
                    <div class="col-xs-4">
                        <span class="pull-right">Search:&nbsp;&nbsp;</span>
                    </div>
                    <div class="col-xs-4">
                        <input name="name" type="checkbox">&nbsp;Name
                    </div>
                    <div class="col-xs-4">
                        <input name="type" type="checkbox">&nbsp;Type
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-4"></div>
                    <div class="col-xs-4">
                        <input name="text" type="checkbox">&nbsp;Text
                    </div>
                    <div class="col-xs-12"><br></div>
                </div>
                <div class="row">
                    <div class="col-xs-4">
                        <span class="pull-right">Text:&nbsp;&nbsp;</span>
                    </div>
                    <div class="col-xs-8">
                        <input id="input-field" name="query" type="text">
                    </div>
                    <div class="col-xs-12"><hr></div>
                </div>
                <div class="row">
                    <div class="col-xs-4">
                        <span class="pull-right">Mana Color:&nbsp;&nbsp;</span>
                    </div>
                    <div class="col-xs-8">
                        <div class="row">
                            <div class="col-xs-1"></div>
                            <div class="col-xs-2" title="White Mana" id="white-mana" onclick=selectMana(0);></div>
                            <div class="col-xs-2" title="Blue Mana" id="blue-mana" onclick=selectMana(1);></div>
                            <div class="col-xs-2" title="Black Mana" id="black-mana" onclick=selectMana(2);></div>
                            <div class="col-xs-2" title="Red Mana" id="red-mana" onclick=selectMana(3);></div>
                            <div class="col-xs-2" title="Green Mana" id="green-mana" onclick=selectMana(4);></div>
                        </div>
                    </div>
                    <div class="col-xs-12"><br></div>
                </div>
                <div class="row">
                    <div class="col-xs-4">
                        <span class="pull-right">Mana Cost:&nbsp;&nbsp;</span>
                    </div>
                    <div class="col-xs-8">
                        <input id="input-field" name="cost" type="text">
                    </div>
                    <div class="col-xs-12"><hr></div>
                </div>
                <div class="row">
                    <div class="col-xs-4">
                        <span class="pull-right">Card Type:&nbsp;&nbsp;</span>
                    </div>
                    <div class="col-xs-8">
                        <input id="input-field" name="type" type="text"><br><br>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-4">
                        <span class="pull-right">Edition:&nbsp;&nbsp;</span>
                    </div>
                    <div class="col-xs-8">
                        <input id="input-field" name="edition" type="text">
                    </div>
                    <div class="col-xs-12"><hr></div>
                </div>
                <div class="row">
                    <div class="col-xs-4">
                        <span class="pull-right">Min Power:&nbsp;&nbsp;</span>
                    </div>
                    <div class="col-xs-8">
                        <input id="input-field" name="min power" type="text"><br><br>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-4">
                        <span class="pull-right">Max Power:&nbsp;&nbsp;</span>
                    </div>
                    <div class="col-xs-8">
                        <input id="input-field" name="max power" type="text"><br><br>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-4">
                        <span class="pull-right">Min Toughness:&nbsp;&nbsp;</span>
                    </div>
                    <div class="col-xs-8">
                        <input id="input-field" name="min toughness" type="text"><br><br>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-4">
                        <span class="pull-right">Max Toughness:&nbsp;&nbsp;</span>
                    </div>
                    <div class="col-xs-8">
                        <input id="input-field" name="max toughness" type="text">
                    </div>
                    <div class="col-xs-12"><hr></div>
                </div>
                <div class="row">
                    <div class="col-xs-4">
                        <span class="pull-right">Card Artist:&nbsp;&nbsp;</span>
                    </div>
                    <div class="col-xs-8">
                        <input id="input-field" name="artist" type="text"><br><br>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-4">
                        <span class="pull-right">Year:&nbsp;&nbsp;</span>
                    </div>
                    <div class="col-xs-8">
                        <input id="input-field" name="year" type="text">
                    </div>
                    <div class="col-xs-12"><hr></div>
                </div>
                <br>
                <div class="row">
                    <div class="hidden-xs col-md-4"></div>
                    <div class="col-xs-12 col-md-8">
                        <input id="form-submit" type="submit" value="Search Cards">
                    </div>
                </div>
            </form>
            <br>
        </div>
        <div class="hidden-xs hidden-sm col-md-1" style="border-right: 1px solid white;position: relative;right: 10px;height: 169%;"></div>
        <div class="col-xs-12 col-md-5">
            <h3>Decks<hr></h3>
            <form id="searchDecksForm" action="SearchServlet" method="POST">
                <input type="hidden" name="action" value="users">
                <input type="hidden" name="username" value="<%=username%>">
                <div class="row">
                    <div class="col-xs-4">
                        <span class="pull-right">Deck Title:&nbsp;&nbsp;</span>
                    </div>
                    <div class="col-xs-8">
                        <input id="input-field" name="username" type="text">
                    </div>
                    <div class="col-xs-12"><hr></div>
                </div>
                <div class="row">
                    <div class="col-xs-4">
                        <span class="pull-right">Containing:&nbsp;&nbsp;</span>
                    </div>
                    <div class="col-xs-8">
                        <input id="input-field" name="name" type="text">
                    </div>
                    <div class="col-xs-12"><hr></div>
                </div>
                <div class="row">
                    <div class="col-xs-4">
                        <span class="pull-right">Creator:&nbsp;&nbsp;</span>
                    </div>
                    <div class="col-xs-8">
                        <input id="input-field" name="age" type="text">
                    </div>
                    <div class="col-xs-12"><hr></div>
                </div>
                <br>
                <div class="row">
                    <div class="hidden-xs col-md-4"></div>
                    <div class="col-xs-12 col-md-8">
                        <input id="form-submit" type="submit" value="Search Decks">
                    </div>
                </div>
            </form><br>
            <h3>Users<hr></h3>
            <form id="searchUsersForm" action="SearchServlet" method="POST">
                <input type="hidden" name="action" value="users">
                <input type="hidden" name="username" value="<%=username%>">
                <div class="row">
                    <div class="col-xs-4">
                        <span class="pull-right">Username:&nbsp;&nbsp;</span>
                    </div>
                    <div class="col-xs-8">
                        <input id="input-field" name="username" type="text">
                    </div>
                    <div class="col-xs-12"><hr></div>
                </div>
                <div class="row">
                    <div class="col-xs-4">
                        <span class="pull-right">Name:&nbsp;&nbsp;</span>
                    </div>
                    <div class="col-xs-8">
                        <input id="input-field" name="name" type="text">
                    </div>
                    <div class="col-xs-12"><hr></div>
                </div>
                <div class="row">
                    <div class="col-xs-4">
                        <span class="pull-right">Age:&nbsp;&nbsp;</span>
                    </div>
                    <div class="col-xs-8">
                        <input id="input-field" name="age" type="text">
                    </div>
                    <div class="col-xs-12"><hr></div>
                </div>
                <br>
                <div class="row">
                    <div class="hidden-xs col-md-4"></div>
                    <div class="col-xs-12 col-md-8">
                        <input id="form-submit" type="submit" value="Search Users">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>