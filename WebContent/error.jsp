  <%@ page isErrorPage="true" import="java.io.PrintWriter" %>

  <html><body>

  <h1 style="color: red">Error</h1>

  <pre>
  <%
  // unwrap ServletExceptions.
  while (exception instanceof ServletException)
    exception = ((ServletException) exception).getRootCause();

  // print stack trace.
  exception.printStackTrace(new PrintWriter(out));
  %>
  </pre>

  </body></html>
