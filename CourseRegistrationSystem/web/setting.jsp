<%-- 
    Document   : setting.jsp
    Created on : Jan 1, 2014, 4:13:58 PM
    Author     : josephstalin
--%>
<%@page import="java.util.List"%>
<%@page import="CloudServlet.UserService"%>
<%@page import="CloudServlet.LoginBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/offcanvas.css" rel="stylesheet">
        <title>个人设置</title>
        <%
            LoginBean login = (LoginBean) session.getAttribute("login");

            if (login == null) {
                session.setAttribute("error", "nologin");
                response.sendRedirect("../index.jsp");
            }
        %>
    </head>
    <body>
        <div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">CRS Project</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">主页</a></li>
                        <li><a href="loginOut.jsp">注销</a></li>
                    </ul>
                </div><!-- /.nav-collapse -->
            </div><!-- /.container -->
        </div><!-- /.navbar -->

        <div class="container">

            <div class="row row-offcanvas row-offcanvas-right">

                <div class="col-xs-12 col-sm-9">
                    <p class="pull-right visible-xs">
                        <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
                    </p>
                    <div class="col-xs-6 col-md-3">
                        <a href="#" class="thumbnail">
                            <img data-src="holder.js/100%x180" alt="..." src="Upload/0.jpg">
                        </a>
                    </div>
                    <div class="col-xs-6 col-md-3">
                        <form role="form" action="UpdateUserServlet" method="get" class="form-horizontal">
                            <div class="form-group">
                                <label for="inputPassword">修改密码</label>
                                <input type="password" class="form-control" id="inputPassword" placeholder="修改密码" name="password">
                                <input type="hidden" class="form-control" id="inputUuid" name="uuid" value="<%=login.getUuid()%>">
                            </div>
                            <div class="form-group">
                                <label for="inputNackname">修改昵称</label>
                                <input type="text" class="form-control" id="inputNackname" placeholder="修改昵称" name="nackname">
                            </div>
                            <button type="submit" class="btn btn-primary btn-lg btn-block" id="submit">Submit</button>
                        </form>
                        <div class="form-group">
                            <form action="FileUploadServlet" method='post' enctype='multipart/form-data'>

                                <%-- 类型enctype用multipart/form-data，这样可以把文件中的数据作为流式数据上传，不管是什么文件类型，均可上传。--%>
                                请选择要上传的背景<input type='file' name="images">
                                <br>
                                <input type='submit' value='提交' class="btn btn-primary btn-lg btn-block" id="submit">
                            </form>
                        </div>
                        <div>
                            <%
                                List<String> fileListInServer = (List<String>) request.getAttribute("downloadList");
                            %>
                            <%=fileListInServer%>
                            <%
                                String path = request.getContextPath();
                            %>
                        </div>
                    </div>
                </div><!--/span-->



                <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
                    <div class="list-group">
                        <!--<a href="admin/admin.jsp" class="list-group-item">管理员主页</a>-->
                        <a href="#" class="list-group-item active">个人设置</a>
                        <a href="javascript:history.go(-2);" class="list-group-item">上一页</a>
                    </div>
                </div><!--/span-->
            </div><!--/row-->


            <footer class="navbar-fixed-bottom">
                <hr>
                <p>&copy; Company 2013</p>
            </footer>

        </div><!--/.container-->
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="js/jquery.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>
        <script src="js/offcanvas.js"></script>
    </body>
</html>
