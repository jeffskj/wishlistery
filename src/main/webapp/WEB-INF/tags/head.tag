<%@ attribute name="title" required="true" %>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <meta name="description" content="">
   <meta name="author" content="">
   
   <title>Wishlistery: ${title}</title>
   
   <!-- Bootstrap core CSS -->
   <link href="/css/bootstrap.min.css" rel="stylesheet">
   
   <link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>
   <link href="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet"/>
   <script src="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
   <!-- Custom styles for this template -->
   <link href="/css/template.css" rel="stylesheet">
   <script src="//code.jquery.com/jquery-2.0.3.js"></script>
   <script src="http://cdnjs.cloudflare.com/ajax/libs/knockout/3.0.0/knockout-min.js"></script>
   <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
   <!--[if lt IE 9]>
      <script src="../../assets/js/html5shiv.js"></script>
      <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->
    <jsp:doBody />
</head>