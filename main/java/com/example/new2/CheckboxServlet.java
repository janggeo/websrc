package com.example.new2;

import com.example.new2.data.DataDAO;
import com.example.new2.data.Map;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/CheckboxServlet")
public class CheckboxServlet extends HttpServlet {
    List<Map> mapdata;
    private static final long serialVersionUID = 1L;
    public CheckboxServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // 입력한 데이터 수신
        // 여러개의 값을 수신할 때 getParameterValues()를 이용
        String item = request.getParameter("item");

        PrintWriter out = response.getWriter();



        String region = request.getParameter("item");
        ConnectionString connectionString = new ConnectionString("mongodb+srv://jeonghwan:wlsdlstk6%21@vegandata.696tu4w.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        System.out.println(region);
        DataDAO dao = new DataDAO(mongoClient,item);
        mapdata = dao.getMap();


        out.println("<br><a href='javascript.history.go(-1)'>다시</a>");
        out.println("</body></html>");
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

    public List<Map> Get_map(){
        return mapdata;
    }

}
