package com.example.ejbtutorial;

import com.example.ejb.LoggerBean;
import jakarta.ejb.EJB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/logger")
public class LoggerServlet extends HttpServlet {

    @EJB
    private LoggerBean loggerBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String message = req.getParameter("message");
        if(message != null){
            loggerBean.log(message);
            resp.getWriter().println("Logger:" + message);
        }
        else{
            resp.getWriter().println("Logger:No Message provided");
        }
    }
}
