/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2019-09-10 09:28:30 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\"/>\r\n");
      out.write("    <title>乐购网</title>\r\n");
      out.write("       <link rel=\"stylesheet\" type=\"text/css\" href=\"/css/jquery.alerts.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/headerfooterindex.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/login.css\" />\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/jquery-1.5.1.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/jquery.cookie.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/jquery.alerts.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/png.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/cas.login.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/capsLock.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<!-- header -->\r\n");
      out.write("\t<div class=\"header\">\r\n");
      out.write("\t\t<a href=\"http://www.legou.cn\"><img src=\"/images/logo.png\" border=\"0\"><span>欢迎登录</span></a>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!-- login_main -->\r\n");
      out.write("\t<div class=\"login_main clear\">\r\n");
      out.write("\t\t<div class=\"pic\">\r\n");
      out.write("\t\t\t<a href=\"http://www.legou.cn/html/activity/1472720729.html\" target=\"_blank\"><img src=\"/images/06f42c372620f92b40da77a8b23cdf7f.png\"></a>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- login -->\r\n");
      out.write("\t\t<div class=\"login\">\r\n");
      out.write("\t\t\t<div class=\"login_header\">\r\n");
      out.write("    \t<span>您还未登录</span>\r\n");
      out.write("    \t<a href=\"/page/register\">免费注册</a>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<div class=\"login_box clear\">\r\n");
      out.write("\t\t\t\t<ul class=\"loginnav\">\r\n");
      out.write("\t\t\t\t\t<li class=\"curr\" mark=\"sfbest\"><em></em>优选账号</li>\r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<div class=\"logincon\">\r\n");
      out.write("\t\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t<form id=\"formlogin\" method=\"post\" >\r\n");
      out.write("\t\t\t\t\t\t<div style=\"display:none;\">\r\n");
      out.write("              \t\t\t\t\t \r\n");
      out.write("       \t\t\t\t\t\t </div>\r\n");
      out.write("\t\t\t\t\t\t<li style=\"display:none;\"><span class=\"title\">BGCode</span>\r\n");
      out.write("\t\t\t\t\t\t    <span class=\"border\">\r\n");
      out.write("\t\t\t\t\t\t     <input type=\"hidden\" name=\"bgcode\" id=\"bgcode\" value=\"sfbest\" class=\"loginText\">\r\n");
      out.write("\t\t\t\t\t\t        <em class=\"icon1\" id=\"bgcode_em\"></em>\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t\t    <span id=\"uNameErr\"></span>\r\n");
      out.write("\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t\t<li><span class=\"title\">手机/验证邮箱/用户名<font style=\"padding-left:20px\" class=\"error_line\" color=\"#ff0000\"></font></span>\r\n");
      out.write("\t\t\t\t\t\t    <span class=\"border\" style=\"position:relative\">\r\n");
      out.write("\t\t\t\t\t\t<input id=\"username\" name=\"username\" type=\"hidden\" accesskey=\"n\" value=\"\" size=\"25\" autocomplete=\"off\">\r\n");
      out.write("\t\t\t\t\t\t<input class=\"new-input1 new-color\" tabindex=\"1\" data-clear-btn=\"true\" id=\"loginname\" size=\"25\" autocomplete=\"off\" htmlescape=\"true\" title=\"请输入用户名\">\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t     <em class=\"icon1\" id=\"usernamelogo\"></em>\r\n");
      out.write("\t\t\t\t\t\t<span style=\"position: absolute; font-size: 14px; left: 5px; top: 9px;display:none;\" class=\"sfbest_username_place\">请输入手机号/邮箱/用户名</span>\r\n");
      out.write("\t\t\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t\t    <span id=\"sfbestNameErr\"></span>\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<li class=\"m-t12\">\r\n");
      out.write("\t\t\t\t\t\t\t<span class=\"title\">登录密码</span> \r\n");
      out.write("\t\t\t\t\t\t\t<span class=\"border\" style=\"position:relative\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input id=\"password\" name=\"password\" class=\"required\" tabindex=\"2\" type=\"password\" value=\"\" autocomplete=\"off\">\r\n");
      out.write("\t\t\t\t\t\t\t    <em class=\"icon2\" id=\"passwordlogo\"></em>\r\n");
      out.write("\t\t\t\t\t\t<span style=\"position: absolute; font-size: 14px; left: 5px; top: 9px;display:none;\" class=\"sfbest_password_place\">密码</span>\r\n");
      out.write("\t\t\t\t\t\t\t</span> \r\n");
      out.write("\t\t\t\t\t\t\t<span id=\"sfbestPwdErr\"></span>\r\n");
      out.write("\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"clear\"></div>\r\n");
      out.write("\t\t\t\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t\t\t<div style=\"width: 65px; margin-left: 260px;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<a href=\"https://passport.legou.cn/reg/findpass/?returnUrl=http://www.legou.cn\" class=\"forget-passWord\">忘记密码?</a>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t\t<li class=\"m-t5\" id=\"btn_sub\">\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"hidden\" name=\"lt\" value=\"LT-7055357-If097ZfAK0WqnrgGeScsi6SndWWZFv-sfbest\">\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"hidden\" name=\"execution\" value=\"2b2fc397-b952-4639-b3c3-4aecf3ab23b8_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\">\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"hidden\" name=\"_eventId\" value=\"submit\">\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t<a href=\"javascript:void(0)\" id=\"login_sub\" class=\"login_btn\">登录</a>\r\n");
      out.write("\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t<div>\r\n");
      out.write("</div></form>\r\n");
      out.write("\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<ul class=\"blink\">\r\n");
      out.write("\t\t\t\t\t<li class=\"p-f10\">\r\n");
      out.write("\t\t\t\t\t\t<h2 class=\"h2\">合作网站账户登录：</h2>\r\n");
      out.write("\t\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t\t<a href=\"http://api2.legou.cn/unionlogin/qq/oauth/qq_login.php?returnUrl=http://www.legou.cn/\" class=\"link\">QQ</a>&nbsp;|&nbsp; <a href=\"https://api.weibo.com/oauth2/authorize?client_id=1800908332&amp;redirect_uri=https%3A%2F%2Fpassport.legou.cn%2Fcallback%2Fsina&amp;response_type=code&amp;state=&amp;display=?returnUrl=http://www.legou.cn/\" class=\"link\">新浪微博</a> &nbsp;|&nbsp; <a href=\"https://open.t.qq.com/cgi-bin/oauth2/authorize?client_id=801198099&amp;redirect_uri=https%3A%2F%2Fpassport.legou.cn%2Fcallback%2Fqq&amp;response_type=code&amp;type=?returnUrl=http://www.legou.cn/\" class=\"link\">腾讯微博</a> &nbsp;|&nbsp; <a href=\"http://api2.legou.cn/unionlogin/alipay.php?returnUrl=http://www.legou.cn/\" class=\"link\">支付宝</a>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- /login -->\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!-- /login_main -->\r\n");
      out.write("\t<div class=\"footer\">\r\n");
      out.write("\t\t<span> <a href=\"http://www.legou.cn/www/379/5109.html\" rel=\"nofollow\" class=\"footerlink1\">关于我们</a> | <a href=\"http://www.legou.cn/www/380/5116.html\" rel=\"nofollow\" class=\"footerlink1\">联系我们</a> | <a href=\"http://www.legou.cn/www/381/5117.html\" rel=\"nofollow\" class=\"footerlink1\">招聘人才</a> | <a href=\"http://www.legou.cn/www/330/2705.html\" rel=\"nofollow\" class=\"footerlink1\">友情链接</a> | <a href=\"http://supplier.legou.cn/supplierApply\" rel=\"nofollow\" class=\"footerlink1\">供应商申请</a>\r\n");
      out.write("\t\t</span>\r\n");
      out.write("\t\t<p>\r\n");
      out.write("\t\t\t<a href=\"http://www.miibeian.gov.cn\" target=\"_blank\" rel=\"nofollow\" class=\"footerlink1\">京ICP备12011349号</a>|<a href=\"http://www.legou.cn/www/174/461.html\" target=\"_blank\" rel=\"nofollow\" class=\"footerlink1\">企业营业执照</a><br> Copyright© 乐购网商城\r\n");
      out.write("\t\t\tlegou.cn 版权所有<br>\r\n");
      out.write("\t\t</p>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!-- /footer -->\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\tvar redirectUrl = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${redirect}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("\tvar LOGIN = {\r\n");
      out.write("\t\t\tcheckInput:function() {\r\n");
      out.write("\t\t\t\t$(\"#sfbestNameErr\").attr(\"class\", \"\").html(\"\").prev().attr(\"class\", \"border\");\r\n");
      out.write("\t\t\t\t$(\"#sfbestPwdErr\").attr(\"class\", \"\").html(\"\").prev().attr(\"class\", \"border\");\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tif(!$(\"#formlogin #loginname\").val()) {\r\n");
      out.write("\t\t\t\t\t$(\"#sfbestNameErr\").attr(\"class\", \"error\").show().html(\"请输入用户名\").prev().attr(\"class\", \"border-error\");\r\n");
      out.write("\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tif(!$(\"#formlogin input[name='password']\").val()) {\r\n");
      out.write("\t\t\t\t    $(\"#sfbestPwdErr\").attr(\"class\", \"error\").show().html(\"请输入密码\").prev().attr(\"class\", \"border-error\");\r\n");
      out.write("\t\t\t        return false;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t$(\"#username\").val($(\"#loginname\").val());\r\n");
      out.write("\t\t\t\treturn true;\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tdoLogin:function() {\r\n");
      out.write("\t\t\t\t$.post(\"/user/login\", $(\"#formlogin\").serialize(),function(data){\r\n");
      out.write("\t\t\t\t\tif (data.status == 200) {\r\n");
      out.write("\t\t\t\t\t\tjAlert('登录成功！',\"提示\", function(){\r\n");
      out.write("\t\t\t\t\t\t\tif (redirectUrl == \"\") {\r\n");
      out.write("\t\t\t\t\t\t\t\tlocation.href = \"http://localhost:8082\";\r\n");
      out.write("\t\t\t\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t\t\t\tlocation.href = redirectUrl;\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t\tjAlert(\"登录失败，原因是：\" + data.msg,\"失败\");\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tlogin:function() {\r\n");
      out.write("\t\t\t\tif (this.checkInput()) {\r\n");
      out.write("\t\t\t\t\tthis.doLogin();\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t};\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t$(\"#login_sub\").click(function(){\r\n");
      out.write("\t\t\tLOGIN.login();\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
