package com.example.ejbtutorial;

import com.example.ejb.CalculatorBean;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @EJB
    private CalculatorBean calculatorBean;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        String output = "";
        if (action == null || action.equals("add")) {
            int result = calculatorBean.add(10, 5);
            output = "10 + 5 = " + result;
        }
        else if (action.equals("subtract")) {
            int result = calculatorBean.subtract(10, 5);
            output = "10 - 5 = " + result;
        }
        else if (action.equals("multiply")) {
            int result = calculatorBean.multiply(10, 5);
            output = "10 * 5 = " + result;
        }
        else if (action.equals("divide")) {
            int result = calculatorBean.divide(10, 5);
            output = "10 / 5 = " + result;
        }

        response.getWriter().println(output);
    }
}
