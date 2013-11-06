<%@ taglib tagdir="/WEB-INF/tags" prefix="w" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
  <w:head title="${wishlist.name}">
    <link href="/css/wishlist.css" rel="stylesheet"/>
  </w:head>
  
  <body onload="ko.applyBindings(new WishlistViewModel());">
    <script src="/rest.js"></script>
    <script type="text/javascript">
    function toggleQuickEdit() {
    	$('#quickEditTxt').toggle();
        $('#quickEditSaveBtn').toggle();
        $('#quickEditPreviewBtn').toggle();
        $('#quickEditBtn').toggle();
    }

    ko.bindingHandlers.debug = 
    {
        init: function(element, valueAccessor) 
        {
            console.log( 'Knockoutbinding:' );
            console.log( element );
            console.log( valueAccessor() );
        }
    };

    var wishlistId = '${wishlist.id}';
    
    function Category(name, itemsInCat) { this.name = name;this.itemsInCat = itemsInCat; }

    function WishlistViewModel() {
        var wishlist = $.parseJSON('${wishlistJson}');
        self.name = wishlist.name;
        self.description = wishlist.description;
        self.items = ko.observableArray(wishlist.items);
        self.categories = wishlist.categories;
        self.views = wishlist.views;
        
        self.itemsByCategory = ko.computed(function() {
            var itemsByCategory = [];
            itemsByCategory.push(new Category('', $.grep(items(), function(it, i) { return it.category == null; })));

            
            for (c of self.categories) {
                itemsByCategory.push(new Category(c, $.grep(items(), function(it, i) { return it.category == c; })));
            }  
            return itemsByCategory;  
        });

        self.quickEdit = function(preview) {
            var txt = $('#quickEditTxt').val();
            var wishlist = $.parseJSON(WishlistWebService.quickEditWishlistById({id: wishlistId, $entity:txt, preview: preview}));
            self.views = wishlist.views;
            self.categories = wishlist.categories;
            self.items(wishlist.items); 
        }   
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
                    <h1 class="ugc" data-bind="text: name"></h1>
                    <p class="lead">${wishlist.description}</p>
                </td>
                <td style="vertical-align: bottom;">
                        
                      <table>
                        <tr>
                            <td>
                               <button id="quickEditBtn" type="button" class="btn btn-primary" 
                                onclick="toggleQuickEdit()">Quick Edit</button>
                                
                               <button id="quickEditPreviewBtn" type="button" class="btn btn-secondary" style="display: none"
                                 onclick="quickEdit(true)">Preview</button>  
                             </td>
                             <td>
                               <button id="quickEditSaveBtn" type="button" class="btn btn-primary" style="display: none"
                                 onclick="quickEdit(false);toggleQuickEdit()">Save</button>
                             </td>
                        </tr>
                      </table>
                      
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <form>
                       <textarea id="quickEditTxt" class="form-control" rows="6" style="margin-top:15px;display:none;width:100%">
                        ${wishlist.quickEditText}
                       </textarea>
                    </form>
                </td>
            </tr>
        </table>
        
        <hr/>
        
        <div data-bind="foreach: itemsByCategory">
           <h1 class="ugc"><small data-bind="text: name"></small></h1> 
           
           <ul data-bind="foreach: itemsInCat, debug: $data" class="list-unstyled">            
                <li class="wishlist-item">
                    <b  data-bind="text: title"></b>
                    <i data-bind="text: description"></i>
                    <a data-bind="text: link, attr: {href: link}"></a>
                </li>    
           </ul>
        </div>
    </div><!-- /.container -->

    <w:foot/>
    
  </body>
</html>
