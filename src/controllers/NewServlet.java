package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Timestamp;
import javax.persistence.EntityManager;
import models.Message;
import utils.DBUtil;


@WebServlet("/new")
public class NewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public NewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();

        //messageのインスタンスを作成
        Message m =new Message();

        //mのフィールドにデータを代入
        String title="taro";
        m.setTitle(title);

        String content ="hello";
        m.setContent(content);

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());//現在日時
        m.setCreated_at(currentTime);
        m.setUpdated_at(currentTime);

        //データベースに保存
        em.persist(m);
        em.getTransaction().commit();

        //自動採番されたIDを表示
        response.getWriter().append(Integer.valueOf(m.getId()).toString());

        em.close();
    }

}
