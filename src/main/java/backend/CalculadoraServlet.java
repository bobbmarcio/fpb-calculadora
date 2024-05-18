package backend;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/calculadora")
public class CalculadoraServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String num1 = req.getParameter("num1");
        String num2 = req.getParameter("num2");
        String operacao = req.getParameter("operacao");

        double numero1 = Double.parseDouble(num1);
        double numero2 = Double.parseDouble(num2);

        String mensagem = "";
        double resultado;

        switch (operacao) {
            case "soma":
                resultado = numero1 + numero2;
                mensagem = formatarResultado(resultado, operacao);
                break;

            case "subtracao":
                resultado = numero1 - numero2;
                mensagem = formatarResultado(resultado, operacao);
                break;

            case "divisao":
                if (numero1 != 0 && numero2 != 0) {
                    resultado = numero1 / numero2;
                    mensagem = formatarResultado(resultado, operacao);
                } else {
                    resp.getWriter().println("Não é possível realizar divisão por 0");
                }
                break;

            case "multiplicacao":
                resultado = numero1 * numero2;
                mensagem = formatarResultado(resultado, operacao);
                break;

            default:
                mensagem = "Operação inválida";
                break;
        }

        resp.getWriter().println(mensagem);
    }

    private String formatarResultado(double resultado, String operacao) {
        return "Resultado da " + operacao + ": " + resultado;
    }
}