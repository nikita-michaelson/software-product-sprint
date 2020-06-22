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
@WebServlet("/translate")
public class TranslationServlet extends HttpServlet {
   private ArrayList<String> translatedComments;
    public void init(){
        translatedComments = new ArrayList<>();
        
    }
  /*  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json;");
  

    // convert to json
    Gson gson = new Gson();
    String json = gson.toJson(translatedComments);
    response.getWriter().println(json);
    System.out.println(json);
  
}
*/
 @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the request parameters.
   String originalText = getParameter(request, "text", "");
    String languageCode = getParameter(request, "languageCode", "");
    System.out.println("originalText "+originalText);
    System.out.println("languageCode "+languageCode);

    // Do the translation.
    Translate translate = TranslateOptions.getDefaultInstance().getService();
    Translation translation =
    translate.translate(originalText, Translate.TranslateOption.targetLanguage(languageCode));
    String translatedText = translation.getTranslatedText();
    System.out.println("translatedText " +translatedText);
    
    // Output the translation.
    response.setContentType("text/html; charset=UTF-8");
    response.setCharacterEncoding("UTF-8");   
    response.getWriter().println(translatedText);
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
