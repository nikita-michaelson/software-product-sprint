// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import java.util.ArrayList;
import java.util.List;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {
    private ArrayList<String> comments;
    public void init(){
        comments = new ArrayList<>();
        
    }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json;");
     
     // Get the request parameters.
    String originalText = request.getParameter("text");
    String languageCode = request.getParameter("languageCode");

    // Do the translation.
    Translate translate = TranslateOptions.getDefaultInstance().getService();
    Translation translation =
        translate.translate(originalText, Translate.TranslateOption.targetLanguage(languageCode));
    String translatedText = translation.getTranslatedText();

    // convert to json
    Gson gson = new Gson();
    String json = gson.toJson(comments);
    String jsonTrans = gson.toJson(translatedText);

    response.getWriter().println(json);
  
}
 @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the input from the form.
    String name = getParameter(request, "userName", "");
    String comment = getParameter(request, "comment", "");
   // System.out.println(comment);
   for(String x : comments){
       System.out.println(x);
   }
    // Respond with the result.
    response.setContentType("text/html;");
    comments.add(comment+"            --"+name);
      Entity comEntity = new Entity("Task");
    comEntity.setProperty("name", name);
    comEntity.setProperty("comment", comment);

    //using the data store to make comments persist
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(comEntity);
    response.sendRedirect("index.html");
    response.getWriter().println(comments);
  }

  /**
   * @return the request parameter, or the default value if the parameter
   *         was not specified by the client
   */
  private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }
}
