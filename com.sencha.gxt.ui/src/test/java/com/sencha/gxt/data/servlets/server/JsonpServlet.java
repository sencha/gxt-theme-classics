package com.sencha.gxt.data.servlets.server;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonpServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String callback = req.getParameter("callback");
    JSONObject json = new JSONObject();
    for (String field : (Collection<String>) req.getParameterMap().keySet()) {
      JSONArray array = new JSONArray();
      for (String value : req.getParameterValues(field)) {
        array.put(value);
      }
      try {
        json.put(field, array);
      } catch (JSONException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    StringBuilder sb = new StringBuilder();
    sb.append(callback).append("(");
    sb.append(json.toString());
    sb.append(");");

    resp.getOutputStream().print(sb.toString());
  }
}
