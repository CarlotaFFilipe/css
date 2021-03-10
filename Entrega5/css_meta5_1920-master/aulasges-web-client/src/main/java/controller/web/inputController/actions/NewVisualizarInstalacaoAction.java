package controller.web.inputController.actions;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facade.handlers.IVisualizarServiceRemote;
import presentation.web.model.VisualizarOcupacaoAulaModel;

public class NewVisualizarInstalacaoAction extends Action {

	@EJB private IVisualizarServiceRemote visualizarHandler;

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		VisualizarOcupacaoAulaModel model = new VisualizarOcupacaoAulaModel();
		model.setVisualizarHandler(visualizarHandler);
		request.setAttribute("model", model);
		request.getRequestDispatcher("/addCustomer/newVisualizar.jsp").forward(request, response);
	}
}
