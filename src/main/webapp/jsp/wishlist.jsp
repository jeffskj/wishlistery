<%@ taglib tagdir="/WEB-INF/tags" prefix="w" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
  <w:head title="${wishlist.name}"/>
  
  <body>
    <script type="text/javascript">
    function toggleQuickEdit() {
    	$('#quickEditTxt').toggle();
        $('#quickEditSaveBtn').toggle();
        $('#quickEditBtn').toggle();
    }
    </script>
  
    <div class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Wishlistery</a>
        </div>
        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
          	<!--  class="active" -->
            <li><a href="#">Profile</a></li>
            <li><a href="#about">Wishlists</a></li>
            <li><a href="#contact">Gift Exchanges</a></li>
            <li><a href="#contact">Shopping Lists</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>

    <div class="container content">
        
        <table style="width: 100%">
            <tr>
                <td style="width: 90%">
                    <h1 class="ugc">${wishlist.name}</h1>
                    <p class="lead">${wishlist.description}</p>
                </td>
                <td style="vertical-align: bottom;">
                      <button id="quickEditBtn" type="button" class="btn btn-primary" 
                        onclick="toggleQuickEdit()">Quick Edit</button>
                        
                      <button id="quickEditSaveBtn" type="button" class="btn btn-primary" style="display: none"
                        onclick="toggleQuickEdit()">Save</button>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <form>
                       <textarea id="quickEditTxt" class="form-control" rows="6" style="margin-top:15px;display:none;width:100%"></textarea>
                    </form>
                </td>
            </tr>
        </table>
        
        <hr/>
        
        <ul>
            <c:forEach var="it" items="${wishlist.getItemsInCategory(null)}">
                <li>${it.title}</li>    
            </c:forEach>
        </ul>

        <c:forEach var="cat" items="${wishlist.categories}">
            <c:set var="items" value="${wishlist.getItemsInCategory(cat)}"/>
            <c:if test="${not empty items}">
               <h2 class="ugc">${cat}</h2>
               <ul>
                  <c:forEach var="it" items="${items}">
                       <li>${it.title}</li>    
                  </c:forEach>
                  
               </ul>
            </c:if>
        </c:forEach>

    </div><!-- /.container -->

    <w:foot/>
    
  </body>
</html>
