/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2023-08-22 09:39:47 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import dao.HistoryDao;
import models.History;
import java.util.List;
import models.User;

public final class history_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title>History Summary</title>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"css/styles.css\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");

    HistoryDao historyDao = (HistoryDao) request.getServletContext().getAttribute("history");
    User user = (User) request.getSession().getAttribute("loggedUser");
    List<History> historyList = historyDao.getHistory(user.getId());

      out.write("\r\n");
      out.write("\r\n");
      out.write("<h1>Quiz History Summary</h1>\r\n");
      out.write("\r\n");
 if (historyList.isEmpty()) { 
      out.write("\r\n");
      out.write("<p>No quiz history available for this user.</p>\r\n");
 } else { 
      out.write("\r\n");
      out.write("<table>\r\n");
      out.write("    <tr>\r\n");
      out.write("        <th>Quiz ID</th>\r\n");
      out.write("        <th>Grade</th>\r\n");
      out.write("        <th>Duration</th>\r\n");
      out.write("        <th>Try Again</th>\r\n");
      out.write("    </tr>\r\n");
      out.write("    ");
 for (History history : historyList) { 
      out.write("\r\n");
      out.write("    <tr>\r\n");
      out.write("        <td>");
      out.print( history.getQuizId() );
      out.write("</td>\r\n");
      out.write("        <td>");
      out.print( history.getGrade() );
      out.write("</td>\r\n");
      out.write("        <td>");
      out.print( history.getDuration() );
      out.write("</td>\r\n");
      out.write("        <td><a href=\"/quiz?quiz_id=");
      out.print( history.getQuizId() );
      out.write("\">Do it again</a></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    ");
 } 
      out.write("\r\n");
      out.write("</table>\r\n");
 } 
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
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
