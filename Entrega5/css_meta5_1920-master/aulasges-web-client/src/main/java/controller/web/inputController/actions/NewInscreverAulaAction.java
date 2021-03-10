package controller.web.inputController.actions;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facade.handlers.IInscreverAulaServiceRemote;
import presentation.web.model.InscreveUtenteNaAulaModel;

@Stateless
public class NewInscreverAulaAction extends Action{
	@EJB private IInscreverAulaServiceRemote iiasr;

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		InscreveUtenteNaAulaModel model = new InscreveUtenteNaAulaModel();
		model.setInscreverAulaHandler(iiasr);
		request.setAttribute("model", model);
		request.getRequestDispatcher("/addInscricao/newInscricao.jsp").forward(request, response);
	}
}
